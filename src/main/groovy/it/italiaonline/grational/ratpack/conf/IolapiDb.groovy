package it.italiaonline.grational.ratpack.conf

import com.fasterxml.jackson.annotation.JsonProperty

final class IolapiDb {

	final String jdbcUrl
	final String username
	final String password
	final String driverClassName

	IolapiDb (
		@JsonProperty(value="jdbcUrl", required=true)
		String jdbcUrl,
		@JsonProperty(value="username", required=true)
		String username,
		@JsonProperty(value="password", required=true)
		String password,
		@JsonProperty(value="driverClassName", required=true)
		String driverClassName
	) {
		this.jdbcUrl         = jdbcUrl
		this.username        = username
		this.password        = password
		this.driverClassName = driverClassName
	}
}
