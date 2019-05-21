yieldUnescaped '<!doctype html>'
html {
	head {
		meta(charset:'utf-8')
		meta(name:'viewport', content='width=device-width, initial-scale=1.0')
		title(title)
		link(rel: 'stylesheet', href: '/css/main.css')
	}
	body {
		div(class: 'header') {
			img(src:'/images/iol-icon.svg', alt:'logo')
			h1(title)
		}
		hr()
		if (cc)      p("No customer found for the cc '${cc}'")
		if (draftId) p("No details found for the draftId '${draftId}' in the Iolconnect database")
		p {
			a(href:'javascript:history.back()') { yield 'back' }
		}
	}
}
