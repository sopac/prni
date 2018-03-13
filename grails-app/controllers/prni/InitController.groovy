package prni


import java.io.File
import groovy.sql.*
import org.apache.poi.ss.usermodel.*


class InitController {
    def index() {

        basic()
        validateMedinFiles()

        println "Finished."
        render "<h3>Init Complete.</h3>"
    }


    def processMedinDetailed(Metadata m, String file) {
        InputStream ins = new FileInputStream(file)
        Workbook wb = WorkbookFactory.create(ins)
        Sheet sheet = wb.getSheetAt(4)

        DataFormatter dataFormatter = new DataFormatter()

        MedinDetailed md = new MedinDetailed()
        md.metadata = m
        md.methodIdentifier = dataFormatter.formatCellValue(sheet.getRow(1).getCell(2)).trim()
        md.systemMountingPoint = dataFormatter.formatCellValue(sheet.getRow(2).getCell(2)).trim()
        md.systemDetails = dataFormatter.formatCellValue(sheet.getRow(3).getCell(2)).trim()
        md.processingOrganisation = dataFormatter.formatCellValue(sheet.getRow(4).getCell(2)).trim()
        md.acquisitionSoftware = dataFormatter.formatCellValue(sheet.getRow(5).getCell(2)).trim()
        md.acquisitionSoftwareVersion = dataFormatter.formatCellValue(sheet.getRow(6).getCell(2)).trim()
        md.processingSoftware = dataFormatter.formatCellValue(sheet.getRow(7).getCell(2)).trim()
        md.processingSoftwareVersion = dataFormatter.formatCellValue(sheet.getRow(8).getCell(2)).trim()
        md.systemFrequencyType = dataFormatter.formatCellValue(sheet.getRow(9).getCell(2)).trim()
        md.minMaxDepth = dataFormatter.formatCellValue(sheet.getRow(10).getCell(2)).trim()
        md.frequenciesUsed = dataFormatter.formatCellValue(sheet.getRow(11).getCell(2)).trim()
        md.calibrationDate = dataFormatter.formatCellValue(sheet.getRow(12).getCell(2)).trim()
        md.calibrationDetails = dataFormatter.formatCellValue(sheet.getRow(13).getCell(2)).trim()
        md.acquiredData = dataFormatter.formatCellValue(sheet.getRow(14).getCell(2)).trim()
        md.processedData = dataFormatter.formatCellValue(sheet.getRow(15).getCell(2)).trim()
        md.coverage = dataFormatter.formatCellValue(sheet.getRow(16).getCell(2)).trim()
        md.interpolation = dataFormatter.formatCellValue(sheet.getRow(17).getCell(2)).trim()
        md.gridSize = dataFormatter.formatCellValue(sheet.getRow(18).getCell(2)).trim()
        md.storageMedium = dataFormatter.formatCellValue(sheet.getRow(19).getCell(2)).trim()
        md.storageFormat = dataFormatter.formatCellValue(sheet.getRow(20).getCell(2)).trim()
        md.proceduresUsed = dataFormatter.formatCellValue(sheet.getRow(21).getCell(2)).trim()
        md.surveyNotes = dataFormatter.formatCellValue(sheet.getRow(22).getCell(2)).trim()
        md.processingNotes = dataFormatter.formatCellValue(sheet.getRow(23).getCell(2)).trim()
        md.processingQCNotes = dataFormatter.formatCellValue(sheet.getRow(24).getCell(2)).trim()
        md.qualityControlScheme = dataFormatter.formatCellValue(sheet.getRow(25).getCell(2)).trim()
        md.profiledOceanDataID = dataFormatter.formatCellValue(sheet.getRow(26).getCell(2)).trim()
        md.tideID = dataFormatter.formatCellValue(sheet.getRow(27).getCell(2)).trim()
        md.ancillaryID = dataFormatter.formatCellValue(sheet.getRow(28).getCell(2)).trim()

        //println md.systemMountingPoint

        md.save(flush: true, failOnError: true)

        ins.close()

    }


