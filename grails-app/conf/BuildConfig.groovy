import grails.util.Environment

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}-${Environment.current.name}-${"git rev-parse --short HEAD".execute().text.replace('\n', '')}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised but here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal(null)
        mavenCentral()

        // this lets us load maven dependencies from the BV repo; relies on ~/.grails/settings.groovy to have your credentials
        // as described here: https://github.com/bazaarvoice/portal-grails/blob/master/doc/dev_environment_setup.md
        mavenRepo "https://repo.bazaarvoice.com/nexus/content/groups/bazaarvoice"
        mavenRepo "https://repo.bazaarvoice.com/nexus/content/repositories/releases"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.20'
        compile "org.codehaus.groovy.modules.http-builder:http-builder:0.7"
    }

    plugins {
        runtime ":hibernate:3.6.10.2"
        runtime ":resources:1.2"
        compile ":spring-security-core:1.2.7.3"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:7.0.47"

        runtime ":database-migration:1.2.1"

        compile ':cache:1.0.1'

        // PORTAL-GRAILS TODO ensure these are the latest version of our plugins
        compile ":portal-app:1.1.2"
        compile ":bv-common:1.0.6"
    }
}
