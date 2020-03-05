package terminverwaltung

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TerminController {

    TerminService terminService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond terminService.list(params), model:[terminCount: terminService.count()]
    }

    def show(Long id) {
        respond terminService.get(id)
    }

    def create() {
        respond new Termin(params)
    }

    def save(Termin termin) {
        if (termin == null) {
            notFound()
            return
        }

        try {
            terminService.save(termin)
        } catch (ValidationException e) {
            respond termin.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'termin.label', default: 'Termin'), termin.id])
                redirect termin
            }
            '*' { respond termin, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond terminService.get(id)
    }

    def update(Termin termin) {
        if (termin == null) {
            notFound()
            return
        }

        try {
            terminService.save(termin)
        } catch (ValidationException e) {
            respond termin.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'termin.label', default: 'Termin'), termin.id])
                redirect termin
            }
            '*'{ respond termin, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        terminService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'termin.label', default: 'Termin'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'termin.label', default: 'Termin'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
