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
			tr {
				th('Periodo')
				td(period)
			}
			tr {
				th('Total calls')
				td(actions.calls.sum())
			}
			tr {
				th('Total directions')
				td(actions.directions.sum())
			}
			tr {
				th('Total website clicks')
				td(actions.clicks.sum())
			}
			tr {
				th('Dati Yext disponibili fino al')
				td(maxdate)
			}
		}
		hr()
		p {
			h3('Details')
		}
		table {
			tr {
				th("row (#${actions.size()})")
				th("day")
				th("calls")
				th("directions")
				th("website clicks")
			}
			actions.eachWithIndex { entry, idx ->
				tr {
					td(idx+1)
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
