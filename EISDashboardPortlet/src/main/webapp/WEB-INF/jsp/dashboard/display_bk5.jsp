<%--
  User: imake
  Date: 20/11/2015
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:set var="ns"><portlet:namespace/></c:set>
<html>
<head>
    <%-- --%>
    <link rel="stylesheet"
          href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
    <link rel="stylesheet"
          href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" type="text/css"/>
    <link rel="stylesheet"
          href="<c:url value="/resources/font-awesome/weloveiconfonts.css"/>" type="text/css"/>
    <%-- --%>
    <link rel="stylesheet"
          href="<c:url value="/resources/css/stycss.css"/>" type="text/css"/>


</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span8">
            <div class="row">
                <div class="span6 pad">
                    <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_1.png" width="44" height="40"> <span class="txtHead">Student</span> </div>
                    <div class="orangeText textR" name="noof_format" id="noof_format"> 17,477 </div>
                    <div class="blackText"> <span name="percent_undergraduate" id="percent_undergraduate">66%</span> </div>
                    <div class="smlBlue"> <span id="no_of_undergraduate_format">11,578</span> Undergraduate Students </div>
                    <div class="blackText"> <span name="percent_graduate" id="percent_graduate">34%</span> </div>
                    <div class="smlOrange"> <span id="no_of_graduate_format">5,899</span> Graduate Students </div>
                    <div class="stdBox">
                        <div class="row">
                            <div class="span6">
                                <div class="Box textC"> <img src="/EISDashboardPortlet/resources/images/ICON_2.png" width="50" height="70">
                                    <p class="orangeText" name="percent_male" id="percent_male">56%</p>
                                </div>
                            </div>
                            <div class="span6">
                                <div class="Box textC"> <img src="/EISDashboardPortlet/resources/images/ICON_3.png" width="50" height="70">
                                    <p class="blueText" name="percent_female" id="percent_female">34%</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="blue-bg whiteText textC"> <span id="no_of_student">22</span>: <span id="faculty_ratio">1</span> Student/Faculty Ratio </div>
                </div>
                <div class="span6">
                    <div class="row">
                        <div class="span12 pad">
                            <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_8.png" width="55" height="38"> <span class="txtHead">Academic</span> </div>
                            <div class="stdBox">
                                <div class="row">
                                    <div class="span12">
                                        <div class="span1 offset2">
                                            <div class="Box textC"> <img src="/EISDashboardPortlet/resources/images/ICON_9.png" style="margin-top: 0.2em;" width="45" height="45"> </div>
                                        </div>
                                        <div class="span3">
                                            <div class="Box orangeText textC" id="faculties_schools"> 11 </div>
                                        </div>
                                        <div class="span1">
                                            <div class="Box orangeText textC"> + </div>
                                        </div>
                                        <div class="span3">
                                            <div class="Box orangeText textC" id="institute"> 1 </div>
                                        </div>
                                    </div>
                                    <div class="span12">
                                        <div class="span5 offset1 grayText textC"> Faculties / Schools </div>
                                        <div class="span3 offset1 grayText textC"> Institute </div>
                                    </div>
                                    <div class="span12 smlBlue textR">
                                        <p class="smlBlue textR">(for Special Study Robotics)</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span12 pad">
                            <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_10.png" width="38" height="38"> <span class="txtHead">Programs</span> </div>
                            <div style="position:relative;margin: 3em 2% 7em 2%">
                                <!-- *** top absolute on progress *** --><img class="orangeLine" src="/EISDashboardPortlet/resources/images/orangeLine.png">
                                <div class="orangeText" style="position: absolute; top:-1.1em; left:30%" id="programs_for_undergraduate">46</div>
                                <div class="smlOrange" style="position: absolute; top:-2.5em; left:45%">Programs for
                                    <br>Undergraduate</div>
                                <!--end-->
                                <!-- *** bottom absolute on progress *** --><img class="blueLine" src="/EISDashboardPortlet/resources/images/blueLine.png">
                                <div class="blueText" style="position: absolute; top:2.6em; left:45%" id="programs_for_graduate">101</div>
                                <div class="smlBlue" style="position: absolute; top:6.7em; left:60%">Programs for
                                    <br>graduate</div>
                                <!--end-->
                                <div class="progress" style="position:relative;top:2em;">
                                    <div class="bar orangeProg" id="researcher_doctoral_percent_progress" style="width: 30%;"></div>
                                    <div class="bar blueProg" id="programs_for_graduate_progress" style="width: 70%;"></div>
                                </div>
                            </div>
                            <div class="span12">
                                <div class="midleOrange textR">International Program</div>
                                <div class="row grayText">
                                    <div class="span1 offset2" style="font-size: 2em;" id="international_program">30</div>
                                    <div class="span1 offset1" style="font-size: 2em;">}</div>
                                    <div class="span7"> <span id="international_program_for_undergraduate">10</span> for Undergraduate
                                        <br> <span id="international_program_for_graduate">20</span> for Graduate</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="span12 pad">
                    <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_4.png" width="38" height="38"> <span class="txtHead">Employees</span> </div>
                    <div class="row textC pad_1" style="position: relative;">
                        <div class="span2"><img src="/EISDashboardPortlet/resources/images/ICON_5.png" width="109" height="80"></div>
                        <div class="span3">
                            <p class="orangeText" id="faculty_member_doctor_format">740</p>
                            <p class="grayText">Faculty Members</p>
                            <p class="smlBlue">(<span id="percent_faculty_member_doctor">66</span>% Doctorate Degrees)</p>
                        </div>
                        <div class="span6 offset1">
                            <!-- absolute line --><img src="/EISDashboardPortlet/resources/images/line.png" style="position: absolute;top: 18%;left: 36%;">
                            <div class="progress" style="position:relative;top:2em;">
                                <div class="bar grayProg" style="width: 4%;" id="factory_member_bachelor_lower_percent">4%</div>
                                <div class="bar orangWProg" style="width: 30%;" id="factory_member_master_percent">30%</div>
                                <div class="bar orangeProg" style="width: 66%;" id="factory_member_doctoral_percent">66%</div>
                            </div>
                        </div>
                    </div>
                    <div class="row textC pad_1" style="position: relative;">
                        <div class="span2"><img src="/EISDashboardPortlet/resources/images/ICON_6.png" width="80" height="80"></div>
                        <div class="span3">
                            <p class="orangeText" id="faculty_member_format">139</p>
                            <p class="grayText" style="font-size: 1.5em;">Researchers</p>
                        </div>
                        <div class="span6 offset1">
                            <!-- absolute line --><img src="/EISDashboardPortlet/resources/images/line.png" style="position: absolute;top: 30%;left: 36%;">
                            <div class="progress" style="position:relative;top:2em;">
                                <div class="bar grayProg" style="width: 6%;" id="researcher_bachelor_lower_percent">6%</div>
                                <div class="bar orangWProg" style="width: 53%;" id="researcher_master_percent">53%</div>
                                <div class="bar orangeProg" style="width: 41%;" id="researcher_doctoral_percent">41%</div>
                            </div>
                        </div>
                    </div>
                    <div class="row textC pad_1" style="position: relative;">
                        <div class="span2"><img src="/EISDashboardPortlet/resources/images/ICON_7.png" width="80" height="80"></div>
                        <div class="span3">
                            <p class="orangeText" id="full_time_format">1,386</p>
                            <p class="grayText">Full-time</p>
                            <p class="grayText">Support Staff</p>
                        </div>
                        <!-- absolute line -->
                        <div class="span6 offset1"> <img src="/EISDashboardPortlet/resources/images/line.png" style="position: absolute;top: 25%;left: 36%;">
                            <div class="progress" style="position:relative;top:2em;">
                                <div class="bar grayProg" style="width: 71%;" id="full_time_bachelor_lower_percent">71%</div>
                                <div class="bar orangWProg" style="width: 28%;" id="full_time_master_percent">28%</div>
                                <div class="bar orangeProg" style="width: 1%;" id="full_time_doctoral_percent">1%</div>
                            </div>
                        </div>
                        <div class="legend-inline">
                            <div class="legend-box">
                                <div class="legend" style="background-color: #a6a6a6;"></div><span class="grayText">Bachelor's &amp; Lower</span> </div>
                            <div class="legend-box">
                                <div class="legend" style="background-color: #ffbf93;"></div><span class="grayText">Master's</span> </div>
                            <div class="legend-box">
                                <div class="legend" style="background-color: #ff8837;"></div><span class="grayText">Doctoral</span> </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="span4">
            <div class="row">
                <div class="span12 pad">
                    <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_11.png" width="38" height="38"> <span class="txtHead">Budget</span> </div>
                    <div class="smlBlue midle">Fiscal Year 2014</div>
                    <div>
                        <div id="_EISDashboardExecutiveSummary_WAR_EISDashboardPortlet_chartContainer1"><span class="fusioncharts-container" style="position: relative; text-align: left; line-height: normal; display: inline-block; font-weight: normal; font-variant: normal; font-style: normal; text-decoration: none; padding: 0px; margin: 0px; border: medium none; direction: ltr; width: 100%; height: 300px;" id="chartobject-1"><svg id="raphael-paper-100" style="overflow: hidden; -moz-user-select: none; cursor: default; position: relative; left: -0.73333px; top: 0.316666px; background-color: rgb(255, 255, 255);" xmlns="http://www.w3.org/2000/svg" width="356" version="1.1" height="300"><desc>Doughnut Chart</desc><defs><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="100-xr___97.25_178_134.5__rgba_255_190_1_1__50-rgba_255_190_1_1__75"><stop stop-opacity="1" stop-color="#ffbe01" offset="50%"></stop><stop stop-opacity="1" stop-color="#ffbe01" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="100-xr___97.25_178_134.5__rgba_249_213_189_1__50-rgba_249_213_189_1__75"><stop stop-opacity="1" stop-color="#f9d5bd" offset="50%"></stop><stop stop-opacity="1" stop-color="#f9d5bd" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="100-xr___97.25_178_134.5__rgba_166_166_166_1__50-rgba_166_166_166_1__75"><stop stop-opacity="1" stop-color="#a6a6a6" offset="50%"></stop><stop stop-opacity="1" stop-color="#a6a6a6" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="100-xr___97.25_178_134.5__rgba_155_187_88_1__50-rgba_155_187_88_1__75"><stop stop-opacity="1" stop-color="#9bbb58" offset="50%"></stop><stop stop-opacity="1" stop-color="#9bbb58" offset="75%"></stop></radialGradient></defs><g class="raphael-group-101-background"><rect ry="0" rx="0" fill="#ffffff" fill-opacity="0.5" stroke="none" height="300" width="356" y="0" x="0" style="stroke: none; fill-opacity: 0.5; fill: rgb(255, 255, 255);"></rect><rect ry="0" rx="0" fill="none" stroke-dasharray="none" stroke-width="0" stroke-opacity="0.5" stroke="#767575" height="300" width="356" y="0" x="0" style="stroke: rgb(118, 117, 117); stroke-opacity: 0.5; fill: none;"></rect></g><g class="raphael-group-102-dataset"><g></g><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#ffefc0" fill="url('#100-xr___97.25_178_134.5__rgba_255_190_1_1__50-rgba_255_190_1_1__75')" d="M121.8761456719758,55.07912443590966A97.25,97.25,0,0,1,240.51109504201594,208.99782209332062L209.25554752100797,171.7489110466603A48.625,48.625,0,0,0,149.9380728359879,94.78956221795482Z" style="stroke: rgb(255, 239, 192); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#fef5ef" fill="url('#100-xr___97.25_178_134.5__rgba_249_213_189_1__50-rgba_249_213_189_1__75')" d="M98.54601497870846,190.57697178197463A97.25,97.25,0,0,1,121.8761456719758,55.07912443590966L149.9380728359879,94.78956221795482A48.625,48.625,0,0,0,138.27300748935423,162.53848589098732Z" style="stroke: rgb(254, 245, 239); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#e9e9e9" fill="url('#100-xr___97.25_178_134.5__rgba_166_166_166_1__50-rgba_166_166_166_1__75')" d="M194.5737483290448,230.32731012777873A97.25,97.25,0,0,1,98.54601497870846,190.57697178197463L138.27300748935423,162.53848589098732A48.625,48.625,0,0,0,186.2868741645224,182.41365506388937Z" style="stroke: rgb(233, 233, 233); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#e6eed6" fill="url('#100-xr___97.25_178_134.5__rgba_155_187_88_1__50-rgba_155_187_88_1__75')" d="M240.511095042016,208.99782209332056A97.25,97.25,0,0,1,194.5737483290448,230.32731012777873L186.2868741645224,182.41365506388937A48.625,48.625,0,0,0,209.255547521008,171.74891104666028Z" style="stroke: rgb(230, 238, 214); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><text fill-opacity="1" font-style="" font-weight="bold" font-size="10px" font-family="Verdana,sans" text-anchor="middle" fill="#555555" stroke="none" y="134.5" x="178" style="stroke: none; fill: rgb(85, 85, 85); text-anchor: middle; font-family: Verdana,sans; font-size: 10px; font-weight: bold; fill-opacity: 1;"><tspan x="178" dy="-2.791667938232422">Total revenue:</tspan><tspan x="178" dy="12">$64.08K</tspan></text></g><g style="" transform="matrix(1,0,0,1,60.5,260.5)" class="fusioncharts-legend"><rect fill="#ffffff" fill-opacity="1" stroke-width="1" stroke-opacity="0" stroke="#545454" ry="0" rx="0" height="25" width="235" y="0" x="0" style="stroke: rgb(84, 84, 84); stroke-opacity: 0; fill-opacity: 1; fill: rgb(255, 255, 255);"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="23" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="23" dy="9.208333969116211">Food</tspan></text><path fill="#ffbe01" fill-opacity="1" stroke="#ffd867" stroke-width="0.5" d="M13.5,11.5L13.5,7.55A3.95,3.95,0,0,1,17.45,11.5ZM11.5,13.5L11.5,8.55A4.95,4.95,0,1,0,16.45,13.5Z" style="stroke: rgb(255, 216, 103); fill-opacity: 1; fill: rgb(255, 190, 1); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="37" y="4" x="4" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="70.85" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="70.85" dy="9.208333969116211">Apparels</tspan></text><path fill="#f9d5bd" fill-opacity="1" stroke="#fbe6d7" stroke-width="0.5" d="M61.35,11.5L61.35,7.55A3.95,3.95,0,0,1,65.3,11.5ZM59.35,13.5L59.35,8.55A4.95,4.95,0,1,0,64.3,13.5Z" style="stroke: rgb(251, 230, 215); fill-opacity: 1; fill: rgb(249, 213, 189); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="47" y="4" x="51.85" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="128.7" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="128.7" dy="9.208333969116211">Electronics</tspan></text><path fill="#a6a6a6" fill-opacity="1" stroke="#cacaca" stroke-width="0.5" d="M119.2,11.5L119.2,7.55A3.95,3.95,0,0,1,123.15,11.5ZM117.2,13.5L117.2,8.55A4.95,4.95,0,1,0,122.15,13.5Z" style="stroke: rgb(202, 202, 202); fill-opacity: 1; fill: rgb(166, 166, 166); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="55" y="4" x="109.7" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="194.55" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="194.55" dy="9.208333969116211">Household</tspan></text><path fill="#9bbb58" fill-opacity="1" stroke="#c3d69b" stroke-width="0.5" d="M185.05,11.5L185.05,7.55A3.95,3.95,0,0,1,189,11.5ZM183.05,13.5L183.05,8.55A4.95,4.95,0,1,0,188,13.5Z" style="stroke: rgb(195, 214, 155); fill-opacity: 1; fill: rgb(155, 187, 88); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="53" y="4" x="175.55" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect></g><g class="raphael-group-103-hot"></g><g class="raphael-group-106-buttons"></g></svg></span></div>
                        <!-- legend in pie -->
                        <div class="typeChart whiteText" style="top: 32%;left: 85%;">INCOMES</div>
                    </div>
                    <div class="smlBlue midle textC">Incomes <span id="income_amount_format"></span> Baht</div>
                    <div>
                        <div id="_EISDashboardExecutiveSummary_WAR_EISDashboardPortlet_chartContainer2"><span class="fusioncharts-container" style="position: relative; text-align: left; line-height: normal; display: inline-block; font-weight: normal; font-variant: normal; font-style: normal; text-decoration: none; padding: 0px; margin: 0px; border: medium none; direction: ltr; width: 100%; min-width: 100px;  height: 300px;" id="chartobject-2"><svg id="raphael-paper-46" style="overflow: hidden; -moz-user-select: none; cursor: default; position: relative; left: -0.73333px; top: -0.733276px; background-color: rgb(255, 255, 255);" xmlns="http://www.w3.org/2000/svg" width="356" version="1.1" height="300"><desc>Doughnut Chart</desc><defs><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="46-xr___97.25_178_134.5__rgba_255_190_1_1__50-rgba_255_190_1_1__75"><stop stop-opacity="1" stop-color="#ffbe01" offset="50%"></stop><stop stop-opacity="1" stop-color="#ffbe01" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="46-xr___97.25_178_134.5__rgba_249_213_189_1__50-rgba_249_213_189_1__75"><stop stop-opacity="1" stop-color="#f9d5bd" offset="50%"></stop><stop stop-opacity="1" stop-color="#f9d5bd" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="46-xr___97.25_178_134.5__rgba_166_166_166_1__50-rgba_166_166_166_1__75"><stop stop-opacity="1" stop-color="#a6a6a6" offset="50%"></stop><stop stop-opacity="1" stop-color="#a6a6a6" offset="75%"></stop></radialGradient><radialGradient fy="134.5" fx="178" cy="134.5" cx="178" r="97.25" id="46-xr___97.25_178_134.5__rgba_155_187_88_1__50-rgba_155_187_88_1__75"><stop stop-opacity="1" stop-color="#9bbb58" offset="50%"></stop><stop stop-opacity="1" stop-color="#9bbb58" offset="75%"></stop></radialGradient></defs><g class="raphael-group-47-background"><rect ry="0" rx="0" fill="#ffffff" fill-opacity="0.5" stroke="none" height="300" width="356" y="0" x="0" style="stroke: none; fill-opacity: 0.5; fill: rgb(255, 255, 255);"></rect><rect ry="0" rx="0" fill="none" stroke-dasharray="none" stroke-width="0" stroke-opacity="0.5" stroke="#767575" height="300" width="356" y="0" x="0" style="stroke: rgb(118, 117, 117); stroke-opacity: 0.5; fill: none;"></rect></g><g class="raphael-group-48-dataset"><g></g><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#ffefc0" fill="url('#46-xr___97.25_178_134.5__rgba_255_190_1_1__50-rgba_255_190_1_1__75')" d="M121.8761456719758,55.07912443590966A97.25,97.25,0,0,1,240.51109504201594,208.99782209332062L209.25554752100797,171.7489110466603A48.625,48.625,0,0,0,149.9380728359879,94.78956221795482Z" style="stroke: rgb(255, 239, 192); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#fef5ef" fill="url('#46-xr___97.25_178_134.5__rgba_249_213_189_1__50-rgba_249_213_189_1__75')" d="M98.54601497870846,190.57697178197463A97.25,97.25,0,0,1,121.8761456719758,55.07912443590966L149.9380728359879,94.78956221795482A48.625,48.625,0,0,0,138.27300748935423,162.53848589098732Z" style="stroke: rgb(254, 245, 239); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#e9e9e9" fill="url('#46-xr___97.25_178_134.5__rgba_166_166_166_1__50-rgba_166_166_166_1__75')" d="M194.5737483290448,230.32731012777873A97.25,97.25,0,0,1,98.54601497870846,190.57697178197463L138.27300748935423,162.53848589098732A48.625,48.625,0,0,0,186.2868741645224,182.41365506388937Z" style="stroke: rgb(233, 233, 233); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><path transform="matrix(1,0,0,1,0,0)" stroke-dasharray="none" fill-opacity="1" opacity="1" stroke-opacity="0.8" stroke-linejoin="round" stroke-width="1" stroke="#e6eed6" fill="url('#46-xr___97.25_178_134.5__rgba_155_187_88_1__50-rgba_155_187_88_1__75')" d="M240.511095042016,208.99782209332056A97.25,97.25,0,0,1,194.5737483290448,230.32731012777873L186.2868741645224,182.41365506388937A48.625,48.625,0,0,0,209.255547521008,171.74891104666028Z" style="stroke: rgb(230, 238, 214); stroke-linejoin: round; stroke-opacity: 0.8; opacity: 1; fill-opacity: 1;"></path><text fill-opacity="1" font-style="" font-weight="bold" font-size="10px" font-family="Verdana,sans" text-anchor="middle" fill="#555555" stroke="none" y="134.5" x="178" style="stroke: none; fill: rgb(85, 85, 85); text-anchor: middle; font-family: Verdana,sans; font-size: 10px; font-weight: bold; fill-opacity: 1;"><tspan x="178" dy="-2.791667938232422">Total revenue:</tspan><tspan x="178" dy="12">$64.08K</tspan></text></g><g style="" transform="matrix(1,0,0,1,60.5,260.5)" class="fusioncharts-legend"><rect fill="#ffffff" fill-opacity="1" stroke-width="1" stroke-opacity="0" stroke="#545454" ry="0" rx="0" height="25" width="235" y="0" x="0" style="stroke: rgb(84, 84, 84); stroke-opacity: 0; fill-opacity: 1; fill: rgb(255, 255, 255);"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="23" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="23" dy="9.208333969116211">Food</tspan></text><path fill="#ffbe01" fill-opacity="1" stroke="#ffd867" stroke-width="0.5" d="M13.5,11.5L13.5,7.55A3.95,3.95,0,0,1,17.45,11.5ZM11.5,13.5L11.5,8.55A4.95,4.95,0,1,0,16.45,13.5Z" style="stroke: rgb(255, 216, 103); fill-opacity: 1; fill: rgb(255, 190, 1); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="37" y="4" x="4" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="70.85" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="70.85" dy="9.208333969116211">Apparels</tspan></text><path fill="#f9d5bd" fill-opacity="1" stroke="#fbe6d7" stroke-width="0.5" d="M61.35,11.5L61.35,7.55A3.95,3.95,0,0,1,65.3,11.5ZM59.35,13.5L59.35,8.55A4.95,4.95,0,1,0,64.3,13.5Z" style="stroke: rgb(251, 230, 215); fill-opacity: 1; fill: rgb(249, 213, 189); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="47" y="4" x="51.85" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="128.7" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="128.7" dy="9.208333969116211">Electronics</tspan></text><path fill="#a6a6a6" fill-opacity="1" stroke="#cacaca" stroke-width="0.5" d="M119.2,11.5L119.2,7.55A3.95,3.95,0,0,1,123.15,11.5ZM117.2,13.5L117.2,8.55A4.95,4.95,0,1,0,122.15,13.5Z" style="stroke: rgb(202, 202, 202); fill-opacity: 1; fill: rgb(166, 166, 166); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="55" y="4" x="109.7" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect><text stroke="none" font-size="10px" text-anchor="start" fill="#555555" y="6.5" x="194.55" class="fusioncharts-legenditem" style="fill: rgb(85, 85, 85); text-anchor: start; cursor: pointer; font-size: 10px; stroke: none;"><tspan x="194.55" dy="9.208333969116211">Household</tspan></text><path fill="#9bbb58" fill-opacity="1" stroke="#c3d69b" stroke-width="0.5" d="M185.05,11.5L185.05,7.55A3.95,3.95,0,0,1,189,11.5ZM183.05,13.5L183.05,8.55A4.95,4.95,0,1,0,188,13.5Z" style="stroke: rgb(195, 214, 155); fill-opacity: 1; fill: rgb(155, 187, 88); cursor: pointer;"></path><rect stroke="none" stroke-width="1" fill="#c0c0c0" fill-opacity="0.000001" ry="0" rx="0" height="17" width="53" y="4" x="175.55" style="fill-opacity: 0.000001; fill: rgb(192, 192, 192); stroke: none; cursor: pointer;"></rect></g><g class="raphael-group-49-hot"></g><g class="raphael-group-52-buttons"></g></svg></span></div>
                        <!-- legend in pie -->
                        <div class="typeChart whiteText" style="top: 58%;left: 85%;">EXPENSES</div>
                    </div>
                    <div class="smlBlue midle textC">Expenses <span id="expense_amount_format"></span> Baht</div>
                </div>
                <div class="span12 pad">
                    <div class="title"> <img src="/EISDashboardPortlet/resources/images/ICON_12.png" width="38" height="38"> <span class="txtHead">Employability</span> </div>
                    <div class="row">
                        <div class="span6 textC">
                            <p class="orangeText" id="undergraduate">60-70%</p>
                            <p class="grayText midle">Undergraduate</p>
                        </div>
                        <div class="span6"> <img src="/EISDashboardPortlet/resources/images/ICON_13.png" style="margin: 0% 30%;" width="70" height="70"> </div>
                    </div>
                    <div class="row">
                        <div class="span6"> <img src="/EISDashboardPortlet/resources/images/ICON_14.png" style="margin: 0% 30%;" width="118" height="70"> </div>
                        <div class="span6 textC">
                            <p class="orangeText" id="graduate">88-94%</p>
                            <p class="grayText midle">Graduate</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/dwr/engine.js"/>"></script>
