<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">

        <li>
            <g:form controller="search">
            <input type="text" name="query" class="form-control" placeholder="Search..."  style="width: 260px; margin-top: 8px;">
            </g:form>
        </li>

        <li>
            <a style="font-size: 100% !important;" href="${createLink(controller: 'account', action: 'show', id: session['aid'])}">${session['name']}</a>
        </li>

        <li>
        <a href="${createLink(controller: 'help')}" style="font-size: 100% !important;">
            <g:img align="left" style="height: 22px" dir="images" file="help.png"/>
            Help</a>
        </li>

    </content>

<div class="svg" role="presentation">
    <div class="grails-logo-container" style="height: 140px; background-color: #52bfd4;">
        <br/>
        <asset:image src="spc.png" style="height: 90px; margin-right: 20px;"/>
        <asset:image src="linz.png" style="height: 90px; margin-right: 20px;"/>
        <asset:image src="mfat.png" style="height: 80px;"/>

    </div>
</div>

<g:if test="${flash.message}">
    <div align="center" class="message" role="status">${flash.message}</div>
</g:if>



    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Pacific Regional Navigation Initiative</h1>

            <p style="text-align: justify">
                The Pacific Regional Navigation Initiative (PRNI) is a project funded by the New Zealand
                Ministry of Foreign Affairs and Trade (MFAT) that focuses on navigation related aspects of
                maritime safety, in particular those necessary to support Pacific Island Countries fulfil their
                obligations relating to hydrography and nautical charting under UN Safety of Life at Sea (SOLAS).
                The Pacific Community (SPC) has been engaged by NZ MFAT to work with targeted countries
                to, amongst other objectives, support hydrographic capability building initiatives in conjunction
                with work being doing by international bodies and development partners.
            </p>


            <div>

                <div align="center">
                <g:link controller="metadata" params="[countryCode: 'all']" style="text-decoration: none; font-weight: bolder;" type="button"
                        class="btn btn-success">Browse All Metadata</g:link>

                <g:link controller="metadata" params="[type:'Dataset']" style="text-decoration: none; font-weight: normal;" type="button"
                        class="btn btn-success">Browse Datasets</g:link>

                <g:link controller="metadata" params="[type:'Report']" style="text-decoration: none; font-weight: normal;" type="button"
                        class="btn btn-success">Browse Reports</g:link>

                <g:link controller="metadata" params="[type:'Chart']" style="text-decoration: none; font-weight: normal;" type="button"
                        class="btn btn-success">Browse Charts</g:link>
            </div>


                <table class="table">
                    <% int count = 0 %>
                    <g:each in="${prni.Country.listOrderByName()}" var="c">
                        <g:if test="${count == 0}">
                            <tr>
                        </g:if>

                        <td>
                            <a href="${createLink(controller: 'metadata', params: [countryCode: c.code])}"
                               style="text-decoration: none; color: #2e6da4; font-size: 120%;">
                                <g:img dir="images" file="flags/${c.code}.jpg"/>
                                <br/>${c.name} (${prni.Metadata.findAllByCountry(c).size()})
                            </a>
                        </td>

                        <g:if test="${count > 3}">
                            <tr>
                        </g:if>
                        <% count = count + 1; if (count > 4) count = 0; %>
                    </g:each>
                </table>
                <hr/>
                <br/>

                <div align="center">

                    <div class="well">
                        <u>For further information contact:</u>
                        <br/>
                        <b>David Mundy</b><br/>
                        Senior Hydrographic Surveyor<br/>
                        Geoscience Division, Pacific Community (SPC)<br/>
                        <a href="mailto:davidm@spc.int">davidm@spc.int</a>
                    </div>

                </div>
            </div>

        </section>
    </div>

</body>
</html>
