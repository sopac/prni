

String path = "/home/sachin/Projects/prni/src/main/webapp/meta/27e2ff14-319f-4f08-8736-bc3e310e8222.xml"
def r = new XmlParser().parseText(new File(path).getText())

println r.breadthFirst()*.name().each { t ->
  tag = t.toString()
  tag = tag.substring(tag.indexOf("}") + 1, tag.size())
  //println tag
  //println "---------"
}

new File("/home/sachin/Projects/prni/src/main/webapp/meta/").listFiles().each {
  //println it
  println "/home/sachin/Lib/apache-sis/bin/sis metadata " + it.toString() + " > /home/sachin/Projects/prni/src/main/webapp/txt/" + it.getName().replace(".xml", ".txt")
}

println "Finished."


