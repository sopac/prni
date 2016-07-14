package prni

class Country {

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
