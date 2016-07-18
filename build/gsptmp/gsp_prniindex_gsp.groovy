import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_prniindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(1)
invokeTag('link','asset',6,['rel':("icon"),'href':("favicon.ico"),'type':("image/x-ico")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
for( c in (prni.Country.listOrderByName()) ) {
printHtmlPart(6)
expressionOut.print(createLink(controller: 'metadata', action: 'listCountry', params: [countryCode: c.code]))
printHtmlPart(7)
expressionOut.print(c.name)
printHtmlPart(8)
}
printHtmlPart(9)
})
invokeTag('captureContent','sitemesh',24,['tag':("nav")],2)
printHtmlPart(10)
invokeTag('image','asset',29,['src':("spc.png"),'style':("height: 90px;")],-1)
printHtmlPart(11)
invokeTag('image','asset',30,['src':("linz.png"),'style':("height: 95px;")],-1)
printHtmlPart(11)
invokeTag('image','asset',31,['src':("iho.png"),'style':("height: 95px;width: 440px")],-1)
printHtmlPart(12)

int count = 0

printHtmlPart(13)
for( c in (prni.Country.listOrderByName()) ) {
printHtmlPart(14)
if(true && (count == 0)) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(createLink(controller: 'metadata', action: 'listCountry', params: [countryCode: c.code]))
printHtmlPart(17)
invokeTag('img','g',91,['dir':("images"),'file':("flags/${c.code}.jpg")],-1)
printHtmlPart(18)
expressionOut.print(c.name)
printHtmlPart(19)
expressionOut.print(prni.Metadata.findAllByCountry(c).size())
printHtmlPart(20)
if(true && (count > 3)) {
printHtmlPart(15)
}
printHtmlPart(14)

count = count + 1; if (count > 4) count = 0;

printHtmlPart(13)
}
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',107,['controller':("metadata"),'style':("text-decoration: none; font-weight: bolder;"),'type':("button"),'class':("btn btn-success")],2)
printHtmlPart(23)
for( c in (grailsApplication.controllerClasses.sort { it.fullName }) ) {
printHtmlPart(24)
createTagBody(3, {->
expressionOut.print(c.fullName)
})
invokeTag('link','g',126,['controller':(c.logicalPropertyName)],3)
printHtmlPart(25)
}
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',134,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468806582000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
