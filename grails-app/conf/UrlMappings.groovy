class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?(.$format)?" {
			constraints {
				// Apply constraints here
			}
		}

		"/"(view: "/index")
		"500"(view: '/error')
	}
}
