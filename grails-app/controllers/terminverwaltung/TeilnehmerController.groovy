package terminverwaltung

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TeilnehmerController {

    TeilnehmerService teilnehmerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond teilnehmerService.list(params), model:[teilnehmerCount: teilnehmerService.count()]
    }

    def show(Long id) {
        respond teilnehmerService.get(id)
    }

    def create() {
        respond new Teilnehmer(params)
    }

    def save(Teilnehmer teilnehmer) {
        if (teilnehmer == null) {
            notFound()
            return
        }

        try {
            teilnehmerService.save(teilnehmer)
        } catch (ValidationException e) {
            respond teilnehmer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'teilnehmer.label', default: 'Teilnehmer'), teilnehmer.id])
                redirect teilnehmer
            }
            '*' { respond teilnehmer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond teilnehmerService.get(id)
    }

    def update(Teilnehmer teilnehmer) {
        if (teilnehmer == null) {
            notFound()
            return
        }

        try {
            teilnehmerService.save(teilnehmer)
        } catch (ValidationException e) {
            respond teilnehmer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'teilnehmer.label', default: 'Teilnehmer'), teilnehmer.id])
                redirect teilnehmer
            }
            '*'{ respond teilnehmer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        teilnehmerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'teilnehmer.label', default: 'Teilnehmer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'teilnehmer.label', default: 'Teilnehmer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
