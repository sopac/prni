package prni

class CleanController {

    def index() {
        resourceType()

        render "<h3>Cleaned</h3>"
    }


    def resourceType() {
        MetadataObselete.list().each { m ->
            String type = m.ResourceType.trim()
            //render type + "<br/>"
            if (type.equals("Map / Report")) type = "Report"
            if (type.equals("Brochure")) type = "Report"
            if (type.equals("Map")) type = "Chart"
            if (type.equals("Report/ All Charts")) type = "Chart"
            if (type.equals("Atlas & Report")) type = "Chart"
            if (type.equals("txt (notes)")) type = "Report"
            if (type.equals("Dataset / Report")) type = "Dataset"
            if (type.equals("Interpretation Map")) type = "Chart"
            if (type.equals("Report / Map")) type = "Report"
            if (type.equals("Dataset / Reports/ Tide Calendars")) type = "Report"
            if (type.equals("Catalogue")) type = "Report"
            if (type.equals("Dataset / Reports")) type = "Report"
            if (type.equals("(Dataset has no Latitutde and Longitude)")) type = "Dataset"
            if (type.equals("dataset")) type = "Dataset"
            if (type.equals("Dataset & Report")) type = "Dataset"
            if (type.equals("coloured")) type = "Chart"
            if (type.equals("Atlas / Reports")) type = "Chart"
            if (type.equals("Charts")) type = "Chart"
            if (type.equals("Contour Map")) type = "Chart"
            if (type.equals("Atlas & Reports")) type = "Report"
            if (type.equals("Report / Atlas")) type = "Report"
            if (type.equals("Panchromatic")) type = "Chart"
            if (type.equals("Coloured")) type = "Chart"
            if (type.equals("Panchromatic")) type = "Chart"
            if (type.equals("Original Color")) type = "Chart"
            if (type.equals("Maps")) type = "Chart"
            if (type.equals("Panchromatic ")) type = "Chart"

            type = type.trim()

            m.ResourceType = type

            m.save(flush:true, failOnError:true)


        }

    }
}
