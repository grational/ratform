package it.italiaonline.grational.ratpack.conf

import com.fasterxml.jackson.annotation.JsonProperty

final class YextApi {
	private final String url
	private final Map    key

	YextApi (
		@JsonProperty(value="url", required=true)
		String url,
		@JsonProperty(value="key", required=true)
		Map    key
	) {
		this.url = url
		this.key = key
	}
}
