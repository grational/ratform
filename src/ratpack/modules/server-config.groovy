/*
 * This ratpack file was auto generated by 'gigawatt'
 * @author d7392
 * @date 16-06-2018 02.36
 *
 * Example conf class
 * ------------------
 * class YextApi {
 *   String key
 *   String url
 * }
 *
 * Corresponding env variables
 * ---------------------------
 * export RATPACK_YEXT__API__KEY='123abcd'
 * export RATPACK_YEXT__API__URL='https://api.yext.com/v2'
 */
// external
import static ratpack.groovy.Groovy.ratpack
// internal
import it.italiaonline.grational.ratpack.conf.Proxy
import it.italiaonline.grational.ratpack.conf.YextApi
import it.italiaonline.grational.ratpack.conf.IolapiDb

ratpack {
	serverConfig {
		env()
		require('/yext/api', YextApi)
		require('/iolapi/db', IolapiDb)
		require("/iol/proxy", Proxy)
	}
}
