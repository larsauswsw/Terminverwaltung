package terminverwaltung

class Kurs {

    Date startzeit
    Date endzeit
    Date ende


    static constraints = {
        startzeit( nullable: false )
        endzeit( nullable: false )
        ende( nullable: true )
    }

}
