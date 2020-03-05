package terminverwaltung

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TerminServiceSpec extends Specification {

    TerminService terminService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Termin(...).save(flush: true, failOnError: true)
        //new Termin(...).save(flush: true, failOnError: true)
        //Termin termin = new Termin(...).save(flush: true, failOnError: true)
        //new Termin(...).save(flush: true, failOnError: true)
        //new Termin(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //termin.id
    }

    void "test get"() {
        setupData()

        expect:
        terminService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Termin> terminList = terminService.list(max: 2, offset: 2)

        then:
        terminList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        terminService.count() == 5
    }

    void "test delete"() {
        Long terminId = setupData()

        expect:
        terminService.count() == 5

        when:
        terminService.delete(terminId)
        sessionFactory.currentSession.flush()

        then:
        terminService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Termin termin = new Termin()
        terminService.save(termin)

        then:
        termin.id != null
    }
}
