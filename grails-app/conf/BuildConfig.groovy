grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
// grails.project.war.file = "target/${appName}-${appVersion}.war"

def gebVersion = "0.9.3"
def seleniumVersion = "2.42.0"

grails.project.fork = [
	// configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
	//  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

	// configure settings for the test-app JVM, uses the daemon by default
	test   : [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon: true],
	// configure settings for the run-app JVM
	run    : [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve: false],
	// configure settings for the run-war JVM
	war    : [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve: false],
	// configure settings for the Console UI JVM
	console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		// specify dependency exclusions here; for example, uncomment this to disable ehcache:
		// excludes 'ehcache'
	}
	log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	checksums true // Whether to verify checksums on resolve
	legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

	repositories {
		inherits true // Whether to inherit repository definitions from plugins

		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()

		// Geb
		mavenRepo "https://nexus.codehaus.org/content/repositories/snapshots"
	}

	dependencies {
		// Specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
		// runtime 'mysql:mysql-connector-java:5.1.29'
		// runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
		test "org.grails:grails-datastore-test-support:1.0-grails-2.4"

		// Geb
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion")
		test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
		test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")

		test "org.gebish:geb-spock:$gebVersion"
		test "org.gebish:geb-junit4:$gebVersion"
	}

	plugins {
		def env = grails.util.Environment.currentEnvironment.name
		def localEnvs = ["development", "test"]

		// Plugins for the build system only
		build ":tomcat:7.0.54"

		// Plugins for the compile step
		compile ":scaffolding:2.1.2"
		compile ':cache:1.1.7'
		compile ":asset-pipeline:1.8.11"

		// User installed plugins
		compile ":build-info:1.2.6"
		compile ":build-info-tag:0.3.1"
		compile ":build-test-data:2.2.1"
		compile ":codenarc:0.21"

		if (env in localEnvs) {
			compile ":console:1.5.0"
		}

		// Plugins needed at runtime but not for compilation
		runtime ":hibernate4:4.3.5.4" // or ":hibernate:3.6.10.16"
		runtime ":database-migration:1.4.0"
		runtime ":jquery:1.11.1"

		// Uncomment these to enable additional asset-pipeline capabilities
		// compile ":sass-asset-pipeline:1.7.4"
		// compile ":less-asset-pipeline:1.7.0"
		// compile ":coffee-asset-pipeline:1.7.0"
		// compile ":handlebars-asset-pipeline:1.3.0.3"

		test ":geb:$gebVersion"
	}
}
