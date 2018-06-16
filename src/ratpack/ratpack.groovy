/*
 * This ratpack file was auto generated by 'gigawatt'
 * @author d7392
 * @date 16-06-2018 02.36
 */
import static ratpack.groovy.Groovy.ratpack

ratpack {
	// cors headers
	include 'modules/cors.groovy'

	// external file conf
	include 'modules/server-config.groovy'

	// hikari and markup server bindings
	include 'modules/server-bindings.groovy'

	// fake business logic module
	include 'modules/business-stub.groovy'

	// logging (have to be the last one)
	include 'modules/request-log.groovy'
}
