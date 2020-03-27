package terminverwaltung.security

import grails.gorm.transactions.Transactional

@Transactional
class SecurityBootstrapService {

    def springSecurityService

    def initDB() {

        //https://grails-plugins.github.io/grails-spring-security-core/4.0.x/index.html#authorityClass

            def adminRole = new Role(authority: 'ROLE_ADMIN').save()

            def testUser = new User(username: 'me', password: 'password').save()

            def userRole = new UserRole(user: testUser, role: adminRole).save()

            log.error("askdhasdgjk")

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            //new RequestMap(url: url, configAttribute: 'permitAll').save(failOnError:true)
        }

        springSecurityService.clearCachedRequestmaps()



        log.warn("User     count: "  + User.count())
        log.warn("Role     count: "  +  Role.count())
        log.warn("UserRole count: "  +  UserRole.count())

    }
}
