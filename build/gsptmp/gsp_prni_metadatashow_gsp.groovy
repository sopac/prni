import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_prni_metadatashow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/metadata/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'metadata.label', default: 'Metadata'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',15,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',16,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',16,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',18,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',18,['class':("create"),'action':("create")],2)
printHtmlPart(9)
expressionOut.print(this.metadata.name)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (this.metadata.thumbnail != null)) {
printHtmlPart(14)
invokeTag('img','g',32,['dir':("thumb"),'file':(this.metadata.thumbnail)],-1)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('display','f',36,['bean':("metadata")],-1)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(17)
createClosureForHtmlPart(18, 3)
invokeTag('link','g',41,['class':("edit"),'style':("background-image: url('${resource(dir: 'images', file: 'skin/database_add.png')}') !important;"),'onclick':("return alert('Not Implemented.');")],3)
printHtmlPart(19)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',44,['class':("edit"),'style':("background-image: url('${resource(dir: 'images', file: 'skin/information.png')}') !important;"),'controller':("metadata"),'action':("iso"),'params':([id: this.metadata.id])],3)
printHtmlPart(21)
createTagBody(3, {->
invokeTag('message','g',47,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',47,['class':("edit"),'action':("edit"),'resource':(this.metadata)],3)
printHtmlPart(22)
expressionOut.print(message(code: 'default.button.delete.label', default: 'Delete'))
printHtmlPart(23)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'))
printHtmlPart(24)
})
invokeTag('form','g',52,['resource':(this.metadata),'method':("DELETE")],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',54,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1468817440000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
