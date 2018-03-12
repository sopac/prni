package prni

class MedinGeneral {

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
        projectCode(nullable: true, blank: true)
        projectStartDate(nullable: true, blank: true)
        projectEndDate(nullable: true, blank: true)
        projectWebsite(nullable: true, blank: true)
        surveyName(nullable: true, blank: true, maxSize: 2500)
        surveyType(nullable: true, blank: true)
        surveyAbstract(nullable: true, blank: true, maxSize: 2500)
        surveyCode(nullable: true, blank: true)
        originator(nullable: true, blank: true)
        owner(nullable: true, blank: true)
        surveyStartDate(nullable: true, blank: true)
        surveyEndDate(nullable: true, blank: true)
        timeZone(nullable: true, blank: true)
        spatialCRS(nullable: true, blank: true)
        originalCRS(nullable: true, blank: true)
        transformation(nullable: true, blank: true)
        positionFix(nullable: true, blank: true)
        horizontalAccuracy(nullable: true, blank: true, maxSize: 2500)
        depthCRS(nullable: true, blank: true, maxSize: 2500)
        verticalAccuracy(nullable: true, blank: true, maxSize: 2500)
        platformType(nullable: true, blank: true)
        platformName(nullable: true, blank: true)
        cruiseReportReference(nullable: true, blank: true, maxSize: 2500)
        confidentiality(nullable: true, blank: true, maxSize: 2500)

    }
}
