package prni


import grails.rest.*
import grails.converters.*

class XmlController {
	//static responseFormats =  ['xml']
	
    def index(String file) {
        response.setContentType("text/xml")
        String res = new File(request.getSession().getServletContext().getRealPath("meta") + "/" + file).getText()
        //res = res.substring(0, res.indexOf("</gmd:MD_Metadata>"))
        //res = res + "</gmd:MD_Metadata>"
        render res
    }
}
