package terminverwaltung

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TeilnehmerServiceSpec extends Specification {

    TeilnehmerService teilnehmerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Teilnehmer(...).save(flush: true, failOnError: true)
        //new Teilnehmer(...).save(flush: true, failOnError: true)
        //Teilnehmer teilnehmer = new Teilnehmer(...).save(flush: true, failOnError: true)
        //new Teilnehmer(...).save(flush: true, failOnError: true)
        //new Teilnehmer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //teilnehmer.id
    }

    void "test get"() {
        setupData()

        expect:
        teilnehmerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Teilnehmer> teilnehmerList = teilnehmerService.list(max: 2, offset: 2)

        then:
        teilnehmerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        teilnehmerService.count() == 5
    }

    void "test delete"() {
        Long teilnehmerId = setupData()

        expect:
        teilnehmerService.count() == 5

        when:
        teilnehmerService.delete(teilnehmerId)
        sessionFactory.currentSession.flush()

        then:
        teilnehmerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Teilnehmer teilnehmer = new Teilnehmer()
        teilnehmerService.save(teilnehmer)

        then:
        teilnehmer.id != null
    }
}
