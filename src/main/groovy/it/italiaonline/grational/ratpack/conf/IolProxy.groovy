package it.italiaonline.grational.ratpack.conf

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.Proxy.Type

final class IolProxy {
	final Proxy.Type type
	final String     host
	final Integer    port

	IolProxy (
		@JsonProperty(value="type", required=true)
		String type,
		@JsonProperty(value="host", required=false)
		String host,
		@JsonProperty(value="port", required=false)
		String port
	) {
		this.type = type as Proxy.Type
		this.host = host
		this.port = port as Integer
	}
}
