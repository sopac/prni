package prni

class Account {

    String name
    String email
    String password
    String organisation
    Country country
    boolean administrator

    static constraints = {
        name()
        email(email: true)
        password()
        organisation()
        country()
        administrator()
    }
}