    def processMedinGeneral(Metadata m, String file) {
        InputStream ins = new FileInputStream(file)
        Workbook wb = WorkbookFactory.create(ins)
        Sheet sheet = wb.getSheetAt(2)

        DataFormatter dataFormatter = new DataFormatter()

        MedinGeneral mg = new MedinGeneral()
        mg.metadata = m
        mg.projectName = sheet.getRow(1).getCell(3).getRichStringCellValue().getString().trim()
        mg.projectCode = sheet.getRow(2).getCell(3).getRichStringCellValue().getString().trim()
        mg.projectStartDate = dataFormatter.formatCellValue(sheet.getRow(3).getCell(3)).trim()
        mg.projectEndDate = dataFormatter.formatCellValue(sheet.getRow(4).getCell(3)).trim()
        mg.projectWebsite = "http://www.pacgeo.org"
        //sheet.getRow(1).getCell(5).getRichStringCellValue().getString().trim()
        mg.surveyName = sheet.getRow(6).getCell(3).getRichStringCellValue().getString().trim()
        mg.surveyCode = sheet.getRow(7).getCell(3).getRichStringCellValue().getString().trim()
        mg.surveyAbstract = sheet.getRow(8).getCell(3).getRichStringCellValue().getString().trim()
        try {
            mg.surveyCode = sheet.getRow(9).getCell(3).getRichStringCellValue().getString().trim()
        } catch (Exception ex) {
        }
        mg.originator = sheet.getRow(10).getCell(3).getRichStringCellValue().getString().trim()
        mg.owner = sheet.getRow(11).getCell(3).getRichStringCellValue().getString().trim()
        mg.surveyStartDate = dataFormatter.formatCellValue(sheet.getRow(12).getCell(3)).trim()
        mg.surveyEndDate = dataFormatter.formatCellValue(sheet.getRow(13).getCell(3)).trim()
        mg.timeZone = sheet.getRow(14).getCell(3).getRichStringCellValue().getString().trim()
        mg.spatialCRS = sheet.getRow(15).getCell(3).getRichStringCellValue().getString().trim()
        mg.originalCRS = sheet.getRow(16).getCell(3).getRichStringCellValue().getString().trim()
        mg.transformation = sheet.getRow(17).getCell(3).getRichStringCellValue().getString().trim()
        mg.positionFix = sheet.getRow(18).getCell(3).getRichStringCellValue().getString().trim()
        try {
            mg.horizontalAccuracy = sheet.getRow(19).getCell(3).getRichStringCellValue().getString().trim()
        } catch (Exception ex) {
        }
        mg.depthCRS = sheet.getRow(20).getCell(3).getRichStringCellValue().getString().trim()
        mg.verticalAccuracy = sheet.getRow(21).getCell(3).getRichStringCellValue().getString().trim()
        mg.platformType = sheet.getRow(22).getCell(3).getRichStringCellValue().getString().trim()
        mg.platformName = sheet.getRow(23).getCell(3).getRichStringCellValue().getString().trim()
        mg.cruiseReportReference = sheet.getRow(24).getCell(3).getRichStringCellValue().getString().trim()
        mg.confidentiality = "Restricted." //sheet.getRow(25).getCell(3).getRichStringCellValue().getString().trim()

        mg.save(flush: true, failOnError: true)

        //metadata project
        m.project = mg.projectName
        m.projection = mg.spatialCRS
        if (m.description == null) m.description = mg.surveyAbstract
        if (m.description.equals("")) m.description = mg.surveyAbstract

        m.save(flush: true, failOnError: true)

        ins.close()

    }

    def validateMedinFiles() {
        int count = 0
        String path = "/home/sachin/tmp/PRNI_DATA/medin_all/"
        Metadata.list().each { m ->
            if (!m.document.toLowerCase().endsWith(".pdf") && m.document.contains(".")) {
                println(m.document)
                String doc = m.document.substring(0, m.document.indexOf("."))
                boolean exists = new File(path + doc + ".xls").exists()
                //render doc + " : " + exists + "<br/>"
                if (exists) count++
                if (exists) {
                    processMedinGeneral(m, path + doc + ".xls")
                    processMedinDetailed(m, path + doc + ".xls")
                }
            }
        }

        //cater for missing MEDIN datasets
        Metadata.list().each { m ->
            MedinGeneral mg = MedinGeneral.findByMetadata(m)
            if (mg == null){
                MedinGeneral mg1 = new MedinGeneral()
                mg1.metadata = m
                mg1.save(flush:true, failOnError:true)
            }
            MedinDetailed md = MedinDetailed.findByMetadata(m)
            if (md == null){
                MedinDetailed md1 = new MedinDetailed()
                md1.metadata = m
                md1.save(flush:true, failOnError:true)
            }
        }

        render "<h3>VALID: " + String.valueOf(count) + "</h3>"
    }


