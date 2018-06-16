/*
 * This ratpack file was auto generated by 'gigawatt'
 * @author d7392
 * @date 16-06-2018 02.36
 */
// external
import groovy.json.JsonSlurper
import static groovy.json.JsonOutput.prettyPrint
import static groovy.json.JsonOutput.toJson
import groovy.sql.Sql
import java.util.Properties
// ratpack
import static ratpack.groovy.Groovy.ratpack
import ratpack.groovy.template.MarkupTemplateModule
import static ratpack.groovy.Groovy.groovyMarkupTemplate
import ratpack.http.client.HttpClient
import ratpack.hikari.HikariModule
import com.zaxxer.hikari.HikariConfig
import ratpack.groovy.sql.SqlModule
import ratpack.exec.Blocking
// query rest with proxy
import static groovyx.net.http.HttpBuilder.configure
import static groovyx.net.http.ContentTypes.JSON
import groovyx.net.http.*
// local
import it.italiaonline.grational.yext.Analytics
import it.italiaonline.grational.ratpack.conf.Proxy
import it.italiaonline.grational.ratpack.conf.YextApi
import it.italiaonline.grational.ratpack.conf.IolconnectDb

ratpack {

	handlers {
		files { dir('static') }
		prefix('support/yext/google/actions') {
			get ('query') {
				render(
					groovyMarkupTemplate([
							title: "Query Yext for Google Actions",
							start: "2018-01-01",
							end:   "2018-06-04"
						],
						"form.gtpl"
					)
				)
			}

			get('result') { HttpClient httpClient, YextApi api, Sql iolconnectDb, Proxy proxy ->
				def qp = request.queryParams

				Blocking.get {
					iolconnectDb.firstRow("""
						|select
						|  b.ID_CUSTOMER id,
						|  b.NAME        name,
						|  b.ID_BOZZA    bozza,
						|  sb.STATO      state
						| from BOZZA b
						| left join STATI_BOZZA sb on b.ID_STATO = sb.ID_STATO
						| where b.CC_IDB = '${qp.id}'""".stripMargin())
				} flatRight { row ->
					// increase of one day because the upper interval is open
					def end = (Date.parse('yyyy-MM-dd',qp.end) + 1).format('yyyy-MM-dd')
					def customer = row.id
					def analytics = new Analytics (
						customer: customer,
						start:    qp.start,
						end:      end,
						api:      api
					)
					
					Blocking.get {
						configure {
							if (proxy.enabled()) {
								execution.proxy(
									proxy.host,
									proxy.port,
									java.net.Proxy.Type.HTTP,
									true
								)
							}

							request.uri         = api.url
							request.contentType = JSON.first() // 'application/json'
						
							response.parser(JSON.first()) { config, resp ->
								// select data and grep just calls and directions
								NativeHandlers.Parsers.json(config, resp)
								.response.data  // select sub section
								.groupBy { it.day }
								.collect { date, actions -> // rationalize content in the form [ calls: #, directions: #, clicks: # ]
									def actionMap = actions.collectEntries {
										def action = it.customer_action_type.toLowerCase().find(/\S+$/) // take the latest word
										[ (action): it.'Google Customer Actions' ]
									}
									actionMap << [ day: date ]
								}
							}
						}.post() {
							request.uri.path  = "/v2/accounts/${customer}/analytics/reports"
							request.uri.query = analytics.qparams()
							request.body      = analytics.body()
						}
					}
				} then { pair ->
					def row = pair.left
					def data  = pair.right
					data.sort { a,b -> b.day <=> a.day }
					println "data -> $data"
					render(
						groovyMarkupTemplate([
							title:  "Google Actions",
							period: "${qp.start} / ${qp.end}",
							actions: data,
							name:    row.name,
							id:      row.id,
							bozza:   row.bozza,
							state:   row.state ],
							"output.gtpl"
						)
					)
				} // then
			} // get('result')
		} // prefix
	} // handlers 
} // ratpack
