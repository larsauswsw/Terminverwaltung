import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.StandardCharsets

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
List<String> appenders = new LinkedList<String>()
if ( Environment.current == Environment.DEVELOPMENT ) {
    appender( 'CONSOLE', ConsoleAppender ) {
        encoder( PatternLayoutEncoder ) {
            charset = StandardCharsets.UTF_8

            pattern =
                    '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                            '%clr(%5p) ' + // Log level
                            '%clr(---){faint} %clr([%t]){faint} ' + // Thread
                            '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                            '%m%n%wex' // Message
        }// encoder
    }// appender
    appenders.add( "CONSOLE" )
}// if

//
// Ausgabeordner festlegen. TARGET_DIR ist der build-Ordner im Projektverzeichnis.
// Für PRODUCTION sollte der Pfad daher an die Verzeichnisstruktur des Kunden angepasst werden.
//
String targetDir = BuildSettings.TARGET_DIR
switch ( Environment.current ) {
    case Environment.DEVELOPMENT:
        targetDir = BuildSettings.TARGET_DIR
        break
    case Environment.CUSTOM:
        targetDir = "C:/avi/daten/log/LEAG"
        break
    case Environment.PRODUCTION:
        targetDir = "/var/log/tomcat8"
        break
    default:
        targetDir = BuildSettings.TARGET_DIR
        break
}


//
// Rolling File Appender
// Logs werden mit Threshold INFO in das hier angegebene File geschrieben.
// Jeden Tag wird ein neues Files angelegt und das alte archiviert.
// Archivierte Logs werden nach 200 Tagen gelöscht.
//
appender( "ROLLING", RollingFileAppender ) {
    file = "${targetDir}/LuQ2.log"
    append = true
    encoder( PatternLayoutEncoder ) {
        charset = StandardCharsets.UTF_8
        pattern = "[%d{dd.MM.yyyy HH:mm:ss}][%level][%logger] - %msg%n"
    }// encoder
    rollingPolicy( TimeBasedRollingPolicy ) {
        fileNamePattern = "${targetDir}/%d{yyyy_MM_dd}_LuQ2.log"
        maxHistory = 200
    }// rollingPolicy
}// appender
//
// Rolling File Appender an die Liste der aktiven Appender anhängen
//
appenders.add( "ROLLING" )

//logger( "org.hibernate.orm.deprecation", ERROR, appenders, false )

root( INFO, appenders )
