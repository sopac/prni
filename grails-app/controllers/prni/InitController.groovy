package prni

import groovy.sql.*

class InitController {

    def index() {
        country()
        metadata()
        println "Finished."
        render "<h3>Init Complete.</h3>"
    }

    def metadata() {
        int masterCount = 0
        println "Populating Metadata from SQLite3 Db..."
        Metadata.list().each {
            it.delete()
        }
        def tableCountries = ["cook islands", "fiji"]
        Class.forName("org.sqlite.JDBC");
        def sql = Sql.newInstance("jdbc:sqlite:/home/sachin/Projects/NZ_MFAT_PRNI_Phase1_output.sqlite", "org.sqlite.JDBC")
        tableCountries.each { table ->
            println "Processing " + table
            int count = 0
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

            sql.eachRow("select * from '" + table + "'") { r ->
                try {
                    count++
                    masterCount++
                    Metadata m = new Metadata()
                    m.setName(r.Name_of_Information)
                    String tableCountry = table
                    if (table.equals("cook islands")) {
                        m.setCountry(Country.findByName("Cook Islands"))
                    }
                    if (table.equals("fiji")) {
                        m.setCountry(Country.findByName("Fiji Islands"))
                    }

                    //area
                    if (table.equals("cook islands")) {
                        if (r?.Island_Area != null && r?.Island_Area != "null") area = r?.Island_Area
                    } else {
                        if (r?.Area != null && r?.Area != "null") area = r?.Area
                    }

                    //description
                    if (table.equals("cook islands")) {
                        if (r?.Describtion != null && r?.Describtion != "null") description = r?.Describtion
                    } else {
                        if (r?.Subject != null && r?.Subject != "null") description = r?.Subject
                    }
                    //surveyType
                    if (r?.Type_of_Survey != null && r?.Type_of_Survey != "null") surveyType = r?.Type_of_Survey

                    //year
                    if (r?.Year != null && r?.year != "null") year = r?.Year

                    //format
                    if (r?.Format_of_Infor != null && r?.Format_of_Infor != "null") format = r?.Format_of_Infor

                    //projection
                    try {
                        if (r?.Projection != null && r?.Projection != "null") projection = r?.Projection
                    } catch (Exception ep) {
                    }

                    //project
                    try {
                        if (r?.Project != null && r?.Project != "null") project = r?.Project
                    } catch (Exception ep) {
                    }

                    //west
                    try {
                        if (r?.West_Bound_Longitude != null && r?.West_Bound_Longitude != "null") wl = r?.West_Bound_Longitude
                    } catch (Exception ep) {
                    }

                    //north
                    try {
                        if (r?.North_Bound_Latitude != null && r?.North_Bound_Latitude != "null") nl = r?.North_Bound_Latitude
                    } catch (Exception ep) {
                    }

                    //east
                    try {
                        if (r?.East_Bound_Latitude != null && r?.East_Bound_Latitude != "null") el = r?.East_Bound_Latitude
                    } catch (Exception ep) {
                    }

                    //south
                    try {
                        if (r?.South_Bound_Latitude != null && r?.South_Bound_Latitude != "null") sl = r?.South_Bound_Latitude
                    } catch (Exception ep) {
                    }

                    //println r.Geonetwork_File_identifier
                    m.setArea(area)
                    m.setYear(year)
                    m.setDescription(description)
                    m.setSurveyType(surveyType)
                    m.setGeonetwork(r.'Geonetwork_File_identifier ')
                    m.setFormat(format)
                    m.setProjection(projection)
                    m.setProject(project)
                    m.setWestBoundLongitude(wl)
                    m.setNorthBoundLatitude(nl)
                    m.setEastBoundLongitude(el)
                    m.setSouthBoundLatitude(sl)
                    m.setPacgeo("Pending...")

                    if (r.Name_of_Information != null)
                        m.save(flush: true, failOnError: true)
                } catch (Exception ex) {
                    println "Failed:" + table + ", Row: " + count.toString() + ", Error:" + ex.getMessage()
                }
            }

        }
        sql.close()
        render String.valueOf(Metadata.list().size()) + "/" + masterCount.toString() + " Metadata Imported."
    }

    def country() {
        if (Country.list().size() == 0) {
            println "Populating Country..."
            def countries = [:]
            countries.put("AS", "American Samoa")
            countries.put("CK", "Cook Islands")
            countries.put("AU", "Australia")
            countries.put("FR", "France")
            countries.put("ID", "Indonesia")
            countries.put("GU", "Guam")
            countries.put("NC", "New Caledonia")
            countries.put("PF", "French Polynesia")
            countries.put("TK", "Tokelau")
            countries.put("NZ", "New Zealand")
            countries.put("US", "United States")
            countries.put("WF", "Wallis and Fatuna")
            countries.put("FJ", "Fiji Islands")
            countries.put("KI", "Kiribati")
            countries.put("MH", "Marshall Islands")
            countries.put("NR", "Nauru")
            countries.put("NU", "Niue")
            countries.put("PG", "Papua New Guinea")
            countries.put("PW", "Palau")
            countries.put("SB", "Solomon Islands")
            countries.put("TV", "Tuvalu")
            countries.put("VU", "Vanuatu")

            countries.keySet().each { code ->
                Country c = new Country()
                c.setCode(code)
                c.setName(countries.get(code))
                c.save(flush: true, failOnError: true)
            }
        }
    }
}
