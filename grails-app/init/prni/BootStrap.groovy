package prni

class BootStrap {

    def init = { servletContext ->
        if (Account.getAll().size() == 0){
            def names = ["Sachindra Singh", "David Mundy", "Keleni Raqisia", "Salesh Kumar"]
            def emails = ["sachindras@spc.int", "davidm@spc.int", "kelenir@spc.int", "saleshk@spc.int"]

            int index = 0
            for (String n : names){
                Account a = new Account()
                a.name = n
                a.email = emails[index]
                a.password = "prni2016!"
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
