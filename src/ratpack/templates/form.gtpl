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
			img(src:"/images/iol-icon.svg", alt:'logo')
			h1(title)
		}
		hr()
		form (id:'request', action:'result', method:'get') {
			div(class: 'form-fields') {
				div(class: 'form-field') {
					label (class: 'form-field-label', for:'id', 'Codice Commissione')
					div   (class: 'form-field-control') {
						input (
							name:'id',
							type:'text',
							pattern:'^(?=.{9,10})[A-Z0-9]{1,4}[0-9]{4,8}$',
							required:''
						)
					}
					div   (class: 'form-field-comment', 'Required')
				}
				div(class: 'form-field') {
					label (class: 'form-field-label', for:'start', 'Start date')
					div   (class: 'form-field-control') {
						input (
							name:     'start',
							type:     'date',
							value:    start,
							max:      max,
							required: ''
						)
					}
					div   (class: 'form-field-comment', 'Required')
				}
				div(class: 'form-field') {
					label (class: 'form-field-label', for:'end', 'End date')
					div   (class: 'form-field-control') {
						input (
							name:     'end',
							type:     'date',
							value:    end,
							max:      max,
							required: ''
						)
					}
					div   (class: 'form-field-comment', 'Required')
				}
				div(class: 'form-field') {
					input (
						type: 'hidden',
						name: 'maxdate',
						value: max
					)
				}
				div(class: 'form-field') {
					div   (class: 'form-field-control') {
						input (
							type:  'submit',
							value: 'Query'
						)
					}
				}
			} // form-fields
			p {
				strong('NOTA: ')
				yield "dati Yext disponibili fino al ${max}"
			}
		}
	}
}
