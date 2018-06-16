package it.italiaonline.grational.yext

import it.italiaonline.grational.ratpack.conf.YextApi

class Analytics {
	String  customer
	String  start
	String  end
	YextApi api

	String hostname() {
		this.api.url.find('(?<=//)[^/]+')
	}

	URI uri() {
		def endpoint = "accounts/${customer}/analytics/reports"
		// api url
		def now    = new Date()
		def params = [
			api_key: api.key,
			v:       now.format('yyyyMMdd')
		]
		def qstring = params.collect { k, v -> "$k=$v" }.join('&')
		return "${this.api.url}/${endpoint}?${qstring}".toURI()
	}

	Map payload() {
		return [
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
