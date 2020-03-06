package terminverwaltung

class Termin {

    Date datum
    String titel
    Boolean wiederhohlung
    Date enddatum


    static constraints = {
        datum( nullable: false )
        titel( nullable: false )
        wiederhohlung( nullable: false )
        enddatum( nullable: true )
    }
}