    def download() {
        Metadata.list().each { b ->
            render "wget http://geonetwork.sopac.org/geonetwork/srv/en/iso19139.xml?uuid=" + b.geonetwork + "<br/>"
        }
    }

    def basic() {
        int masterCount = 0
        println "Populating Metadata from SQLite3 Db..."
        //Metadata.list().each {
        //    it.delete()
        //}
        Class.forName("org.sqlite.JDBC")
        if (Metadata.list().size() == 0) {
            def tableCountries = ["cook_islands", "fiji", "fsm", "kiribati", "marshall_is", "nauru", "new_caledonia", "niue", "papua_new_guinea", "samoa", "solomon_is", "tonga", "tuvalu", "vanuatu", "french_polynesia"]
            Class.forName("org.sqlite.JDBC");
            def sql = Sql.newInstance("jdbc:sqlite://home/sachin/tmp/PRNI_DATA/out.sqlite", "org.sqlite.JDBC")
            tableCountries.each { table ->
                println "Processing " + table
                int count = 0
                String name = ""
                String area = ""
                String year = ""
                String description = ""
                String surveyType = ""
                String format = ""
                String projection = ""
                String project = ""
                String wl = ""
                String el = ""
                String nl = ""
                String sl = ""
                String document = ""

                sql.eachRow("select * from '" + table + "'") { r ->
                    try {
                        count++
                        masterCount++
                        Metadata m = new Metadata()
                        name = r.NameofInformation

                        String tableCountry = table
                        if (table.equals("cook_islands")) {
                            m.setCountry(Country.findByName("Cook Islands"))
                        }
                        if (table.equals("fiji")) {
                            m.setCountry(Country.findByName("Fiji Islands"))
                        }
                        if (table.equals("fsm")) {
                            m.setCountry(Country.findByCode("FM"))
                        }
                        if (table.equals("guam")) {
                            m.setCountry(Country.findByCode("GU"))
                            name = r.Area
                        }
                        if (table.equals("kiribati")) {
                            m.setCountry(Country.findByCode("KI"))
                        }
                        if (table.equals("marshall_is")) {
                            m.setCountry(Country.findByCode("MH"))
                        }
                        if (table.equals("nauru")) {
                            m.setCountry(Country.findByCode("NR"))
                        }
                        if (table.equals("new_caledonia")) {
                            m.setCountry(Country.findByCode("NC"))
                        }
                        if (table.equals("french_polynesia")) {
                            m.setCountry(Country.findByCode("FP"))
                        }
                        if (table.equals("niue")) {
                            m.setCountry(Country.findByCode("NU"))
                        }
                        if (table.equals("palau")) {
                            m.setCountry(Country.findByCode("PW"))
                        }
                        if (table.equals("papua_new_guinea")) {
                            m.setCountry(Country.findByCode("PG"))
                        }
                        if (table.equals("samoa")) {
                            m.setCountry(Country.findByCode("WS"))
                        }
                        if (table.equals("solomon_is")) {
                            m.setCountry(Country.findByCode("SB"))
                        }
                        if (table.equals("tonga")) {
                            m.setCountry(Country.findByCode("TO"))
                        }
                        if (table.equals("tuvalu")) {
                            m.setCountry(Country.findByCode("TV"))
                        }
                        if (table.equals("vanuatu")) {
                            m.setCountry(Country.findByCode("VU"))
                        }
                        if (table.equals("other")) {
                            m.setCountry(Country.findByCode("XX"))
                        }

                        m.setName(name)

                        //area
                        //if (table.equals("cook islands")) {
                        //    if (r?.Island_Area != null && r?.Island_Area != "null") area = r?.Island_Area
                        //} else {
                        if (r?.Area != null && r?.Area != "") area = r?.Area
                        //}

                        //description
                        //if (table.equals("cook islands")) {
                        //   if (r?.Describtion != null && r?.Describtion != "null") description = r?.Describtion
                        //} else {
                        if (r?.Subject != null && r?.Subject != "") description = r?.Subject
                        //}
                        //surveyType
                        if (r?.TypeofSurvey != null && r?.TypeofSurvey != "") surveyType = r?.TypeofSurvey

                        //year
                        if (r?.Year != null && r?.year != "") year = r?.Year

                        //format
                        if (r?.FormatofInfor != null && r?.FormatofInfor != "") format = r?.FormatofInfor

                        //projection
                        try {
                            if (r?.Projection != null && r?.Projection != "") projection = r?.Projection
                        } catch (Exception ep) {
                        }

                        //project
                        try {
                            if (r?.Project != null && r?.Project != "") project = r?.Project
                        } catch (Exception ep) {
                        }

                        //west
                        try {
                            if (r?.WestBoundLongitude != null && r?.WestBoundLongitude != "") wl = r?.WestBoundLongitude
                        } catch (Exception ep) {
                        }

                        //north
                        try {
                            if (r?.NorthBoundLatitude != null && r?.NorthBoundLatitude != "") nl = r?.NorthBoundLatitude
                        } catch (Exception ep) {
                        }

                        //east
                        try {
                            if (r?.EastBoundLongitude != null && r?.EastBoundLongitude != "") el = r?.EastBoundLongitude
                        } catch (Exception ep) {
                        }

                        //south
                        try {
                            if (r?.SouthBoundLatitude != null && r?.SouthBoundLatitude != "") sl = r?.SouthBoundLatitude
                        } catch (Exception ep) {
                        }

                        if (r?.file != null && r?.file != "") document = r?.file

                        //println r.Geonetwork_File_identifier
                        m.setArea(area)
                        m.setYear(year)
                        m.setDescription(description)
                        m.setSurveyType(surveyType)
                        String uuid = r?.'Geonetwork File identifier '
                        if (uuid != null && !uuid.equals("")) uuid = uuid.trim()
                        m.setGeonetwork(uuid)
                        m.setFormat(format)
                        m.setProjection(projection)
                        m.setProject(project)
                        m.setWestBoundLongitude(wl)
                        m.setNorthBoundLatitude(nl)
                        m.setEastBoundLongitude(el)
                        m.setSouthBoundLatitude(sl)
                        m.setDocument(document)

                        //pacgeo link
                        String pacgeo = "Pending..."
                        /*
                        if (table.equals("cook islands")) {
                            if (r?.PACGEO_Link != null && r?.PACGEO_Link != "") {
                                if (r?.PACGEO_Link.toString().startsWith("http")) {
                                    pacgeo = r?.PACGEO_Link.toString().trim()
                                }
                            }
                        }
                        */
                        m.setPacgeo(pacgeo)


                        if (!name.equals("")) {
                            m.save(flush: true, failOnError: true)
                        }
                    } catch (Exception ex) {
                        println "Failed:" + table + ", Row: " + count.toString() + ", Error:" + ex.getMessage()
                        //System.exit(-1)
                    }
                }

            }
            sql.close()

            //set thumbnails
            String path = request.getSession().getServletContext().getRealPath("/") + "/thumbs.txt"
            new File(path).eachLine { l ->
                String[] la = l.trim().split(":")
                String uuid = la[0].trim()
                String file = la[1].trim()
                //println uuid + " : " + file
                try {
                    def m = Metadata.findByGeonetwork(uuid)
                    //m.setThumbnail(file)
                    //m.save(flush: true, failOnError: true)
                } catch (Exception ex) {
                    println "Thumbnail Error: " + ex.getMessage()
                }
            }

            render String.valueOf(Metadata.list().size()) + "/" + masterCount.toString() + " Metadata Imported."


        }
    }


}
