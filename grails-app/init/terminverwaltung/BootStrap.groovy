package terminverwaltung

class BootStrap {

    def securityBootstrapService

    def init = { servletContext ->
        securityBootstrapService.initDB()
    }
    def destroy = {
    }
}
