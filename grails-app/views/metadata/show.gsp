<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'metadata.label', default: 'Metadata')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-metadata" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>


<div id="show-metadata" class="content scaffold-show" role="main">
    <h1>${this.metadata.name}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div style="margin-left: 0px" align="center">
        <g:if test="${this.metadata.thumbnail != null}">
            <br/>
            <g:img dir="thumb" file="${this.metadata.thumbnail}"/>
        </g:if>
    </div>

    <f:display bean="metadata"/>



    <g:form resource="${this.metadata}" method="DELETE">
        <fieldset class="buttons">

            <g:if test="${!this.metadata.pacgeo.toString().startsWith("Pending")}">
                <a href="${this.metadata.pacgeo}" class="edit"
                   style="background-image: url('${resource(dir: 'images', file: 'skin/database_add.png')}') !important;">Open in PacGeo</a>
            </g:if>

            <g:link class="edit"
                    style="background-image: url('${resource(dir: 'images', file: 'skin/information.png')}') !important;"
                    controller="metadata" action="iso" params="[id: this.metadata.id]">ISO19139</g:link>

            <g:link class="edit" action="edit" resource="${this.metadata}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>


<h2 align="center">Metadata for ${this.metadata.name}</h2>
<div align="center" style="width: 100%;  white-space: pre-wrap; margin-top: -20px;">
<pre style="white-space: pre-wrap; width: 80%; text-align: left;">
    <%= new File(request.getSession().getServletContext().getRealPath("txt") + "/" + this.metadata.geonetwork + ".txt").getText() %>
</pre>
</div>

</body>
</html>
