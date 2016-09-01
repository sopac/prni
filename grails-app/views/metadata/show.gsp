<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'metadata.label', default: 'Metadata')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.0-rc.3/dist/leaflet.css" />


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


    <g:if test="${!this.metadata.northBoundLatitude.equals("")}">
<div align="center">
    <div id="mapid" style="width: 400px; height: 300px"></div>
</div>
    </g:if>

    <script src="https://unpkg.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
    <script>

        var mymap = L.map('mapid').setView([${this.metadata.northBoundLatitude}, ${this.metadata.eastBoundLongitude}], 8);

        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
            maxZoom: 18,
            attribution: 'Bounding Box for ${this.metadata.name}',
            id: 'mapbox.streets'
        }).addTo(mymap);



        L.rectangle([
            [${this.metadata.northBoundLatitude}, ${this.metadata.eastBoundLongitude}],
            [${this.metadata.southBoundLatitude}, ${this.metadata.westBoundLongitude}]
        ]).addTo(mymap).bindPopup("Bounding Box");


        var popup = L.popup();

        function onMapClick(e) {
            popup
                    .setLatLng(e.latlng)
                    .setContent("You clicked the map at " + e.latlng.toString())
                    .openOn(mymap);
        }

        mymap.on('click', onMapClick);

    </script>




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
