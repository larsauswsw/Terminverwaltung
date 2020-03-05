package terminverwaltung

import grails.gorm.services.Service

@Service(Teilnehmer)
interface TeilnehmerService {

    Teilnehmer get(Serializable id)

    List<Teilnehmer> list(Map args)

    Long count()

    void delete(Serializable id)

    Teilnehmer save(Teilnehmer teilnehmer)

}