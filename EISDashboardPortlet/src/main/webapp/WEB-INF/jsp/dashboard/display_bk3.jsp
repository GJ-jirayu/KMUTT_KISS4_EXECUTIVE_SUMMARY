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
    <%--
    <link rel="stylesheet"
          href="<c:url value="/resources/css/stycss.css"/>" type="text/css"/>
    --%>
    <link rel="stylesheet"
          href="<c:url value="/resources/font-awesome/weloveiconfonts.css"/>" type="text/css"/>
</head>
<body>
<div class="row-fluid">
    <div class="span8" style="border:1px;background-color: whitesmoke">
        <div class="row-fluid">
            <div class="span6" style="background-color: #b7d2af" align="center">
                <div class="">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_1.png"/>" width="42" height="38">
                        <span>Student</span>
                    </div>
                    <div class="orangeText textR" name="noof_format" id="noof_format">
                        17,477
                    </div>
                    <div class="blackText" name="percent_undergraduate" id="percent_undergraduate">
                        66%
                    </div>
                    <div class="smlBlue" name="no_of_undergraduate_format" id="no_of_undergraduate_format">
                        11,578 Undergraduate Students
                    </div>
                    <div class="blackText" name="percent_graduate" id="percent_graduate">
                        34%
                    </div>
                    <div class="smlOrange" name="no_of_graduate_format" id="no_of_graduate_format">
                        5,899 Graduate Students
                    </div>
                    <div class="stdBox">
                        <div class="row">
                            <div class="col-md-6 col-xs-6">
                                <div class="Box textC">
                                    <img src="<c:url value="/resources/images/ICON_2.png"/>" width="50" height="70">
                                    <p class="orangeText" name="percent_male" id="percent_male">56%</p>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-6">
                                <div class="Box textC">
                                    <img src="<c:url value="/resources/images/ICON_3.png"/>" width="50" height="70">

                                    <p class="blueText" name="percent_female" id="percent_female">34%</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="blue-bg whiteText textC">
                        <span id="no_of_student">22</span>:
                        <span id="faculty_ratio">1</span>
                        Student/Faculty Ratio
                    </div>
                </div>
            </div>
            <div class="span6" style="background-color: #a5a4d2" align="center">
                <div class="col-md-12 col-xs-12">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_8.png"/>" width="55" height="38">
                        <label>Academic</label>
                    </div>
                    <div class="stdBox">
                        <div class="row">
                            <div class="col-md-1 col-xs-1 col-md-offset-2 col-xs-offset-2">
                                <div class="Box textC">
                                    <img src="<c:url value="/resources/images/ICON_9.png"/>" width="45" height="45"
                                         style="margin-top: 0.5em;">
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <div class="Box orangeText textC" id="faculties_schools">
                                    11
                                </div>
                            </div>
                            <div class="col-md-1 col-xs-1">
                                <div class="Box orangeText textC">
                                    +
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <div class="Box orangeText textC" id="institute">
                                    1
                                </div>
                            </div>
                            <div class="col-md-5 col-xs-5 col-md-offset-1 col-xs-offset-1 grayText textR">
                                Faculties / Schools
                            </div>
                            <div class="col-md-3 col-xs-3 col-md-offset-1 col-xs-offset-1 grayText textR">
                                Institute
                            </div>
                            <div class="col-md-12 col-xs-12 smlBlue textR">
                                <p class="smlBlue textR">(for Special Study Robotics)</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-xs-12">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_10.png"/>" width="38" height="38">
                        <label>Programs</label>
                    </div>
                    <div id="${ns}chartProgram"></div>
                    <div >
                        <%--
                        <div class="progress" >
                            <div class="progress-bar" style="width: 30%;background-color: #ff7415;">
                            </div>
                            <div class="progress-bar" style="width: 70%;background-color: #003399;">
                            </div>
                        </div>
                        --%>
                        <!-- *** top absolute on progress *** -->
                        <img class="orangeLine" src="<c:url value="/resources/images/orangeLine.png"/>">

                        <div class="orangeText" id="researcher_doctoral_percent">
                            46
                        </div>
                        <div class="smlOrange">Programs for <br>Undergraduate
                        </div>
                        <!--end-->
                        <!-- *** bottom absolute on progress *** -->
                        <img class="blueLine" src="<c:url value="/resources/images/blueLine.png"/>">

                        <div class="blueText"  id="programs_for_graduate">
                            101
                        </div>
                        <div class="smlBlue">Programs for <br>graduate
                        </div>
                        <!--end-->
                    </div>
                    <div class="col-md-12 col-xs-12">
                        <div class="midleOrange textR">International Program</div>
                        <div class="row grayText">
                            <div class="col-md-1 col-md-offset-2 col-xs-1 col-xs-offset-2" style="font-size: 2em;" id="international_program">
                                30
                            </div>
                            <div class="col-md-1 col-md-offset-1 col-xs-1 col-xs-offset-1" style="font-size: 2em;">
                                }
                            </div>
                            <div class="col-md-7 col-xs-7">
                                <span id="international_program_for_undergraduate">10</span>
                                for Undergraduate<br>
                                <span id="international_program_for_graduate">20</span>
                                for Graduate</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid" style="background-color: #d27c46" align="center">
            <div class="span12">
                <div class="col-md-12 col-xs-12">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_4.png"/>" width="38" height="38">
                        <label>Employees</label>
                    </div>
                    <div id="${ns}chartEmployees"></div>
                    <div class="row textC">
                        <div class="col-md-2 col-xs-2"><img src="<c:url value="/resources/images/ICON_5.png"/>" width="109" height="80"></div>
                        <div class="col-md-3 col-xs-3">
                            <p class="orangeText" id="faculty_member_doctor_format">740</p>

                            <p class="grayText">Faculty Members</p>

                            <p class="smlBlue">(<span id="percent_faculty_member_doctor"/>% Doctorate Degrees)</p>
                        </div>
                        <div class="col-md-6 col-xs-6 col-md-offset-1 col-xs-offset-1">
                            <!-- absolute line  -->
                            <img src="<c:url value="/resources/images/line.png"/>">
                            <%--
                            <div class="progress" style="position:relative;top:2em;">
                                <div class="progress-bar" style="width: 4%;background-color: #a6a6a6;" id="factory_member_bachelor_lower_percent">4%</div>
                                <div class="progress-bar" style="width: 30%;background-color: #ffbf93;" id="factory_member_master_percent">30%</div>
                                <div class="progress-bar" style="width: 66%;background-color: #ff8837;" id="factory_member_doctoral_percent">66%</div>
                            </div>
                            --%>
                        </div>
                    </div>
                    <div class="row textC">
                        <div class="col-md-2 col-xs-2"><img src="<c:url value="/resources/images/ICON_6.png"/>" width="80" height="80"></div>
                        <div class="col-md-3 col-xs-3">
                            <p class="orangeText" id="faculty_member_format">139</p>

                            <p class="grayText" style="font-size: 1.5em;">Researchers</p>
                        </div>
                        <div class="col-md-6 col-xs-6 col-md-offset-1 col-xs-offset-1">
                            <!-- absolute line  -->
                            <img src="<c:url value="/resources/images/line.png"/>">
                            <%--
                           <div class="progress" style="position:relative;top:2em;">
                               <div class="progress-bar" style="width: 6%;background-color: #a6a6a6;" id="researcher_bachelor_lower_percent">6%</div>
                               <div class="progress-bar" style="width: 53%;background-color: #ffbf93;" id="researcher_master_percent">53%</div>
                               <div class="progress-bar" style="width: 41%;background-color: #ff8837;" id="researcher_doctoral_percent">41%</div>
                           </div>
                           --%>
                        </div>
                    </div>
                    <div class="row textC">
                        <div class="col-md-2 col-xs-2"><img src="<c:url value="/resources/images/ICON_7.png"/>" width="80" height="80"></div>
                        <div class="col-md-3 col-xs-3">
                            <p class="orangeText" id="full_time_format">1,386</p>

                            <p class="grayText">Full-time</p>

                            <p class="grayText">Support Staff</p>
                        </div>
                        <!-- absolute line  -->
                        <img src="<c:url value="/resources/images/line.png"/>">

                        <div class="col-md-6 col-xs-6 col-md-offset-1 col-xs-offset-1">
                            <%--
                               <div class="progress" style="position:relative;top:2em;">
                                   <div class="progress-bar" style="width: 71%;background-color: #a6a6a6;" id="full_time_bachelor_lower_percent">71%</div>
                                   <div class="progress-bar" style="width: 28%;background-color: #ffbf93;" id="full_time_master_percent">28%</div>
                                   <div class="progress-bar" style="width: 1%;background-color: #ff8837;" id="full_time_doctoral_percent">1%</div>
                               </div>
                               --%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="span4" style="border:1px;background-color: #d2368f" align="center">
        <div class="row-fluid">
            <div class="span12" style="background-color: #23d267" align="center">
                <div class="col-md-12 col-xs-12">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_11.png"/>" width="38" height="38">
                        <label>Budget</label>
                    </div>
                    <div class="smlBlue midle">Fiscal Year 2014</div>
                    <div class="block">
                        <div id="${ns}chartContainer1"></div>

                        <div id="${ns}chartContainer2"></div>
                        <%--
                        <div class="donut-chart">
                            <div id="porcion11" class="recorte">
                                <div class="quesito" data-rel="29"></div>
                            </div>
                            <div id="porcion21" class="recorte">
                                <div class="quesito" data-rel="5"></div>
                            </div>
                            <div id="porcion31" class="recorte">
                                <div class="quesito" data-rel="39"></div>
                            </div>
                            <div id="porcion4" class="recorte">
                                <div class="quesito" data-rel="12"></div>
                            </div>
                            <div id="porcionFin" class="recorte">
                                <div class="quesito" data-rel="15"></div>
                            </div>

                            <p class="center-date"></p>
                        </div>
                        --%>
                        <!-- text percent in pie -->

                        <!-- legend in pie -->
                        <div class="typeChart whiteText" style="top: 15%;left: 65%;">INCOMES</div>
                        <ul class="horizontal-list" style="top: 25%;left: 60%;" id="income_list">
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #ffbe01;"></div>
                                    <span>Government</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #f9d5bd;"></div>
                                    <span>External research grant</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #a6a6a6;"></div>
                                    <span>Tuition & fees</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #9bbb58;"></div>
                                    <span>Academic services</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #e46c0b;"></div>
                                    <span>Others</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="smlBlue midle textC">Incomes <span id="income_amount_format"></span> Baht</div>
                    <div class="block">
                        <div class="donut-chart">
                            <div id="porcion1" class="recorte">
                                <div class="quesito" data-rel="29"></div>
                            </div>
                            <div id="porcion2" class="recorte">
                                <div class="quesito" data-rel="5"></div>
                            </div>
                            <div id="porcion3" class="recorte">
                                <div class="quesito" data-rel="39"></div>
                            </div>
                            <div id="porcionFinish" class="recorte">
                                <div class="quesito" data-rel="15"></div>
                            </div>

                            <p class="center-date"></p>
                        </div>
                        <!-- text percent in pie -->

                        <!-- legend in pie -->
                        <div class="typeChart whiteText" style="top: 60%;left: 65%;">EXPENSES</div>
                        <ul class="horizontal-list" style="top: 70%;left: 60%;" id="expense_list">
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #ffbe01;"></div>
                                    <span>Salary & Wage</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #f9d5bd;"></div>
                                    <span>Operating Cost</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #a6a6a6;"></div>
                                    <span>Utility and Others</span>
                                </div>
                            </li>
                            <li>
                                <div class="">
                                    <div class="legend" style="background-color: #9bbb58;"></div>
                                    <span>Depreciations</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="smlBlue midle textC">Expense <span id="expense_amount_format"></span> Baht</div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12" style="background-color: #3f5dd2" align="center">
                <div class="col-md-12 col-xs-12">
                    <div class="title">
                        <img src="<c:url value="/resources/images/ICON_12.png"/>" width="38" height="38">
                        <label>Employability</label>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xs-6 textC">
                            <p class="orangeText" id="undergraduate">60-70%</p>

                            <p class="grayText midle">Undergraduate</p>
                        </div>
                        <div class="col-md-6 col-xs-6">
                            <img src="<c:url value="/resources/images/ICON_13.png"/>" width="70" height="70">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xs-6">
                            <img src="<c:url value="/resources/images/ICON_14.png"/>" width="118" height="70">
                        </div>
                        <div class="col-md-6 col-xs-6 textC">
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
        /*
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
         $("#undergraduate").html(employabilityM.undergraduate);
         $("#graduate").html(employabilityM.graduate);

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
         $("#factory_member_bachelor_lower_percent").html(employeeM.factory_member_bachelor_lower_percent);
         $("#factory_member_master_percent").html(employeeM.factory_member_master_percent);
         $("#factory_member_doctoral_percent").html(employeeM.factory_member_doctoral_percent);
         $("#researcher_bachelor_lower_percent").html(employeeM.researcher_bachelor_lower_percent);
         $("#researcher_master_percent").html(employeeM.researcher_master_percent);
         $("#researcher_doctoral_percent").html(programM.researcher_doctoral_percent);
         $("#full_time_bachelor_lower_percent").html(programM.full_time_bachelor_lower_percent);
         $("#full_time_master_percent").html(programM.full_time_master_percent);
         $("#full_time_doctoral_percent").html(programM.full_time_doctoral_percent);

         //studentM
         $("#noof_format").html(studentM.noof_format);
         $("#noof").html(studentM.noof);
         $("#percent_undergraduate").html(studentM.percent_undergraduate);
         $("#no_of_undergraduate_format").html(studentM.no_of_undergraduate_format);
         $("#no_of_undergraduate").html(studentM.no_of_undergraduate);
         $("#percent_graduate").html(studentM.percent_graduate);
         $("#no_of_graduate").html(studentM.no_of_graduate);
         $("#no_of_graduate_format").html(studentM.no_of_graduate_format);
         $("#percent_male").html(studentM.percent_male);
         $("#percent_female").html(studentM.percent_female);
         $("#no_of_student").html(studentM.no_of_student);
         $("#faculty_ratio").html(studentM.faculty_ratio);

         //budgetM
         var color_list=['#ffbe01','#f9d5bd','#a6a6a6','#9bbb58','#b3fff0','#4da6ff','##80ffff','#ff80bf','#d966ff','#ff8080','#9999ff'
         ,'#ffff66','#ffa64d','#bbbb77'];
         var income_list=budgetM.income_list;
         var expense_list=budgetM.expense_list;

         if(income_list!=null && income_list.length>0){
         var str="";
         for(var i=0;i<income_list.length;i++){

         str=str+"<li>"+
         "<div class=\"\"> "+
         " <div class=\"legend\" style=\"background-color: "+color_list[i]+";\"></div> "+
         "   <span>"+income_list[i][0]+"</span> "+
         "   </div>"+
         "</li>";
         }
         $("#income_list").html(str);
         }

         if(expense_list!=null && expense_list.length>0){
         var str="";
         for(var i=0;i<expense_list.length;i++){
         str=str+"<li>"+
         "<div class=\"\"> "+
         " <div class=\"legend\" style=\"background-color: "+color_list[i]+";\"></div> "+
         "   <span>"+expense_list[i][0]+"</span> "+
         "   </div>"+
         "</li>";
         }
         $("#expense_list").html(str);
         }
         $("#income_amount").html(budgetM.income_amount);
         $("#income_amount_format").html(budgetM.income_amount_format);
         $("#expense_amount").html(budgetM.expense_amount);
         $("#expense_amount_format").html(budgetM.expense_amount_format);


         }
         //alert(data.studentM.noof_format)
         }
         }
         });
         */
        var chart={
            "chart": {
                "caption": "Split of Revenue by Product Categories",
                "subCaption": "Last year",
                "numberPrefix": "$",
                "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
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

        var bar1Employee={
            "chart": {
                "caption": "Product-wise quarterly revenue in current year",
                "subCaption": "Harry's SuperMart",
                "xAxisname": "Quarter",
                "yAxisName": "Revenue (In USD)",
                "numberPrefix": "$",
                "paletteColors": "#0075c2,#1aaf5d",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "showCanvasBorder": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "legendBorderAlpha": "0",
                "legendShadow": "0",
                "valueFontColor": "#ffffff",
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",
                "divLineDashed": "1",
                "showAlternateVGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14",
                "showHoverEffect": "1"
            },
            "categories": [
                {
                    "category": [
                        {
                            "label": "Q1"
                        },
                        {
                            "label": "Q2"
                        },
                        {
                            "label": "Q3"
                        }
                    ]
                }
            ],
            "dataset": [
                {
                    "seriesname": "Food Products",
                    "data": [
                        {
                            "value": "121000"
                        },
                        {
                            "value": "135000"
                        },
                        {
                            "value": "123500"
                        }
                    ]
                },
                {
                    "seriesname": "Non-Food Products",
                    "data": [
                        {
                            "value": "131400"
                        },
                        {
                            "value": "154800"
                        },
                        {
                            "value": "98300"
                        }
                    ]
                },
                {
                    "seriesname": "Drink ",
                    "data": [
                        {
                            "value": "130000"
                        },
                        {
                            "value": "1500"
                        },
                        {
                            "value": "9800"
                        }
                    ]
                }
            ]
        };

        var bar1={
            "chart": {
                "caption": "Product-wise quarterly revenue in current year",
                "subCaption": "Harry's SuperMart",
                "xAxisname": "Quarter",
                "yAxisName": "Revenue (In USD)",
                "numberPrefix": "$",
                "paletteColors": "#0075c2,#1aaf5d",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "showCanvasBorder": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "legendBorderAlpha": "0",
                "legendShadow": "0",
                "valueFontColor": "#ffffff",
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",
                "divLineDashed": "1",
                "showAlternateVGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14",
                "showHoverEffect": "1"
            },
            "categories": [
                {
                    "category": [
                        {
                            "label": "Q1"
                        }
                    ]
                }
            ],
            "dataset": [
                {
                    "seriesname": "Food Products",
                    "data": [
                        {
                            "value": "121000"
                        }
                    ]
                },
                {
                    "seriesname": "Non-Food Products",
                    "data": [
                        {
                            "value": "131400"
                        }
                    ]
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
            "type": "doughnut3d",
            "renderAt": "${ns}chartContainer2",
            "width": "100%", // 500
            "height": "300", // chartHeight,
            "dataFormat": "json",
            "dataSource":chart

        });
        revenueChart2.render();

        var revenueChart_Program_bar = new FusionCharts({
            "type": "stackedbar2d",
            "renderAt": "${ns}chartProgram",
            "width": "100%", // 500
            "height": "300", // chartHeight,
            "dataFormat": "json",
            "dataSource":bar1

        });
        revenueChart_Program_bar.render();

        var revenueChart_Employee_bar = new FusionCharts({
            "type": "stackedbar2d",
            "renderAt": "${ns}chartEmployees",
            "width": "100%", // 500
            "height": "300", // chartHeight,
            "dataFormat": "json",
            "dataSource":bar1Employee

        });
        revenueChart_Employee_bar.render();
    });
</script>
</body>
</html>
