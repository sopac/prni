package prni

class MedinDetailed {

    static searchable = true

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
        systemMountingPoint(nullable: true, blank: true, maxSize: 2500)
        systemDetails(nullable: true, blank: true, maxSize: 2500)
        processingOrganisation(nullable: true, blank: true, maxSize: 2500)
        acquisitionSoftware(nullable: true, blank: true, maxSize: 2500)
        acquisitionSoftwareVersion(nullable: true, blank: true, maxSize: 2500)
        processingSoftware(nullable: true, blank: true, maxSize: 2500)
        processingSoftwareVersion(nullable: true, blank: true, maxSize: 2500)
        systemFrequencyType(nullable: true, blank: true, maxSize: 2500)
        minMaxDepth(nullable: true, blank: true, maxSize: 2500)
        frequenciesUsed(nullable: true, blank: true, maxSize: 2500)
        calibrationDate(nullable: true, blank: true, maxSize: 2500)
        calibrationDetails(nullable: true, blank: true, maxSize: 2500)
        acquiredData(nullable: true, blank: true, maxSize: 2500)
        processedData(nullable: true, blank: true, maxSize: 2500)
        coverage(nullable: true, blank: true, maxSize: 2500)
        interpolation(nullable: true, blank: true, maxSize: 2500)
        gridSize(nullable: true, blank: true, maxSize: 2500)
        storageMedium(nullable: true, blank: true, maxSize: 2500)
        storageFormat(nullable: true, blank: true, maxSize: 2500)
        proceduresUsed(nullable: true, blank: true, maxSize: 2500)
        surveyNotes(nullable: true, blank: true, maxSize: 2500)
        processingNotes(nullable: true, blank: true, maxSize: 2500)
        processingQCNotes(nullable: true, blank: true, maxSize: 2500)
        qualityControlScheme(nullable: true, blank: true, maxSize: 2500)
        profiledOceanDataID(nullable: true, blank: true, maxSize: 2500)
        tideID(nullable: true, blank: true, maxSize: 2500)
        ancillaryID(nullable: true, blank: true, maxSize: 2500)

    }
}
