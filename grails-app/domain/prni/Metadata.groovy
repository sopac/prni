package prni

class Metadata {

    String name
    Country country
    String area
    String project
    String year
    String description
    String surveyType
    String format
    String projection
    String westBoundLongitude
    String northBoundLatitude
    String eastBoundLongitude
    String southBoundLatitude
    String geonetwork
    String pacgeo


    static constraints = {
        name(nullable: false, blank: false)
        country(nullable: false)
        area(nullable: true, blank: true)
        project(nullable: true, blank: true)
        year(nullable: true, blank: true)
        description(nullable: true, blank: true)
        surveyType(nullable: true, blank: true)
        format(nullable: true, blank: true)
        projection(nullable: true, blank: true)
        westBoundLongitude(nullable: true, blank: true)
        northBoundLatitude(nullable: true, blank: true)
        eastBoundLongitude(nullable: true, blank: true)
        southBoundLatitude(nullable: true, blank: true)
        geonetwork(nullable: true, blank: true)
        pacgeo(nullable: true, blank: true)
    }
}
