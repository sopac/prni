package prni

class MedinDetailed {

    Metadata metadata

    String methodIdentifier
    String systemMountingPoint
    String systemDetails
    String processingOrganisation
    String acquisitionSoftware
    String acquisitionSoftwareVersion
    String processingSoftware
    String processingSoftwareVersion
    String systemFrequencyType
    String minMaxDepth
    String frequenciesUsed
    String calibrationDate
    String calibrationDetails
    String acquiredData
    String processedData
    String coverage
    String interpolation
    String gridSize
    String storageMedium
    String storageFormat
    String proceduresUsed
    String surveyNotes
    String processingNotes
    String processingQCNotes
    String qualityControlScheme
    String profiledOceanDataID
    String tideID
    String ancillaryID


    static constraints = {

        metadata()

        methodIdentifier(nullable: true, blank: true)
        systemMountingPoint(nullable: true, blank: true)
        systemDetails(nullable: true, blank: true)
        processingOrganisation(nullable: true, blank: true)
        acquisitionSoftware(nullable: true, blank: true)
        acquisitionSoftwareVersion(nullable: true, blank: true)
        processingSoftware(nullable: true, blank: true)
        processingSoftwareVersion(nullable: true, blank: true)
        systemFrequencyType(nullable: true, blank: true)
        minMaxDepth(nullable: true, blank: true)
        frequenciesUsed(nullable: true, blank: true)
        calibrationDate(nullable: true, blank: true)
        calibrationDetails(nullable: true, blank: true)
        acquiredData(nullable: true, blank: true)
        processedData(nullable: true, blank: true)
        coverage(nullable: true, blank: true)
        interpolation(nullable: true, blank: true)
        gridSize(nullable: true, blank: true)
        storageMedium(nullable: true, blank: true)
        storageFormat(nullable: true, blank: true)
        proceduresUsed(nullable: true, blank: true)
        surveyNotes(nullable: true, blank: true)
        processingNotes(nullable: true, blank: true)
        processingQCNotes(nullable: true, blank: true)
        qualityControlScheme(nullable: true, blank: true)
        profiledOceanDataID(nullable: true, blank: true)
        tideID(nullable: true, blank: true)
        ancillaryID(nullable: true, blank: true)

    }
}
