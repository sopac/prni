package prni

class Country {

    static searchable = true

    String code
    String name

    static constraints = {
        String code
        String name
    }

    String toString(){
        return name
    }
}
