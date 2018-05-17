package prni

class MedinGeneral {

    static searchable = true

    Metadata metadata

    String projectName
    String projectCode
    String projectStartDate
    String projectEndDate
    String projectWebsite
    String surveyName
    String surveyType
    String surveyAbstract
    String surveyCode
    String originator
    String owner
    String surveyStartDate
    String surveyEndDate
    String timeZone
    String spatialCRS
    String originalCRS
    String transformation
    String positionFix
    String horizontalAccuracy
    String depthCRS
    String verticalAccuracy
    String platformType
    String platformName
    String cruiseReportReference
    String confidentiality


    static constraints = {

        metadata()
        projectName(nullable: true, blank: true, maxSize: 2500)
        projectCode(nullable: true, blank: true, maxSize: 2500)
        projectStartDate(nullable: true, blank: true, maxSize: 2500)
        projectEndDate(nullable: true, blank: true, maxSize: 2500)
        projectWebsite(nullable: true, blank: true, maxSize: 2500)
        surveyName(nullable: true, blank: true, maxSize: 2500)
        surveyType(nullable: true, blank: true, maxSize: 2500)
        surveyAbstract(nullable: true, blank: true, maxSize: 2500)
        surveyCode(nullable: true, blank: true, maxSize: 2500)
        originator(nullable: true, blank: true, maxSize: 2500)
        owner(nullable: true, blank: true,  maxSize: 2500)
        surveyStartDate(nullable: true, blank: true, maxSize: 2500)
        surveyEndDate(nullable: true, blank: true, maxSize: 2500)
        timeZone(nullable: true, blank: true, maxSize: 2500)
        spatialCRS(nullable: true, blank: true, maxSize: 2500)
        originalCRS(nullable: true, blank: true, maxSize: 2500)
        transformation(nullable: true, blank: true, maxSize: 2500)
        positionFix(nullable: true, blank: true, maxSize: 2500)
        horizontalAccuracy(nullable: true, blank: true, maxSize: 2500)
        depthCRS(nullable: true, blank: true, maxSize: 2500)
        verticalAccuracy(nullable: true, blank: true, maxSize: 2500)
        platformType(nullable: true, blank: true, maxSize: 2500)
        platformName(nullable: true, blank: true, maxSize: 2500)
        cruiseReportReference(nullable: true, blank: true, maxSize: 2500)
        confidentiality(nullable: true, blank: true, maxSize: 2500)

    }
}
