package it.italiaonline.grational.yext

import it.italiaonline.grational.ratpack.conf.YextApi

@groovy.transform.ToString(includeNames=true)
class Analytics {
	String  customer
	String  start
	String  end
	YextApi api

	String hostname() {
		this.api.url.find('(?<=//)[^/]+')
	}

	String endpoint() {
		"v2/accounts/${this.customer}/analytics/reports"
	}

	Map qparams() {
		[
			api_key: api.key,
			v:       new Date().format('yyyyMMdd')
		]
	}

	Map body() {
		[
			filters: [
				startDate: this.start,
				endDate:   this.end
			],
			metrics: [
				'GOOGLE_CUSTOMER_ACTIONS'
			],
			dimensions: [
				'DAYS',
				'CUSTOMER_ACTION_TYPE'
			]
		]
	}
}
