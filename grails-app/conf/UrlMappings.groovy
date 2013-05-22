class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?" {
			constraints {
				// Apply constraints here
			}
		}

		"/"(view: "/index")
		"500"(view: '/error')
	}
}
