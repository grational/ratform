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
		p {
			a(href:'query') { yield 'new search' }
		}
		table {
			tr {
				th('Cliente')
				td(name)
			}
			tr {
				th('Periodo')
				td(period)
			}
			tr {
				th('Dati Yext disponibili fino al')
				td(maxdate)
			}
			tr {
				th('ID customer Yext')
				td(id)
			}
			tr {
				th('ID bozza Yext')
				td(bozza)
			}
			tr {
				th('Stato bozza')
				td(state)
			}
		}
		hr()
		table {
			tr {
				th('day')
				th('calls')
				th('directions')
				th('website clicks')
			}
			actions.each { entry ->
				tr {
					td(entry.day)
					td(entry.calls)
					td(entry.directions)
					td(entry.clicks)
				}
			}
		}
		p {
			a(href:'query') { yield 'new search' }
		}
	}
}
