package terminverwaltung

import grails.gorm.services.Service

@Service(Termin)
interface TerminService {

    Termin get(Serializable id)

    List<Termin> list(Map args)

    Long count()

    void delete(Serializable id)

    Termin save(Termin termin)

}