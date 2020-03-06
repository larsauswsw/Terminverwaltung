package terminverwaltung

class Teilnehmer {

    String vorname
    String name
    String telefon

    static constraints = {
        vorname( nullable: false )
        name( nullable: false )
        telefon( nullable: false )
    }
}
