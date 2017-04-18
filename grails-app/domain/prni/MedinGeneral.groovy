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
        projectName(nullable: true, blank: true)
        projectCode(nullable: true, blank: true)
        projectStartDate(nullable: true, blank: true)
        projectEndDate(nullable: true, blank: true)
        projectWebsite(nullable: true, blank: true)
        surveyName(nullable: true, blank: true)
        surveyType(nullable: true, blank: true)
        surveyAbstract(nullable: true, blank: true)
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
        horizontalAccuracy(nullable: true, blank: true)
        depthCRS(nullable: true, blank: true)
        verticalAccuracy(nullable: true, blank: true)
        platformType(nullable: true, blank: true)
        platformName(nullable: true, blank: true)
        cruiseReportReference(nullable: true, blank: true)
        confidentiality(nullable: true, blank: true)


    }
}
