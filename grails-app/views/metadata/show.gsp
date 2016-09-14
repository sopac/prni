<%@ page import="prni.Country; prni.Metadata; org.grails.core.DefaultGrailsDomainClass" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'metadata.label', default: 'Metadata')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css"/>

</head>

<body>
<a href="#show-metadata" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        %{--<li><g:link class="create" action="create"><g:message code="default.new.label"--}%
        %{--args="[entityName]"/></g:link></li>--}%
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

    <script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.15/proj4.js"></script>
    <script>

        //var crs = new L.Proj.CRS("EPSG:32704","+proj=utm +zone=4 +south +datum=WGS84 +units=m +no_defs");

        var mymap = L.map('mapid', {
            //crs: crs,
        });

        //n, e, s, w
        var n = ${this.metadata.northBoundLatitude};
        var e = ${this.metadata.eastBoundLongitude};
        var s = ${this.metadata.southBoundLatitude};
        var w = ${this.metadata.westBoundLongitude};


        //convert to UTM Zone 4S (WGS 84)
        var p1 = "+proj=utm +zone=4 +south +datum=WGS84 +units=m +no_defs";


        /*
         var tmp = proj4(p1, 'EPSG:3857', [n, e]);
         n = tmp[0];
         e = tmp[1];

         tmp = proj4(p1, 'EPSG:3857', [s, w]);
         s = tmp[0];
         w = tmp[1];
         */


        mymap.setView([n, e], 8);

        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
            maxZoom: 18,
            attribution: 'Bounding Box for ${this.metadata.name}',
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



    %{--<f:display bean="metadata"/>--}%


    <%
        def domainClass = new org.grails.core.DefaultGrailsDomainClass(prni.Metadata.class)
        def attr = ["name", "country", "area", "project", "year", "description", "surveyType", "format", "projection", "westBoundLongitude", "northBoundLatitude", "eastBoundLongitude", "southBoundLatitude", "geonetwork", "datasetSize", "pacgeo", "document", "thumbnail"]
    %>

    <ol class="property-list metadata">
    <g:each in="${attr}" var="a">
        <g:set var="val" value="${metadata[a]}"/>
        <g:set var="label" value="${domainClass.getPropertyByName(a).naturalName}"/>
        <g:if test="${val != null}">
            <g:if test="${!val.toString().trim().equals("")}">


                <li class="fieldcontain">
                    <span id="name-label" class="property-label">${label}</span>
                    <% if (val.toString().startsWith("http:")) { %>
                    <div class="property-value" aria-labelledby="name-label"><a href="${val}">${val.toString().replace("%3A", "/")}</a></div>
                    <% } else if (label.startsWith("Country"))  { %>
                    <div class="property-value" aria-labelledby="name-label"><a href="${createLink(controller: 'metadata', action: 'listCountry', params: [countryCode: prni.Country.findByName(val).code])}">${val}</a></div>
                    <% } else if (label.startsWith("Area"))  { %>
                    <div class="property-value" aria-labelledby="name-label"><a href="${createLink(controller: 'metadata', action: 'listArea', params: [area: val.toString()])}">${val}</a></div>
                    <% } else if (label.startsWith("Year"))  { %>
                    <div class="property-value" aria-labelledby="name-label"><a href="${createLink(controller: 'metadata', action: 'listYear', params: [year: val.toString()])}">${val}</a></div>
                    <% } else { %>
                    <div class="property-value" aria-labelledby="name-label">${val}</div>
                    <% } %>
                </li>
            </g:if>
        </g:if>
    </g:each>
    </ol>



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

<div align="center">
    <h2>Metadata for ${this.metadata.name}</h2>
    <button data-toggle="collapse" class="btn btn-primary" data-target="#md">Show Complete Metadata</button>
</div>

<div id="md" class="collapse" align="center" style="width: 100%;  white-space: pre-wrap; margin-top: -20px;">
    <pre class="well alert alert-info"
         style="white-space: pre-wrap; width: 80%; text-align: left; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace">
        <%=new File(request.getSession().getServletContext().getRealPath("txt") + "/" + this.metadata.geonetwork + ".txt").getText()%>
    </pre>
</div>

</body>
</html>
