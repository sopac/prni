package prni

//medin - http://www.oceannet.org/finding_data/search/full/catalogue/dassh.ac.uk__MEDIN_2.3__909fac4039e6bd6f5516e0e815d3d642.xml

class Metadata {

    static searchable = true

    String Title
    String AlternativeResourceTitle
    String Abstract1
    String ResourceType
    String ResourceLocator
    String UniqueResourceIdentifier
    String CoupledResource
    String ResourceLanguage
    String TopicCategory
    String SpatialDataServiceType
    String Keywords
    String GeographicExtentWest
    String GeographicExtentSouth
    String GeographicExtentEast
    String GeographicExtentNorth
    String Extent
    String VerticalExtentInformation
    String SpatialReferenceSystem
    String TemporalReference
    String Lineage
    String SpatialResolution
    String AdditionalInformation
    String LimitationsOnPublicAccess
    String ConditionsForAccessUseConstraints
    String ResponsibleParty
    String DataFormat
    String FrequencyOfUpdate
  //String INSPIRE_Conformity
    String DateUpdateOfMetadata
    String MetadataStandardName
    String MetadataStandardVersion
    String MetadataLanguage

    //non-medin
    String area
    Country country
    String project
    String year
    String datasetName
    String supportingDocuments
    String thumbnail
    Basic basic


    static constraints = {
        title(nullable: false, blank: false)
        alternativeResourceTitle(nullable: true, blank: true)
        abstract1(nullable: true, blank: true)
        resourceType(nullable: true, blank: true)
        resourceLocator(nullable: true, blank: true)
        uniqueResourceIdentifier(nullable: true, blank: true)
        coupledResource(nullable: true, blank: true)
        resourceLanguage(nullable: true, blank: true)
        topicCategory(nullable: true, blank: true)
        spatialDataServiceType(nullable: true, blank: true)
        keywords(nullable: true, blank: true)
        geographicExtentWest(nullable: true, blank: true)
        geographicExtentSouth(nullable: true, blank: true)
        geographicExtentEast(nullable: true, blank: true)
        geographicExtentNorth(nullable: true, blank: true)
        extent(nullable: true, blank: true)
        verticalExtentInformation(nullable: true, blank: true)
        spatialReferenceSystem(nullable: true, blank: true)
        temporalReference(nullable: true, blank: true)
        lineage(nullable: true, blank: true)
        spatialResolution(nullable: true, blank: true)
        additionalInformation(nullable: true, blank: true)
        limitationsOnPublicAccess(nullable: true, blank: true)
        conditionsForAccessUseConstraints(nullable: true, blank: true)
        responsibleParty(nullable: true, blank: true)
        dataFormat(nullable: true, blank: true)
        frequencyOfUpdate(nullable: true, blank: true)
        //INSPIRE_Conformity(nullable: true, blank: true)
        dateUpdateOfMetadata(nullable: true, blank: true)
        metadataStandardName(nullable: true, blank: true)
        metadataStandardVersion(nullable: true, blank: true)
        metadataLanguage(nullable: true, blank: true)
        area(nullable: true, blank: true)
        country(nullable: true, blank: true)
        project(nullable: true, blank: true)
        year(nullable: true, blank: true)
        datasetName(nullable: true, blank: true)
        supportingDocuments(nullable: true, blank: true)
        thumbnail(nullable: true, blank: true)
        basic(nullable: true, blank: true)
    }
}
