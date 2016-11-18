<!doctype html>
<html>
<head>
    <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
    <meta name="layout" content="main">
    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>

<body>

<div style="margin-left: 60px; width: 80%">
    <h3>Searching <i><b><u>${header}</u></b></i>, ${total} results returned.</i></h3>

    <hr/>

    <ul class="list-group">
        <g:each in="${metadataList}" var="m">
            <li class="list-group-item">
                <g:link controller="metadata" action="show" id="${m.id}">${m.title}</g:link><br/>
                <i>${m.resourceType}, ${m.area}, ${m.year}</i>
            </li>
        </g:each>
    </ul>
</div>

</body>
</html>
