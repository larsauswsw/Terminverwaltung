package terminverwaltung



class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        SpringRole springRole = new SpringRole(authority: "Admin")
        println (springRole.save())

        SpringUser springUser = new SpringUser(username: "admin", password: ".admin")
        println (springUser.save())

        SpringUserSpringRole springUserSpringRole = new SpringUserSpringRole(springRole: springRole, springUser: springUser)
        println (springUserSpringRole.save())


        Requestmap requestmap = new Requestmap(configAttribute: "ROLE_" + springRole.authority, url: "/**")
        println (requestmap.save())

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }
        springSecurityService.clearCachedRequestmaps()

    }
    def destroy = {
    }
}
