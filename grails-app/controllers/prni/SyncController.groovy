package prni

class SyncController {

    def index(){
        //sync metadata to medin
        MetadataObselete.list().each { m->
            def md = new MedinGeneral()
            md.projectName = m.getTitle()
            md.metadata = m
            md.save(failOnError:true, flush: true)

            md = new MedinDetailed()
            md.metadata = m
            md.save(failOnError:true, flush: true)
        }

        render "<hr3>Synced.</hr>"

    }
	
    def index_basic_metadata() {
        //sync basic domain and src/main/webapp/meta/*.xml
        render "Starting...</hr/>"
        String path = getServletContext().getRealPath("meta") + "/"
        MetadataObselete.list().each {
            it.delete()
        }
        Metadata.list().each { b ->
            MetadataObselete m = new MetadataObselete()
            //basic matches
            m.Title = b.name
            String alt = b.pacgeo.replace("http://www.pacgeo.org/layers/geonode%3A", "")
            if (alt.startsWith("Pending...")) alt = b.project
            m.AlternativeResourceTitle = alt
            String abstract_ = "" //+ purpose
            String purpose = ""
            m.ResourceType = b.format
            m.ResourceLocator = b.pacgeo.toString().replace("%3A", ":")
            String keywords= ""
            m.UniqueResourceIdentifier = b.geonetwork
            m.CoupledResource = b.project
            if (b.project.equals("")) m.CoupledResource = "None"
            m.ResourceLanguage = "English"
            String category = ""
            m.SpatialDataServiceType = b.surveyType //"Project; PRNI"
            m.GeographicExtentEast = b.eastBoundLongitude
            m.GeographicExtentNorth = b.northBoundLatitude
            m.GeographicExtentWest = b.westBoundLongitude
            m.GeographicExtentSouth = b.southBoundLatitude
            m.Extent = "Sea Areas: " + b.country.name
            m.VerticalExtentInformation = "Varied; Min/Max Pending"
            m.SpatialReferenceSystem = b.projection
            String temporalReference = ""
            String lineage = ""
            m.SpatialResolution = "Integer"

            m.LimitationsOnPublicAccess = "Limited. Commercial use will require legal consent from the owning member country of Pacific Community (SPC)."
            m.ConditionsForAccessUseConstraints = "Metadata freely available for personal and educational use. Dataset access use will require legal consent from the owning member country of Pacific Community (SPC)."
            m.ResponsibleParty = "Geoscience Division, Pacific Community (SPC).\r\nSuva, Fiji Islands.\r\ngeodatarequest@spc.int"
            m.DataFormat = b.format
            m.FrequencyOfUpdate = "Dataset is not updated and /or maintained"
            String dateUpdated = ""
            m.MetadataStandardName = "MEDIN Discovery Metadata Standard"
            m.MetadataStandardVersion = "2.3.8"
            m.MetadataLanguage = "English"
            m.country = b.country
            m.area = b.area
            m.year = b.year
            m.project = b.project
            m.datasetName = b.pacgeo.replace("http://www.pacgeo.org/layers/geonode%3A", "").trim()
            m.supportingDocuments = b.document
            m.thumbnail = b.thumbnail
            m.basic = b

            //xml matches
            if (b.geonetwork != null){
                File file = new File(path + b.geonetwork.trim() + ".xml")
                if (file.exists()) {
                    String res = file.getText()
                    //res = res.substring(0, res.indexOf("</gmd:MD_Metadata>"))
                    //res = res + "</gmd:MD_Metadata>"
                    def xml = new XmlParser().parseText(res)
                    abstract_ = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:abstract'.'gco:CharacterString'.text()
                    keywords = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:descriptiveKeywords'.'gmd:MD_Keywords'.'gmd:keyword'.'gco:CharacterString'.text()
                    category = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:topicCategory'.'gmd:MD_TopicCategoryCode'.text()
                    temporalReference = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:extent'.'gmd:EX_Extent'.'gmd:temporalElement'.'gmd:EX_TemporalExtent'.'gmd:extent'.'gml:TimePeriod'.text()
                    lineage = xml.'gmd:dataQualityInfo'.'gmd:DQ_DataQuality'.'gmd:lineage'.text()
                    dateUpdated = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:citation'.'gmd:CI_Citation'.'gmd:date'.text()
                    purpose = xml.'gmd:identificationInfo'.'gmd:MD_DataIdentification'.'gmd:purpose'.'gco:CharacterString'.text()
                } else {
                    render "File does not exist : " + file.toString() + "<br/>"
                }
            } else {
                render "Metadata XML does not exist.<br/>"
            }
            if (keywords.equals("")){
                keywords= "Marine, Bathymetric, Dataset"
            }
            if (category.equals("")){
                category = "elevation; bathymetry"
            }
            if (abstract_.equals("")) abstract_ = b.description
            if (dateUpdated.equals("")) dateUpdated = new Date().toString()
            if (lineage.equals("")) lineage = b.project
            if (temporalReference.equals("")) temporalReference = "Unknown; Single Survey"
            if (lineage.equals("")) lineage = "None"

            m.AdditionalInformation = b.description  + purpose
            abstract_ = abstract_.trim() + " " + purpose


            m.Abstract1 = abstract_.trim()
            m.Keywords = keywords
            m.TopicCategory = category
            m.TemporalReference = temporalReference
            m.Lineage = lineage
            m.DateUpdateOfMetadata = dateUpdated


            //render abstract_ + "<br/>"

            m.save(failOnError: true, flush: true)
        }

        render "<h3>Finished</h3>"


    }
}
