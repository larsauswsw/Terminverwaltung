package terminverwaltung

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class KursController {


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index( Integer max ) {
        params.max = Math.min( max ?: 10, 100 )
        respond kursService.list( params ), model: [kursCount: kursService.count()]
    }

    def show( Long id ) {
        respond kursService.get( id )
    }

    def create() {
        respond new Kurs( params )
    }

    def save( Kurs kurs ) {
        if ( kurs == null ) {
            notFound()
            return
        }

        try {
            kursService.save( kurs )
        }
        catch ( ValidationException e ) {
            respond kurs.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message( code: 'default.created.message', args: [message( code: 'kurs.label', default: 'Kurs' ), kurs.id] )
                redirect kurs
            }
            '*' { respond kurs, [status: CREATED] }
        }
    }

    def edit( Long id ) {
        respond kursService.get( id )
    }

    def update( Kurs kurs ) {
        if ( kurs == null ) {
            notFound()
            return
        }

        try {
            kursService.save( kurs )
        }
        catch ( ValidationException e ) {
            respond kurs.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message( code: 'default.updated.message', args: [message( code: 'kurs.label', default: 'Kurs' ), kurs.id] )
                redirect kurs
            }
            '*' { respond kurs, [status: OK] }
        }
    }

    def delete( Long id ) {
        if ( id == null ) {
            notFound()
            return
        }

        kursService.delete( id )

        request.withFormat {
            form multipartForm {
                flash.message = message( code: 'default.deleted.message', args: [message( code: 'kurs.label', default: 'Kurs' ), id] )
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message( code: 'default.not.found.message', args: [message( code: 'kurs.label', default: 'Kurs' ), params.id] )
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
