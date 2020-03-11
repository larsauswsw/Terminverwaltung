package terminverwaltung

import grails.gorm.services.Service

@Service( Kurs )
interface KursService {

    Kurs get( Serializable id )

    List<Kurs> list( Map args )

    Long count()

    void delete( Serializable id )

    Kurs save( Kurs kurs )

}