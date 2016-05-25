<%--
  User: imake
  Date: 20/11/2015
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
<portlet:renderURL var="list">
    <portlet:param name="action" value="list"/>
</portlet:renderURL>
<portlet:resourceURL var="research_group_resource_get_pdf"
                     id="research_group_resource_get_pdf">
</portlet:resourceURL>
<c:set var="img_wait">
    <c:url value="/resources/images/wait.gif"/>
</c:set>
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

        <style type="text/css">
            img.iconImg{ height: 45px; }
		span.txtHead{ font-weight: bold;color: #000; }
		.fontLevel01{ font-size: 38px; }
		.fontLevel02{ font-size: 26px; }
		.fontLevel03{ font-size: 20px; }
		.fontLevel04{ font-size: 18px; }
		.fontLevel05{ font-size: 14px; }
		.fontLevel06{ font-size: 12px; }
		.typeChart {
		    display: block;
		    border: 4px solid chocolate;
		    background-color: #ff8837;
		    padding: 1%;
		    /* position: absolute; */
		    text-align: center;
		    padding: 5px;
		}
		.languageChange {margin-top:0.3%;}
		.boxLang{margin:5px;height: 23px;width: 35px;}
		.activeLang{border:1px solid #C8C8C8;background:#EAE7E7;padding:1px 5px 8px 5px;}
		.progress{height:33px!important;}
		.aui .progress .bar {color:black!important;font-size:11px;}
		.blodYear {width: 50px;height: 30px;border-top: 1px solid #e5e5e5;border-bottom: 1px solid #e5e5e5;border-left: 1px solid #e5e5e5;padding:6px 5% 5px 1.5%;background-color: #e5e5e5;}
		@media print {
	body {-webkit-print-color-adjust: exact;}

}
        </style>
</head>
<body>
<div class="modal fade" id="${ns}doloadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div style="width: 100%; margin: 0 auto; text-align: center;">
                    <p>Loading....</p><br/>
                    <img src="${img_wait}" alt="" border="0"/>
                </div>

            </div>
            <div class="modal-footer" style="width: 100%; text-align: right;">

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="row-fluid body" style="border-bottom: 1px solid #e5e5e5;">
    <div class="languageChange">
        <div class="span6" style="display: inline-flex;">
            <span id="${ns}year_languageMap" class="blodYear">${kissLanguage.languageMap['year']}: </span>
            <span id="${ns}year_section" style="padding-left: 5px">
                <%--
            <select id="${ns}year" style="width: 100px;" >
<c:if test="${not empty yearM}">
    <c:forEach items="${yearM.years}" var="year" varStatus="loop">
        <c:if test="${dashboardForm.lang=='th'}">
            <option value="${year.yearTH}" >${year.yearTH}</option>
        </c:if>
        <c:if test="${dashboardForm.lang=='eng'}">
            <option value="${year.yearENG}" >${year.yearENG}</option>
        </c:if>
    </c:forEach>
    </c:if>

            </select>
            --%>
                </span>
            <span style="padding-left: 5px" style="width: 90%"><button id="viewDashboard" onclick="${ns}renderDashboard()" class=""><span id="${ns}view_languageMap">${kissLanguage.languageMap['view']}</span></button></span>
        </div>
        <div class="span6">
            <div style="float: right;margin-right:2%;">
<form:form  id="dashboardForm" modelAttribute="dashboardForm" method="post"  name="dashboardForm" action="${formAction}" enctype="multipart/form-data">
    <form:hidden path="lang" id="${ns}lang" />
	<div id="${ns}lang_section" style="float:left;">
            <%--
                <span class="boxLang" id="print" style="float:left;">
                    <img onclick="${ns}printFile()" src="/EISDashboardPortlet/resources/images/print1.jpg" style="cursor: pointer;">
                </span>
                <c:if test="${kissLanguage.languageMap['year']=='Year'}">
                    <span class="boxLang activeLang" id="Eng" style="float:left;">
                        <img onclick="${ns}doSubmit('eng')" src="/EISDashboardPortlet/resources/images/eng.png" style="cursor: pointer; margin-top:3px;">
                    </span>
                    <span class="boxLang" id="TH" style="float:left;">
                    <img onclick="${ns}doSubmit('th')" src="/EISDashboardPortlet/resources/images/th.png" style="cursor: pointer; margin-top:3px;">
                </span>
                </c:if>
                <c:if test="${kissLanguage.languageMap['year']!='Year'}">
                    <span class="boxLang" id="Eng" style="float:left;">
                        <img onclick="${ns}doSubmit('eng')" src="/EISDashboardPortlet/resources/images/eng.png" style="cursor: pointer; margin-top:3px;">
                    </span>
                    <span class="boxLang activeLang" id="TH" style="float:left;">
                        <img onclick="${ns}doSubmit('th')" src="/EISDashboardPortlet/resources/images/th.png" style="cursor: pointer; margin-top:3px;">
                    </span>
                </c:if>
                --%>
	</div>
    </form:form>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span8">
            <div class="row">
                <div class="span6 pad">
                    <div class="title">
                        <div class="imgHead">
			  <img class="iconImg" src="/EISDashboardPortlet/resources/images/ICON_1.png">
			</div>
                        <div class="txtHeadBox">
			  <span id="${ns}student_languageMap" class="txtHead">${kissLanguage.languageMap['student']}</span>
			</div>
                    </div>
                    <div class="orangeText textR fontLevel01" name="noof_format" id="noof_format"
                         style="margin-bottom:5px; padding-top:5px; padding-right: 15px;">
                        0
                    </div>
                    <div class="blackText fontLevel01" name="percent_undergraduate" id="percent_undergraduate" style="margin-bottom: 5px;">
                        0%
                    </div>
                    <div class="smlBlue fontLevel04" style="margin-bottom: 5px;">
                        <span id="no_of_undergraduate_format">0</span> <span id="${ns}undergraduate_students_languageMap" >${kissLanguage.languageMap['undergraduate_students']}</span>
                    </div>
                    <div class="blackText fontLevel01"  name="percent_graduate" id="percent_graduate" style="margin-bottom: 5px; margin-top:20px;">
                        0%
                    </div>
                    <div class="smlOrange fontLevel04" style="margin-bottom: 5px;" style="margin-bottom: 5px;">
                        <span id="no_of_graduate_format">0</span> <span id="${ns}graduate_students_languageMap" >${kissLanguage.languageMap['graduate_students']}</span>
                    </div>
                    <div class="stdBox" style="margin-top:20px;">
                        <div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
                            <div class="" style="width: 50%; float: left;">
                                <div class="Box textC">
                                    <img src="/EISDashboardPortlet/resources/images/ICON_2.png" width="50" height="70">
                                    <p class="orangeText fontLevel01" name="perM" id="percent_male">0%</p>
                                </div>
                            </div>
                            <div class="" style="width: 50%; float: left;">
                                <div class="Box textC">
                                    <img src="/EISDashboardPortlet/resources/images/ICON_3.png" width="50" height="70">
                                    <p class="blueText fontLevel01" name="perW" id="percent_female">0%</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="blue-bg whiteText textC fontLevel03" style="padding-top:5px; padding-bottom:5px; margin-top:10px;">
                        <span id="no_of_student"></span> : <span id="faculty_ratio">1</span>  <span id="${ns}student_faculty_ratio_languageMap" >${kissLanguage.languageMap['student_faculty_ratio']}</span>
                    </div>
                </div>

                <div class="span6">
                    <div class="row">
                        <div class="span12 pad" style="margin-bottom: 5px;">
                            <div class="title" style="margin-top:0.9%;">
				<div class="imgHead">
                                  <img class="iconImg" src="/EISDashboardPortlet/resources/images/ICON_8.png">
				</div>
				<div class="txtHeadBox">
                                  <span id="${ns}academic_languageMap"  class="txtHead">${kissLanguage.languageMap['academic']}</span>
				</div>
                            </div>
                            <div class="stdBox">
                                <div class="row-fluid">
                                    <div class="row-fluid">
                                        <div style="width: 50%; float: left;">
                                            <div class="Box textC">
                                                <div style="width:50%; float:left;">
                                                    <img src="/EISDashboardPortlet/resources/images/ICON_9.png" width="38" height="30"/>
                                                </div>
                                                <div style="width:50%; float:left;">
                                                    <div class="orangeText textC fontLevel01" id="faculties_schools"> 0 </div>
                                                </div>
                                            </div>
                                            <div>
                                                <div id="${ns}faculties_schools_languageMap" class="Box grayText textC fontLevel04"> ${kissLanguage.languageMap['faculties_schools']} </div>
                                            </div>
                                        </div>
                                        <div style="width: 20%; float: left;">
                                            <div class="Box orangeText textC fontLevel01"> <b> + </b> </div>
                                        </div>
                                        <div style="width: 30%; float: left;">
                                            <div class="orangeText textC fontLevel01" id="institute" style="margin-top:8%;"> 0 </div>
                                            <div id="${ns}institute_languageMap" class="grayText textC fontLevel04"> ${kissLanguage.languageMap['institute']} </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <p id="${ns}for_special_study_robotics_languageMap" class="smlBlue textR fontLevel05">(${kissLanguage.languageMap['for_special_study_robotics']})</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="span12 pad">
                            <div class="title">
				<div class="imgHead">
                                  <img src="/EISDashboardPortlet/resources/images/ICON_10.png" class="iconImg">
				</div>
				<div class="txtHeadBox">
                                  <span id="${ns}programs_languageMap" class="txtHead">${kissLanguage.languageMap['programs']}</span>
				</div>
                            </div>
                            <div class="row-fluid" style="margin-top:10px">
                                <div style="width:30%; padding-left:20px; float:left;">
                                    <img src="/EISDashboardPortlet/resources/images/orangeLine.png" width="100"/>
                                </div>

				<div style="width:70%; float:left;">
                                    <div class="orangeText fontLevel01" style="width:25%; vertical-align:middle; float:left;" id="programs_for_undergraduate"> 0 </div>
                                    <div id="${ns}programs_for_ungraduate_languageMap" class="orangeText fontLevel04" style="width:75%; vertical-align:middle; float:left; margin-top:0.5em;">
                                        ${kissLanguage.languageMap['programs_for_ungraduate']}
                                    </div>
                                </div>
                            </div>
                            <div style="width:98%;">
                                <div class="row-fluid progress" style="margin-bottom:8px;">
                                    <div class="bar orangeProg" style="width:30%;"></div>
                                    <div class="bar blueProg" style="width:70%;"></div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div style="width:75%; float:left; margin-right: 5%; text-align: right;">
                                    <div id="${ns}programs_for_graduate_languageMap" class="smlBlue fontLevel04" style="width:80%; vertical-align:middle; float:left; margin-top:1.5em; text-align:left; padding-right:0; padding-left:25%;">
                                        ${kissLanguage.languageMap['programs_for_graduate']}
                                    </div>
				<div class="smlBlue fontLevel01" style="width:20%; float:left; vertical-align:middle; margin-top:0.6em; text-align: right; padding:0;" id="programs_for_graduate">
                                        0
                                    </div>
                                </div>
                                <div style="width:20%; float:left; padding-right:20px; text-align:right;">
                                    <img src="/EISDashboardPortlet/resources/images/blueLine.png" width=60/>
                                </div>
                            </div>
                            <div class="span12" style="margin-top:10px;">
                                <div id="${ns}international_program_languageMap" class="grayText textR fontLevel02">${kissLanguage.languageMap['international_program']}</div>
                                <div class="row-fluid grayText" style="margin-top:5px;">
                                    <div class="fontLevel01" style="width:25%; float:left; margin-top:5px; text-align:center;" id="international_program"> 0 </div>
                                    <div class="fontLevel01" style="width:15%; float:left; margin-top:5px; text-align:left;"> } </div>
                                    <div class="55 fontLevel04" style="width:55%;height:35%;padding-top:10px; float:left; text-align:left;">
                                        <span id="international_program_for_undergraduate">0</span> <span id="${ns}for_undergraduate_languageMap" >${kissLanguage.languageMap['for_undergraduate']}</span> <br><span id="international_program_for_graduate">0</span>  <span id="${ns}for_graduate_languageMap" >${kissLanguage.languageMap['for_graduate']}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="span12 pad">
                    <div class="title">
                   	<img src="/EISDashboardPortlet/resources/images/ICON_4.png" class="iconImg" style="float:left;margin-right:2%;">
                        <span id="${ns}employees_languageMap" class="txtHead" style="float:left;line-height:40px;">${kissLanguage.languageMap['employees']}</span>
                    </div>
                    <div class="row-fluid textC pad_1" style="position:relative;">
                        <div class="span2">
                            <img src="/EISDashboardPortlet/resources/images/ICON_5.png" width="109" height="80">
                        </div>
                        <div class="span10">
                            <div  style="width:30%; float:left;">
                                <p class="orangeText fontLevel01" id="faculty_member_doctor_format"> 0 </p>
                                <p id="${ns}facutty_member_languageMap" class="grayText fontLevel03">${kissLanguage.languageMap['facutty_member']}</p>

                            </div>
                            <!-- absolute line -->
                            <div style="width:20%; float:left;margin-top:3%;">
                                <img src="/EISDashboardPortlet/resources/images/line.png" />
                            </div>
                            <div style="width:50%; float:left;margin-top:2.7%;">
                                <div class="progress" style="position:relative;top:1em;">
                                    <div class="bar grayProg" style="width: 0%;" id="factory_member_bachelor_lower_percent">0%</div>
                                    <div class="bar orangWProg" style="width: 0%;"  id="factory_member_master_percent">0%</div>
                                    <div class="bar orgProg" style="width: 0%;" id="factory_member_doctoral_percent">0%</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid textC pad_1" style="position: relative;">
                        <div class="span2">
                            <img src="/EISDashboardPortlet/resources/images/ICON_6.png" width="80" height="80">
                        </div>
                        <div class="span10">
                            <div style="width:30%; float:left;">
                                <p class="orangeText fontLevel01"  id="faculty_member_format">0</p>
                                <p id="${ns}rearchers_languageMap"  class="grayText fontLevel03" >${kissLanguage.languageMap['rearchers']}</p>
                            </div>
                            <div style="width:20%; float:left;margin-top:3%;">
                                <!-- absolute line -->
                                <img src="/EISDashboardPortlet/resources/images/line.png">
                            </div>
                            <div style="width:50%; float:left;margin-top:2.7%;">
                                <div class="progress" style="position:relative;top:1em;">
                                    <div class="bar grayProg" style="width: 0%;" id="researcher_bachelor_lower_percent">0%</div>
                                    <div class="bar orangWProg" style="width: 0%;" id="researcher_master_percent">0%</div>
                                    <div class="bar orgProg" style="width: 0%;" id="researcher_doctoral_percent">0%</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid textC pad_1" style="position: relative;">
                        <div class="span2">
                            <img src="/EISDashboardPortlet/resources/images/ICON_7.png" width="80" height="80">
                        </div>
                        <div class="span10">
                            <div style="width:30%; float:left;">
                                <p class="orangeText  fontLevel01"  id="full_time_format">0</p>
                                <p id="${ns}full_time_languageMap"  class="grayText fontLevel03">${kissLanguage.languageMap['full_time']}</p>
				<p id="${ns}support_staff_languageMap"  class="grayText fontLevel03">${kissLanguage.languageMap['support_staff']}</p>
                            </div>
                            <div style="width:20%; float:left;margin-top:3%;">
                                <!-- absolute line  -->
                                <img src="/EISDashboardPortlet/resources/images/line.png">
                            </div>
                            <div style="width:50%; float:left;margin-top:2.7%;">
                                <div class="progress" style="position:relative;top:1em;">
                                    <div class="bar grayProg" style="width: 0%;" id="full_time_bachelor_lower_percent">0%</div>
                                    <div class="bar orangWProg" style="width: 0%;" id="full_time_master_percent">0%</div>
                                    <div class="bar orgProg" style="width: 0%;" id="full_time_doctoral_percent">0%</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div style="float: right;width:320px;">
                            <div class="legend-box" style="width: 46%; float:left;">
                                <div class="legend" style="background-color: #dbdbdb;float:left;"></div>
                                <div id="${ns}bachelor_low_languageMap" class="grayText span10" style="float:left;"> ${kissLanguage.languageMap['bachelor_low']}</div>
			    </div>
                            <div class="legend-box" style="width: 23%; float:left;">
                                <div class="legend" style="background-color: #ffbf93;float:left;"></div>
                                <div id="${ns}master_s_languageMap" class="grayText" style="float:left;">${kissLanguage.languageMap['master_s']}</div>
                            </div>
                            <div class="legend-box" style="width: 25%; float:left;">
                                <div class="legend" style="background-color: #ff8837;float:left;"></div>
				<div id="${ns}doctoral_languageMap" class="grayText" style="float:left;">${kissLanguage.languageMap['doctoral']}</div>
			    </div>
			</div>

                    </div>
                </div>
            </div>
        </div>

        <div class="span4">
            <div class="row">
                <div class="span12 pad">
                    <div class="title" style="margin-top:0.763%;">
			<div class="imgHead">
			  <img src="/EISDashboardPortlet/resources/images/ICON_11.png" class="iconImg">
			</div>
			<div class="txtHeadBox">
			  <span id="${ns}budget_languageMap"  class="txtHead">${kissLanguage.languageMap['budget']}</span>
			</div>
		    </div>
                    <div class="smlBlue midle fontLevel03"><span id="${ns}fiscal_year_languageMap">${kissLanguage.languageMap['fiscal_year']}</span> <span id="year_select">2014</span></div>
                    <!-- INCOMES pie -->
                    <div id="${ns}incomes_1_languageMap" class="typeChart whiteText span8 offset2 textC" style="margin-top:3%;">${kissLanguage.languageMap['incomes_1']}</div>
                    <div style="padding:0 10%;">
                        <div id="${ns}chartContainer1"></div>
                    </div>
                    <div  class="smlBlue midle textC fontLevel04"><span id="${ns}incomes_2_languageMap">${kissLanguage.languageMap['incomes_2']}</span> <span id="income_amount_format"></span> <span name="${ns}million_baht_languageMap">${kissLanguage.languageMap['million_baht']}</span></div>
                    <br/>
                    <!-- EXPENSES pie -->
                    <div id="${ns}expenses_1_languageMap" class="typeChart whiteText span8 offset2 textC">${kissLanguage.languageMap['expenses_1']}</div>
                    <div style="padding:0 10%;">
                        <div id="${ns}chartContainer2"></div>
                    </div>
                    <div class="smlBlue midle textC fontLevel04"><span id="${ns}expenses_2_languageMap">${kissLanguage.languageMap['expenses_2']}</span> <span id="expense_amount_format"></span> <span name="${ns}million_baht_languageMap">${kissLanguage.languageMap['million_baht']}</span></div>
                </div>

                <div class="span12 pad">
                    <div class="title">
			<div class="imgHead">
			  <img src="/EISDashboardPortlet/resources/images/ICON_12.png" class="iconImg">
			</div>
			<div class="txtHeadBox">
			  <span id="${ns}employability_languageMap" class="txtHead">${kissLanguage.languageMap['employability']}</span>
			</div>
		    </div>
                    <div class="row" style="display: flex;margin:1% 0 1% 2%;width: 100%;">
                        <div class="textC" style="width:50%;float:left;">
                            <p class="smlBlue fontLevel01" id="undergraduate">0%</p>
                            <p id="${ns}undergraduate_languageMap" class="grayText midle fontLevel02">${kissLanguage.languageMap['undergraduate']}</p>
                        </div>
                        <div style="width:50%;float:left;"> <img src="/EISDashboardPortlet/resources/images/ICON_13.png" style="margin: 0% 30%;" width="70" height="70"> </div>
                    </div>
                    <div class="row" style="display: flex;margin:1% 0 1% 2%;width: 100%">
                        <div style="width:50%;float:left;"> <img src="/EISDashboardPortlet/resources/images/ICON_14.png" style="margin: 0% 30%;" width="90" height="53"> </div>
                        <div class="textC" style="width:50%;float:left;">
                            <p class="fontLevel01 orangeText" id="graduate">0%</p>
                            <p id="${ns}graduate_languageMap" class="grayText midle fontLevel02">${kissLanguage.languageMap['graduate']}</p>
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
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<%-- --%>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>



<script>
    var obj_lang_g;
    function ${ns}renderDashboard(){
        ${ns}waiting()
        var year=    $("#${ns}year").val();
        var lang=    $("#${ns}lang").val();
        // alert(year+","+lang)
        var param={
            "year":year,
            "lang":lang
        };
        var param_lang={
            lang:lang
        }
        DashboardAjax.getKissLanguage(param_lang, {
            callback: function (data_lang) {
                //  data = data.resultListObj;
                if (data_lang != null) {
                    obj_lang_g=data_lang;
                   // alert(obj_lang_g.languageMap)
                    for(key in obj_lang_g.languageMap) {

                        if(key=='for_special_study_robotics'){
                            $("#${ns}"+key+"_languageMap").html("("+obj_lang_g.languageMap[key]+")");
                        }else if(key=='million_baht'){
                            $('span[name="${ns}million_baht_languageMap"]').html(obj_lang_g.languageMap[key])
                        }else{
                            $("#${ns}"+key+"_languageMap").html(obj_lang_g.languageMap[key]);
                        }
                    }
                }
                //alert("${dashboardForm.lang}")
                $("#year_select").html(year)
                DashboardAjax.getDashboard(param, {
                    callback: function (data) {
                        //  data = data.resultListObj;
                        if (data != null ) {
                            // console.log(data);
                            if(data!=null){
                                var employabilityM=data.employabilityM;
                                var academicM=data.academicM;
                                var programM=data.programM;
                                var budgetM=data.budgetM;
                                var employeeM=data.employeeM;
                                var studentM=data.studentM;

                                //employabilityM
                                if(employabilityM!=null) {
                                    var undergraduate="0";
                                    if(employabilityM.undergraduate!=null)
                                        undergraduate=employabilityM.undergraduate
                                    $("#undergraduate").html(undergraduate + " %");
                                    var graduate="0";
                                    if(employabilityM.graduate!=null)
                                        graduate=employabilityM.graduate
                                    $("#graduate").html(graduate + " %");
                                }

                                //academicM
                                $("#faculties_schools").html((academicM.faculties_schools!=null?(academicM.faculties_schools):("0")));
                                $("#institute").html((academicM.institute!=null?(academicM.institute):("0")));

                                //programM
                                $("#programs_for_undergraduate").html((programM.programs_for_undergraduate!=null?(programM.programs_for_undergraduate):("0")));
                                $("#programs_for_graduate").html((programM.programs_for_graduate!=null?(programM.programs_for_graduate):("0")));
                                $("#international_program").html((programM.international_program!=null?(programM.international_program):("0")));
                                $("#international_program_for_undergraduate").html((programM.international_program_for_undergraduate!=null?(programM.international_program_for_undergraduate):("0")));
                                $("#international_program_for_graduate").html((programM.international_program_for_graduate!=null?(programM.international_program_for_graduate):("0")));

                                //employeeM
                                $("#percent_faculty_member_doctor").html((employeeM.percent_faculty_member_doctor!=null?(employeeM.percent_faculty_member_doctor):("0")));
                                $("#faculty_member_doctor").html((employeeM.faculty_member_doctor!=null?(employeeM.faculty_member_doctor):("0")));
                                $("#faculty_member_doctor_format").html((employeeM.faculty_member_doctor_format!=null?(employeeM.faculty_member_doctor_format):("0")));
                                $("#faculty_member").html((employeeM.faculty_member!=null?(employeeM.faculty_member):("0")));
                                $("#faculty_member_format").html((employeeM.faculty_member_format!=null?(employeeM.faculty_member_format):("0")));
                                $("#full_time").html((employeeM.full_time!=null?(employeeM.full_time):("0")));
                                $("#full_time_format").html((employeeM.full_time_format!=null?(employeeM.full_time_format):("0")));
                                var factory_member_bachelor_lower_percent=(employeeM.factory_member_bachelor_lower_percent!=null?(employeeM.factory_member_bachelor_lower_percent):("0"))+"%"
                                var factory_member_master_percent=(employeeM.factory_member_master_percent!=null?(employeeM.factory_member_master_percent):("0"))+"%"
                                var factory_member_doctoral_percent=(employeeM.factory_member_doctoral_percent!=null?(employeeM.factory_member_doctoral_percent):("0"))+"%"

                                var researcher_bachelor_lower_percent=(employeeM.researcher_bachelor_lower_percent!=null?(employeeM.researcher_bachelor_lower_percent):("0"))+"%"
                                var researcher_master_percent=(employeeM.researcher_master_percent!=null?(employeeM.researcher_master_percent):("0"))+"%"
                                var researcher_doctoral_percent=(employeeM.researcher_doctoral_percent!=null?(employeeM.researcher_doctoral_percent):("0"))+"%"

                                var full_time_bachelor_lower_percent=(employeeM.full_time_bachelor_lower_percent!=null?(employeeM.full_time_bachelor_lower_percent):("0"))+"%"
                                var full_time_master_percent=(employeeM.full_time_master_percent!=null?(employeeM.full_time_master_percent):("0"))+"%"
                                var full_time_doctoral_percent=(employeeM.full_time_doctoral_percent!=null?(employeeM.full_time_doctoral_percent):("0"))+"%"

                                $("#factory_member_bachelor_lower_percent").html(factory_member_bachelor_lower_percent);
                                $("#factory_member_master_percent").html(factory_member_master_percent);
                                $("#factory_member_doctoral_percent").html(factory_member_doctoral_percent);

                                $("#researcher_bachelor_lower_percent").html(researcher_bachelor_lower_percent);
                                $("#researcher_master_percent").html(researcher_master_percent);
                                $("#researcher_doctoral_percent").html(researcher_doctoral_percent);

                                $("#full_time_bachelor_lower_percent").html(full_time_bachelor_lower_percent);
                                $("#full_time_master_percent").html(full_time_master_percent);
                                $("#full_time_doctoral_percent").html(full_time_doctoral_percent);

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
                                $("#percent_undergraduate").html((studentM.percent_undergraduate!=null?(studentM.percent_undergraduate):("0"))+" %");
                                $("#no_of_undergraduate_format").html((studentM.no_of_undergraduate_format!=null?(studentM.no_of_undergraduate_format):("0")));
                                $("#no_of_undergraduate").html((studentM.no_of_undergraduate!=null?(studentM.no_of_undergraduate):("0")));
                                $("#percent_graduate").html((studentM.percent_graduate!=null?(studentM.percent_graduate):("0"))+" %");
                                $("#no_of_graduate").html((studentM.no_of_graduate!=null?(studentM.no_of_graduatet):("0")));
                                $("#no_of_graduate_format").html((studentM.no_of_graduate_format!=null?(studentM.no_of_graduate_format):("0")));
                                $("#percent_male").html((studentM.percent_male!=null?(studentM.percent_male):("0"))+" %");
                                $("#percent_female").html((studentM.percent_female!=null?(studentM.percent_female):("0"))+" %");
                                $("#no_of_student").html((studentM.no_of_student!=null?(studentM.no_of_student):("0")));
                                $("#faculty_ratio").html((studentM.faculty_ratio!=null?(studentM.faculty_ratio):("0")));

                                var chart={
                                    "chart": {
                                        "paletteColors":"",
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
                                        "centerLabelBold": "1",
                                        "showTooltip": "1",
                                        "decimals": "0",
                                        "captionFontSize": "14",
                                        "subcaptionFontSize": "14",
                                        "subcaptionFontBold": "0",
                                        "useDataPlotColorForLabels": "1",
                                        "showValues":"0"
                                    },
                                    "data": []
                                };
                                //budgetM

                                var income_color_list="#FFC000,#FCD6B6,#A6A6A6,#9BBB59,#E46C0A,#4da6ff,##80ffff,#ff80bf,#d966ff,#ff8080,#9999ff,#ffff66,#ffa64d,#bbbb77";
                                var income_list=budgetM.income_list;

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

                                    }
                                    chart.chart.paletteColors=income_color_list;
                                    chart.data=data_income;
                                    var revenueChart1 = new FusionCharts({
                                        "type": "doughnut2d",
                                        "renderAt": "${ns}chartContainer1",
                                        "width": "350", // 500
                                        "height":"300", // chartHeight,
                                        "dataFormat": "json",
                                        "dataSource":chart

                                    });
                                    revenueChart1.render();
                                }else{
                                    $("#${ns}chartContainer1").html('');
                                }

                                var expense_color_list="#FF7D25,#A6A6A6,#9BBB59,#FAC090,#E46C0A,#4da6ff,##80ffff,#ff80bf,#d966ff,#ff8080,#9999ff,#ffff66,#ffa64d,#bbbb77";
                                var expense_list=budgetM.expense_list;
                                //alert(expense_list)
                                //     if(false)
                                if(expense_list!=null && expense_list.length>0){
                                    var str="";
                                    var data_outcome=[];
                                    for(var i=0;i<expense_list.length;i++){
                                        // alert(expense_list[i][0])
                                        var values={
                                            "label": expense_list[i][0],
                                            "value": expense_list[i][1]
                                        }
                                        data_outcome.push(values)

                                    }
                                    chart.chart.paletteColors=expense_color_list;
                                    chart.data=data_outcome;


                                    //$("#income_list").html(str);
                                    // $("#expense_list").html(str);
                                    var revenueChart2 = new FusionCharts({
                                        "type": "doughnut2d",
                                        "renderAt": "${ns}chartContainer2",
                                        "width": "350", // 500 100%
                                        "height": "300", // chartHeight,
                                        "dataFormat": "json",
                                        "dataSource":chart

                                    });
                                    revenueChart2.render();
                                }
                                else{
                                    $("#${ns}chartContainer2").html('');
                                }


                                $("#income_amount").html((budgetM.income_amount!=null?(budgetM.income_amount):("0")));
                                $("#income_amount_format").html((budgetM.income_amount_format!=null?(budgetM.income_amount_format):("0")));
                                $("#expense_amount").html((budgetM.expense_amount!=null?(budgetM.expense_amount):("0")));
                                $("#expense_amount_format").html((budgetM.expense_amount_format!=null?(budgetM.expense_amount_format):("0")));
                                ${ns}closeWaitng();

                            }

                        }
                    }
                });
            }
        });

    }
    function <portlet:namespace />doSubmit(lang) {
        var form = document.forms['dashboardForm'];
        $("#${ns}lang").val(lang)
        form.submit();

    }
    function ${ns}waiting() {
        $("#${ns}doloadModal").modal();
    }
    function ${ns}closeWaitng() {
        $("#${ns}doloadModal").modal('hide');
    }
    $.urlParam = function(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if(results!=null)
            return results[1] || 0;
        else
            return null;
    }

    function ${ns}getLang(){
        var param={
            lang:'th'
        }
        DashboardAjax.getKissLanguage(param, {
            callback: function (data) {
                //  data = data.resultListObj;
                if (data != null) {
                   // alert(data.languageMap['budget'])
                }
            }
        });

    }
    $( document ).ready(function() {
        var year=decodeURIComponent($.urlParam('year'));
        var lang=decodeURIComponent($.urlParam('lang'));
        var lang_str="<span class=\"boxLang\" id=\"print\">"+
                "<img onclick=\"${ns}printFile()\" src=\"/EISDashboardPortlet/resources/images/print1.jpg\" style=\"cursor: pointer;\">"+
                "</span>";
        if(year !=null && year!='null'){
            $("#${ns}year").val(year);
            if(lang=='eng'){
            lang_str=lang_str+" <span class=\"boxLang activeLang\" id=\"Eng\"> "+
                    " <img onclick=\"${ns}doSubmit('eng')\" src=\"/EISDashboardPortlet/resources/images/eng.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    "</span> "+
                    " <span class=\"boxLang\" id=\"TH\"> "+
                    "<img onclick=\"${ns}doSubmit('th')\" src=\"/EISDashboardPortlet/resources/images/th.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    "</span> ";
            }
            else{
            lang_str=lang_str+"   <span class=\"boxLang\" id=\"Eng\"> "+
                    " <img onclick=\"${ns}doSubmit('eng')\" src=\"/EISDashboardPortlet/resources/images/eng.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    " </span> "+
                    " <span class=\"boxLang activeLang\" id=\"TH\"> "+
                    "  <img onclick=\"${ns}doSubmit('th')\" src=\"/EISDashboardPortlet/resources/images/th.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    "</span>";
            }
        }else{
             <c:if test="${kissLanguage.languageMap['year']=='Year'}">
                lang_str=lang_str+" <span class=\"boxLang activeLang\" id=\"Eng\"> "+
                       " <img onclick=\"${ns}doSubmit('eng')\" src=\"/EISDashboardPortlet/resources/images/eng.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                        "</span> "+
                        " <span class=\"boxLang\" id=\"TH\"> "+
                        "<img onclick=\"${ns}doSubmit('th')\" src=\"/EISDashboardPortlet/resources/images/th.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                        "</span> ";
                </c:if>
                <c:if test="${kissLanguage.languageMap['year']!='Year'}">
            lang_str=lang_str+"   <span class=\"boxLang\" id=\"Eng\"> "+
                       " <img onclick=\"${ns}doSubmit('eng')\" src=\"/EISDashboardPortlet/resources/images/eng.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    " </span> "+
                    " <span class=\"boxLang activeLang\" id=\"TH\"> "+
                      "  <img onclick=\"${ns}doSubmit('th')\" src=\"/EISDashboardPortlet/resources/images/th.png\" style=\"cursor: pointer; margin-top:3px;\"> "+
                    "</span>";
                </c:if>
        }
            $("#${ns}lang_section").html(lang_str);
            // alert(year)

        var year_str="<select id=\"${ns}year\" style=\"width: 100px;\" >";
            if(lang !=null && lang!='null'){
                $("#${ns}lang").val(lang);
                <c:if test="${not empty yearM}">
                    <c:forEach items="${yearM.years}" var="year" varStatus="loop">
                        if(lang=='th'){
                            year_str=year_str+"<option value=\"${year.yearTH}\" >${year.yearTH}</option>";
                        }else{
                            year_str=year_str+"<option value=\"${year.yearENG}\" >${year.yearENG}</option>";
                        }
                    </c:forEach>
                </c:if>

            }else{
            <c:if test="${not empty yearM}">
                <c:forEach items="${yearM.years}" var="year" varStatus="loop">
                    <c:if test="${dashboardForm.lang=='th'}">
                        year_str=year_str+"<option value=\"${year.yearTH}\" >${year.yearTH}</option>";
                    </c:if>
                    <c:if test="${dashboardForm.lang=='eng'}">
                        year_str=year_str+"<option value=\"${year.yearENG}\" >${year.yearENG}</option>";
                    </c:if>
                </c:forEach>
            </c:if>
            }
        year_str=year_str+"</select>";
        $("#${ns}year_section").html(year_str)
        if(year !=null && year!='null'){
            $("#${ns}year").val(year);
        }
                //alert(lang)
            ${ns}renderDashboard()
    });

    function ${ns}printFile(){
        var year=$("#${ns}year").val();
        var lang=$("#${ns}lang").val();
        var src = '${research_group_resource_get_pdf}'+'&${ns}year='+year+'&${ns}lang='+lang//$("#<portlet:namespace />_export_"+type).val();
      //  alert(src)

        var div = document.createElement("div");
        document.body.appendChild(div);
        div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";

    }
</script>
</body>
</html>
