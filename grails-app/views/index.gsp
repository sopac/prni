<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Pacific Regional Navigation Initiative</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>


</head>

<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Browse By Country <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each in="${prni.Country.listOrderByName()}" var="c">
                <li style="width: 240px"><a href="#">${c.name}</a></li>
            </g:each>
        </ul>
    </li>

</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container" style="height: 140px;">
        <br/>
        <asset:image src="spc.png" style="height: 90px;"/>
        <asset:image src="linz.png" style="height: 95px;"/>
        <asset:image src="iho.png" style="height: 95px;width: 440px"/>

    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Pacific Regional Navigation Initiative</h1>

        <p style="text-align: justify">
            Safe and reliable passage through Pacific waters is essential to protect fragile ocean environments and allow Pacific island countries' economies to develop. Up-to-date navigation charts based on modern, accurate surveys are critical transport infrastructure the ocean equivalent of well-constructed roads. Hydrography is highly specialised. Few Pacific countries have the technical capability or systems needed to undertake hydrographic surveys or update their own navigational charts.This new regional programme aims to ensure Pacific navigation charts meet international standards and support maritime safety and economic growth. The initiative contributes to sustainable development in selected Pacific SIDS through supporting:- improved transport and infrastructure management services as a key enabler of growth. For every $1 spent on hydrography, there is an estimated $91 return in ongoing economic activity.- safe, reliable and affordable transport services that connect people to markets and services. As of 1 July 2016 all international vessels will need to use Electronic Navigation Charts (ENCs) that meet international standards. This investment will ensure that vessels will continue to operate in the Pacific underpinning trade, tourism and economic development in the region. Specific outcomes sought include:- continued visits by passenger, tanker and cargo ships to PICs, resulting in better maritime transport safety and continued opportunities for economic development- improved domestic passenger ferry safety.
        </p>
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseTarget" aria-expanded="false" aria-controls="collapseTarget">
            More Information
        </button>

        <div class="collapse" id="collapseTarget">
            <h3><u>Implementation methodologies</u></h3>

            <p style="text-align: justify">
                This initiative builds on a successful project in Vanuatu under which hydrographic surveys were conducted of four key Vanuatu cruise ship destinations. This unique proof of concept partnership with SPC, the Government of Vanuatu, the International Hydrographic Organisation and LINZ has recently produced updated marine survey charts and Electronic Navigation Charts to permit compliance with IMO regulations, and allow the continuance of cruise ship visits to these islands.This new partnership will be delivered through Land Information New Zealand (LINZ) and the Geoscience for Development Programme at SPC-SOPAC, with an initial focus on Tonga, Cook Islands, Niue, Samoa, and Tokelau, with a view to extending to the rest of the Pacific.
            <br/>
            </p>

            <h3><u>Arrangements for Capacity-Building and Technology Transfer</u></h3>

            <p style="text-align: justify">
                The Pacific Regional Naviation Initiative will cover a number of activities across four broad components:<br/>1.) Risk Assessment Component this will involve identification of the need / priority areas for hydrographic surveys in Pacific island countries (PICs), in particular completing Hydrographic Risk Assessments for all Pacific Island Countries; and data discovery of existing hydrographic or bathymetric data that may be of sufficient quality to upgrade areas where the charts are old, poor, or of insufficient detail.<br/>2.) Capability Support Component - assistance and capacity building through regional technical support from Land Information New Zealand (LINZ), the Secretariat of the Pacific Community SOPAC division, the South West Pacific Hydrography Commission and the International Hydrographic Office. This will also involve working closely with Primary Charting Authorities to render/process survey data into hydrographic paper charts and Electronic Navigation Charts, and making these charts available to PIC governments and domestic shipping operators.<br/>3.) Mitigation Component - providing technical assistance for non-survey mitigation of risk areas (i.e. operational / other direct mitigation such as installation of navigational aids, identification of shipping lanes, and / or notice to mariners of hazardous areas), and monitoring and reporting to Primary Charting Authorities of changes to maritime conditions.<br/>4.) Survey Programme utilising the Hydrographic Risk Assessments to target those areas assessed as having significant or heightened risk. The hydrographic survey work will include New Zealand and Australia work through their naval Defence Forces to carry out this work while on Pacific operations.The survey programme will also include developing further collaborative partnerships and funding mechanisms involving donors and the private sector, provision of surveys and survey project management from private sector and regional agencies; and negotiating agreements with Primary Charting Authorities and PICs to utilise survey data to maintain and produce accurate and adequate Electronic Navigation Charts.
                <br/>
            </p>

            <h3><u>Coordination mechanisms/governance structure</u></h3>

            <p style="text-align: justify">
                This partnership will be supported by the New Zealand Aid Proramme and delivered through Land Information New Zealand (LINZ) and the Geoscience for Development Programme at SPC, with an initial focus on Tonga, Cook Islands, Niue, Samoa, and Tokelau, with a view to extending to the rest of the Pacific. A Steering Group will be established with LINZ, SPC and MFAT for governance of the initiative.Discussions are underway with other development partners and the private sector (cruise and other shipping company operators) on a potential consortium funding model to expand the scope of the partnership. Governance options for a consortium will be looked at as part of these discussions.
                <br/>
            </p>

            <h3><u>Partners</u></h3>

            <p style="text-align: justify">
                Land Information New Zealand (LINZ); Geoscience for Development Programme at the South Pacific Commission (SPC); South West Pacific Hydrography Commission, the International Hydrographic Office, New Zealand Ministry of Foreign Affairs and Trade; New Zealand and Australian naval Defence Forces; Governments of Tonga, Cook Islands, Niue, Samoa, and Tokelau initially, expanding to other Pacific nations. Other key stakeholders consulted in the development of this partnership are the World Bank (WB) and International Finance Corporation (IFC), Asia Development Bank (ADB), European Union (EU), European Investment Bank (EIB), , Japan International Cooperation Agency (JICA), Australian Department of Foreign Affairs and Trade (DFAT) and the Pacific Region Infrastructure Facility Coordination Office (PRIF PCO).
            </p>
        </div>

        <div id="controllers" role="navigation">
            <h2>Available Controllers:</h2>
            <ul>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                    </li>
                </g:each>
            </ul>
        </div>
    </section>
</div>

</body>
</html>
