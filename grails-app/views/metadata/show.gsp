<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'metadata.label', default: 'Metadata')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.1/dist/leaflet.css"/>
    <script src="https://unpkg.com/leaflet@1.0.1/dist/leaflet.js"></script>
</head>

<body>
<a href="#show-metadata" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="indexAll"><g:message code="default.list.label"
                                                              args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-metadata" class="content scaffold-show" role="main">
    <h1>${metadata.name.toUpperCase()}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>


    <g:if test="${metadata.westBoundLongitude != null && metadata.northBoundLatitude != null}">
        <div align="center">
            <div id="mapid" style="width: 420px; height: 320px"></div>
        </div>
    </g:if>

    <br/>

    <g:if test="${metadata.format != null}">

    <g:if test="${metadata.format.toLowerCase().startsWith("rep")}">
        <div align="center">
            <a target="_blank" class="btn btn-primary" style="text-decoration: none;" href="http://www.pacgeo.org/static/prnidoc/${metadata.document}">Download Report</a>
        </div>
        <br/>
    </g:if>

    <g:if test="${metadata.format.toLowerCase().startsWith("data")}">
        <div align="center">
            <a target="_blank" class="btn btn-primary" style="text-decoration: none;" href="${metadata.pacgeo}">Visualise and Access Data</a>
        </div>
        <br/>
    </g:if>

    </g:if>


    <hr/>

    <br/>

    <script>
        //var crs = new L.Proj.CRS("EPSG:32704","+proj=utm +zone=4 +south +datum=WGS84 +units=m +no_defs");
        var mymap = L.map('mapid', {
            //crs: crs,
        });
        //n, e, s, w
        var n = ${metadata.northBoundLatitude};
        var e = ${metadata.eastBoundLongitude};
        var s = ${metadata.southBoundLatitude};
        var w = ${metadata.westBoundLongitude};
        mymap.setView([n, e], 10);
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1Ijoic2FjaGluMTYxOCIsImEiOiJjamVvenVoZmYxaW5uMnhwYnJ3dDBuY3ljIn0.PteZFVffW0Lh8xlw3oixyQ', {
            maxZoom: 14,
            attribution: 'Bounding Box for ${metadata.name}',
            id: 'mapbox.streets'
        }).addTo(mymap);
        L.rectangle([
            [n, e],
            [s, w]
        ]).addTo(mymap).bindPopup("Bounding Box");
        var popup = L.popup();

        function onMapClick(e) {
            popup
                .setLatLng(e.latlng)
                .setContent("Current: " + e.latlng.toString())
                .openOn(mymap);
        }

        mymap.on('click', onMapClick);
    </script>




    <ul class="nav nav-tabs nav-justified">
        <li class="active"><a data-toggle="tab" href="#a1">Metadata</a></li>
        <li><a data-toggle="tab" href="#a2">MEDIN General</a></li>
        <li><a data-toggle="tab" href="#a3">MEDIN Detailed</a></li>
    </ul>

    <div class="tab-content">
        <div id="a1" class="tab-pane fade in active">
            <h3 align="center" style="background-color: lightgreen; padding: 5px;">Metadata</h3>
            <f:display bean="metadata"/>
        </div>

        <div id="a2" class="tab-pane fade">
            <h3 align="center" style="background-color: lightblue; padding: 5px;">MEDIN General</h3>
            <f:display bean="${medinGeneral}"/>
        </div>

        <div id="a3" class="tab-pane fade">
            <h3 align="center" style="background-color: lightcoral; padding: 5px;">MEDIN Detailed</h3>
            <f:display bean="${medinDetailed}"/>
        </div>
    </div>










    <g:form resource="${this.metadata}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.metadata}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
