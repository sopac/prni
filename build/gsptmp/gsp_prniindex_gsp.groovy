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
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
for( c in (prni.Country.listOrderByName()) ) {
printHtmlPart(7)
expressionOut.print(c.name)
printHtmlPart(8)
}
printHtmlPart(9)
})
invokeTag('captureContent','sitemesh',23,['tag':("nav")],2)
printHtmlPart(10)
invokeTag('image','asset',28,['src':("spc.png"),'style':("height: 90px;")],-1)
printHtmlPart(11)
invokeTag('image','asset',29,['src':("linz.png"),'style':("height: 95px;")],-1)
printHtmlPart(11)
invokeTag('image','asset',30,['src':("iho.png"),'style':("height: 95px;width: 440px")],-1)
printHtmlPart(12)
for( c in (grailsApplication.controllerClasses.sort { it.fullName }) ) {
printHtmlPart(13)
createTagBody(3, {->
expressionOut.print(c.fullName)
})
invokeTag('link','g',80,['controller':(c.logicalPropertyName)],3)
printHtmlPart(14)
}
printHtmlPart(15)
})
invokeTag('captureBody','sitemesh',88,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468455798000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
