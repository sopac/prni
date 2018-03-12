package prni

class BootStrap {

    def init = { servletContext ->

        if (Country.list().size() == 0) {
            println "Populating Country..."
            def countries = [:]
            countries.put("CK", "Cook Islands")
            //countries.put("GU", "Guam")
            countries.put("NC", "New Caledonia")
            countries.put("FJ", "Fiji Islands")
            countries.put("FP", "French Polynesia")
            countries.put("KI", "Kiribati")
            countries.put("MH", "Marshall Islands")
            countries.put("NR", "Nauru")
            countries.put("NU", "Niue")
            countries.put("PG", "Papua New Guinea")
            //countries.put("PW", "Palau")
            countries.put("SB", "Solomon Islands")
            countries.put("TV", "Tuvalu")
            countries.put("TO", "Tonga")
            countries.put("VU", "Vanuatu")
            countries.put("FM", "Micronesia Fed. St.")
            countries.put("WS", "Samoa")
            //countries.put("XX", "Other")
            countries.keySet().sort().each { code ->
                Country c = new Country()
                c.setCode(code)
                c.setName(countries.get(code))
                c.save(flush: true, failOnError: true)
            }
        }


        if (Account.list().size() == 0){
            def names = ["Sachindra Singh", "Thierry Nervale", "Keleni Raqisia", "Salesh Kumar"]
            def emails = ["sachindras@spc.int", "thierryn@spc.int", "kelenir@spc.int", "saleshk@spc.int"]

            int index = 0
            for (String n : names){
                Account a = new Account()
                a.name = n
                a.email = emails[index]
                a.password = "prni2018!"
                a.organisation = "Pacific Community (SPC)"
                a.country = Country.findByCode("FJ")
                a.administrator = true
                a.save(failOnError: true, flush: true)
                index++
            }
        }
    }

    def destroy = {
    }
}