<script src="<c:url value="/dwr/util.js"/>"></script>
<script src="<c:url value="/dwr/interface/DashboardAjax.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js" />"></script>
<%-- --%>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>



<script>
    $( document ).ready(function() {
        var param={};

        DashboardAjax.getDashboard(param, {
            callback: function (data) {
                //  data = data.resultListObj;
                if (data != null ) {
                    console.log(data);
                    if(data!=null){
                        var employabilityM=data.employabilityM;
                        var academicM=data.academicM;
                        var programM=data.programM;
                        var budgetM=data.budgetM;
                        var employeeM=data.employeeM;
                        var studentM=data.studentM;

                        //employabilityM
                        $("#undergraduate").html(employabilityM.undergraduate+" %");
                        $("#graduate").html(employabilityM.graduate+" %");

                        //academicM
                        $("#faculties_schools").html(academicM.faculties_schoolse);
                        $("#institute").html(academicM.institute);

                        //programM
                        $("#programs_for_undergraduate").html(programM.programs_for_undergraduate);
                        $("#programs_for_graduate").html(programM.programs_for_graduate);
                        $("#international_program").html(programM.international_program);
                        $("#international_program_for_undergraduate").html(programM.international_program_for_undergraduate);
                        $("#international_program_for_graduate").html(programM.international_program_for_graduate);

                        //employeeM
                        $("#percent_faculty_member_doctor").html(employeeM.percent_faculty_member_doctor);
                        $("#faculty_member_doctor").html(employeeM.faculty_member_doctor);
                        $("#faculty_member_doctor_format").html(employeeM.faculty_member_doctor_format);
                        $("#faculty_member").html(employeeM.faculty_member);
                        $("#faculty_member_format").html(employeeM.faculty_member_format);
                        $("#full_time").html(employeeM.full_time);
                        $("#full_time_format").html(employeeM.full_time_format);
                        var factory_member_bachelor_lower_percent=employeeM.factory_member_bachelor_lower_percent+"%"
                        var factory_member_master_percent=employeeM.factory_member_master_percent+"%"
                        var factory_member_doctoral_percent=employeeM.factory_member_doctoral_percent+"%"

                        var researcher_bachelor_lower_percent=employeeM.researcher_bachelor_lower_percent+"%"
                        var researcher_master_percent=employeeM.researcher_master_percent+"%"
                        var researcher_doctoral_percent=employeeM.researcher_doctoral_percent+"%"

                        var full_time_bachelor_lower_percent=employeeM.full_time_bachelor_lower_percent+"%"
                        var full_time_master_percent=employeeM.full_time_master_percent+"%"
                        var full_time_doctoral_percen=employeeM.full_time_doctoral_percen+"%"

                        $("#factory_member_bachelor_lower_percent").html(factory_member_bachelor_lower_percent);
                        $("#factory_member_master_percent").html(factory_member_master_percent);
                        $("#factory_member_doctoral_percent").html(factory_member_doctoral_percent);

                        $("#researcher_bachelor_lower_percent").html(researcher_bachelor_lower_percent);
                        $("#researcher_master_percent").html(researcher_master_percent);
                        $("#researcher_doctoral_percent").html(researcher_doctoral_percent);

                        $("#full_time_bachelor_lower_percent").html(full_time_bachelor_lower_percent);
                        $("#full_time_master_percent").html(full_time_master_percent);
                        $("#full_time_doctoral_percent").html(full_time_doctoral_percen);

                        $("#factory_member_bachelor_lower_percent").css("width",factory_member_bachelor_lower_percent);
                        $("#factory_member_master_percent").css("width",factory_member_master_percent);
                        $("#factory_member_doctoral_percent").css("width",factory_member_doctoral_percent);

                        $("#researcher_bachelor_lower_percent").css("width",researcher_bachelor_lower_percent);
                        $("#researcher_master_percent").css("width",researcher_master_percent);
                        $("#researcher_doctoral_percent").css("width",researcher_doctoral_percent);

                        $("#full_time_bachelor_lower_percent").css("width",full_time_bachelor_lower_percent);
                        $("#full_time_master_percent").css("width",full_time_master_percent);
                        $("#full_time_doctoral_percent").css("width",full_time_doctoral_percent);

                        //studentM
                        $("#noof_format").html(studentM.noof_format);
                        $("#noof").html(studentM.noof);
                        $("#percent_undergraduate").html(studentM.percent_undergraduate+" %");
                        $("#no_of_undergraduate_format").html(studentM.no_of_undergraduate_format);
                        $("#no_of_undergraduate").html(studentM.no_of_undergraduate);
                        $("#percent_graduate").html(studentM.percent_graduate+" %");
                        $("#no_of_graduate").html(studentM.no_of_graduate);
                        $("#no_of_graduate_format").html(studentM.no_of_graduate_format);
                        $("#percent_male").html(studentM.percent_male+" %");
                        $("#percent_female").html(studentM.percent_female+" %");
                        $("#no_of_student").html(studentM.no_of_student);
                        $("#faculty_ratio").html(studentM.faculty_ratio);

                        var chart={
                            "chart": {
                                // "caption": "Split of Revenue by Product Categories",
                                // "subCaption": "Last year",
                                // "numberPrefix": "$",
                                // "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
                                "paletteColors": "#ffbe01,#f9d5bd,#a6a6a6,#9bbb58,#b3fff0,#4da6ff,##80ffff,#ff80bf,#d966ff,#ff8080,#9999ff,#ffff66,#ffa64d,#bbbb77",
                                "bgColor": "#ffffff",
                                "showBorder": "0",
                                "use3DLighting": "0",
                                "showShadow": "0",
                                "enableSmartLabels": "0",
                                "startingAngle": "310",
                                "showLabels": "0",
                                "showPercentValues": "0",
                                "showLegend": "1",
                                "legendShadow": "0",
                                "legendBorderAlpha": "0",
                                //  "defaultCenterLabel": "Total revenue: $64.08K",
                                //   "centerLabel": "Revenue from $label: $value",
                                "centerLabelBold": "1",
                                "showTooltip": "1",
                                "decimals": "0",
                                "captionFontSize": "14",
                                "subcaptionFontSize": "14",
                                "subcaptionFontBold": "0",
                                "useDataPlotColorForLabels": "1",
                                "showValues":"0"
                            },
                            "data": [
                                {
                                    "label": "Food",
                                    "value": "28504"
                                },
                                {
                                    "label": "Apparels",
                                    "value": "14633"
                                },
                                {
                                    "label": "Electronics",
                                    "value": "10507"
                                },
                                {
                                    "label": "Household",
                                    "value": "4910"
                                }
                            ]
                        };
                        //budgetM
                        var color_list=['#ffbe01','#f9d5bd','#a6a6a6','#9bbb58','#b3fff0','#4da6ff','##80ffff','#ff80bf','#d966ff','#ff8080','#9999ff'
                            ,'#ffff66','#ffa64d','#bbbb77'];
                        var income_list=budgetM.income_list;
                        var expense_list=budgetM.expense_list;
                        //  if(false)
                        if(income_list!=null && income_list.length>0){
                            var str="";
                            var data_income=[];
                            for(var i=0;i<income_list.length;i++){
                                var values={
                                    "label": income_list[i][0],
                                    "value": income_list[i][1]
                                }
                                data_income.push(values)
                                str=str+"<li>"+
                                        "<div class=\"\"> "+
                                        " <div class=\"legend\" style=\"background-color: "+color_list[i]+";\"></div> "+
                                        "   <span>"+income_list[i][0]+"</span> "+
                                        "   </div>"+
                                        "</li>";
                            }
                            chart.data=data_income;
                            var revenueChart1 = new FusionCharts({
                                "type": "doughnut2d",
                                "renderAt": "${ns}chartContainer1",
                                "width": "100%", // 500
                                "height": "300", // chartHeight,
                                "dataFormat": "json",
                                "dataSource":chart

                            });
                            revenueChart1.render();


                        }
                        //     if(false)
                        if(expense_list!=null && expense_list.length>0){
                            var str="";
                            var data_income=[];
                            for(var i=0;i<expense_list.length;i++){
                                var values={
                                    "label": income_list[i][0],
                                    "value": income_list[i][1]
                                }
                                data_income.push(values)
                                str=str+"<li>"+
                                        "<div class=\"\"> "+
                                        " <div class=\"legend\" style=\"background-color: "+color_list[i]+";\"></div> "+
                                        "   <span>"+expense_list[i][0]+"</span> "+
                                        "   </div>"+
                                        "</li>";
                            }
                            chart.data=data_income;
                            var revenueChart2 = new FusionCharts({
                                "type": "doughnut2d",
                                "renderAt": "${ns}chartContainer2",
                                "width": "100%", // 500
                                "height": "300", // chartHeight,
                                "dataFormat": "json",
                                "dataSource":chart

                            });
                            revenueChart2.render();
                            //$("#income_list").html(str);
                            // $("#expense_list").html(str);
                        }
                        $("#income_amount").html(budgetM.income_amount);
                        $("#income_amount_format").html(budgetM.income_amount_format);
                        $("#expense_amount").html(budgetM.expense_amount);
                        $("#expense_amount_format").html(budgetM.expense_amount_format);


                    }

                }
            }
        });

        /*
         var chart={
         "chart": {
         // "caption": "Split of Revenue by Product Categories",
         // "subCaption": "Last year",
         // "numberPrefix": "$",
         // "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
         "paletteColors": "#ffbe01,#f9d5bd,#a6a6a6,#9bbb58,#b3fff0,#4da6ff,##80ffff,#ff80bf,#d966ff,#ff8080,#9999ff,#ffff66,#ffa64d,#bbbb77",
         "bgColor": "#ffffff",
         "showBorder": "0",
         "use3DLighting": "0",
         "showShadow": "0",
         "enableSmartLabels": "0",
         "startingAngle": "310",
         "showLabels": "0",
         "showPercentValues": "0",
         "showLegend": "1",
         "legendShadow": "0",
         "legendBorderAlpha": "0",
         "defaultCenterLabel": "Total revenue: $64.08K",
         "centerLabel": "Revenue from $label: $value",
         "centerLabelBold": "1",
         "showTooltip": "1",
         "decimals": "0",
         "captionFontSize": "14",
         "subcaptionFontSize": "14",
         "subcaptionFontBold": "0",
         "useDataPlotColorForLabels": "1",
         "showValues":"0"
         },
         "data": [
         {
         "label": "Food",
         "value": "28504"
         },
         {
         "label": "Apparels",
         "value": "14633"
         },
         {
         "label": "Electronics",
         "value": "10507"
         },
         {
         "label": "Household",
         "value": "4910"
         }
         ]
         };
         var revenueChart1 = new FusionCharts({
         "type": "doughnut2d",
         "renderAt": "${ns}chartContainer1",
         "width": "100%", // 500
         "height": "300", // chartHeight,
         "dataFormat": "json",
         "dataSource":chart

         });
         revenueChart1.render();
         var revenueChart2 = new FusionCharts({
         "type": "doughnut2d",
         "renderAt": "${ns}chartContainer2",
         "width": "100%", // 500
         "height": "300", // chartHeight,
         "dataFormat": "json",
         "dataSource":chart

         });
         revenueChart2.render();
         */
    });

</script>
</body>
</html>
