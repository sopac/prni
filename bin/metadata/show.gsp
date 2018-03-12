<%@ page import="prni.MedinGeneral" %>
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
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-metadata" class="content scaffold-show" role="main">
    <h1>Metadata: ${this.metadata.title}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div align="center">
        <table style="width: 900px;" class="table-striped">
            <tr>
                <td>
                    <a href="#">What does this data set describe?</a><br/><br/>
                    <a href="#">Who produced the data set?</a><br/><br/>
                    <a href="#">Why was the data set created?</a><br/><br/>
                    <a href="#">How was the data set created?</a><br/><br/>
                    <a href="#">How reliable are the data?</a><br/><br/>
                    <a href="#">How can someone get a copy of the data set?</a><br/><br/>
                    <a href="#">Who wrote the metadata?</a><br/><br/>
                    <a href="#">How were these data collected?</a><br/>
                </td>
                <td>
                    <g:if test="${!this.metadata.geographicExtentNorth.equals("")}">
                        <div align="center">
                            <div id="mapid" style="width: 420px; height: 320px"></div>
                        </div>
                    </g:if>
                </td>
            </tr>
        </table>

        <a href="${this.metadata.resourceLocator}" style="text-decoration: none"
           class="btn btn-primary"><b>Open in PacGeo</b></a>
        <a href="#" style="text-decoration: none" class="btn btn-primary">Export MEDIN</a>
        <a href="#" style="text-decoration: none" class="btn btn-primary">Export ISO/19139</a>

    </div>
    <br/>






    <script>

        //var crs = new L.Proj.CRS("EPSG:32704","+proj=utm +zone=4 +south +datum=WGS84 +units=m +no_defs");

        var mymap = L.map('mapid', {
            //crs: crs,
        });

        //n, e, s, w
        var n = ${this.metadata.geographicExtentNorth};
        var e = ${this.metadata.geographicExtentEast};
        var s = ${this.metadata.geographicExtentSouth};
        var w = ${this.metadata.geographicExtentWest};


        mymap.setView([n, e], 10);

        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
            maxZoom: 18,
            attribution: 'Bounding Box for ${this.metadata.title}',
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

    <div style="margin-left: 140px">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#t1">Metadata</a></li>
            <li><a data-toggle="tab" href="#t2">Basic</a></li>
            <li><a data-toggle="tab" href="#t3">MEDIN General</a></li>
            <li><a data-toggle="tab" href="#t4">MEDIN Detailed</a></li>
        </ul>

        <div class="tab-content">
            <div id="t1" class="tab-pane fade in active">
                <h3>METADATA</h3>
                <f:display bean="metadata"/>
            </div>
            <div id="t2" class="tab-pane fade">
                <h3>BASIC</h3>
                <g:link controller="basic" action="edit" id="${metadata.basic.id}">Edit</g:link>
                <f:display bean="${metadata.basic}"/>
            </div>
            <div id="t3" class="tab-pane fade">
                <h3>MEDIN - GENERAL</h3>
                <g:link controller="medinGeneral" action="edit" id="${general.id}">Edit</g:link>
                <f:display bean="${general}"/>
            </div>
            <div id="t4" class="tab-pane fade">
                <h3>MEDIN - DETAILED</h3>
                <g:link controller="medinDetailed" action="edit" id="${detailed.id}">Edit</g:link>
                <f:display bean="${detailed}"/>
            </div>
        </div>

    </div>


   %{-- <f:display bean="metadata"/>--}%


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
