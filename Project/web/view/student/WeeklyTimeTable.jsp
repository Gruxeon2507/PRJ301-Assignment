<%-- 
    Document   : WeeklyTimeTable
    Created on : Mar 12, 2023, 1:40:35 PM
    Author     : Nguyen Hoang Minh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="WeeklyTimeTable.css">
        <style>
            table{
                text-align: center;
                border-collapse: collapse;
                grid-column: 2 / 3;
                display: flex;
                flex-direction: column;
                margin: 20px;
                text-align: center;

            }
            input{
                width:130px
            }
            .main_content{
                width: 80%;
            }
            body{
                display: flex;
            }
            tr{
                height: 120px;
            }
            td{
                text-align: center;
                width: 170px;
                border:1px solid;
            }
            th{
                text-align: center;
                width: 170px;
                border:1px solid;
                background-color: #99ccff;
            }
            .header{
                height: 10%;
                display: flex;
                justify-content: flex-end;
                align-items: center;

            }
            p{
                padding: 0;
                margin: 5px;
            }
            .side_nav {
                width: 20%;
                grid-column: 1 / 2;
                grid-row: 1 / 4;
                display: flex;
                justify-content: space-between;
                flex-direction: column;
                padding: 24px;
                border-right: 1px solid #dadce0;
            }
            .side-nav {
                grid-column: 1 / 2;
                grid-row: 1 / 4;
                display: flex;
                justify-content: space-between;
                flex-direction: column;
                padding: 24px;
                border-right: 1px solid #dadce0;
                height: 870px;
            }

            li {
                display: flex;
                align-items: center;
                gap: 8px;
                padding-bottom: 16px;
            }

            li > a {
                text-decoration: none;
                font-weight: bold;
                color: black;
            }

            li > a:hover {
                color: #314AE7;
                cursor: pointer;
            }
            /* Dropdown Button */
            .dropbtn {

                padding: 16px;
                font-size: 16px;
                border: none;
            }

            /* The container <div> - needed to position the dropdown content */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            /* Dropdown Content (Hidden by Default) */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Links inside the dropdown */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .logo img{
                width: 100%;
            }
            /* Change color of dropdown links on hover */
            .dropdown-content a:hover {
                background-color: #ddd;
            }

            /* Show the dropdown menu on hover */
            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Change the background color of the dropdown button when the dropdown content is shown */
            .dropdown:hover .dropbtn {
                background-color: white;
            }
            .attended{
                color:green;
            }
            .not-yet{
                color:red;
            }
            .attend_status a{
                text-decoration: none;
/*                background-color: whitesmoke;
                padding:2px;
                color:black;
                border:1px solid;
                margin-top: 10px;*/
            }
            .header{
                height: 8%;
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin-right: 10px
            }
            .hours{
                display: inline;
                padding: .2em .6em .3em;
                font-size: 75%;
                font-weight: 700;
                line-height: 1;
                color: #fff;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: .25em;
                background-color: #5cb85c;
            }
            .isHavingClass{
                text-align: left;
            }
            .label {
                display: inline;
                padding: .2em .6em .3em;
                font-size: 75%;
                font-weight: 700;
                line-height: 1;
                color: #fff;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: .25em;
            }
            .attendance{
                background-color: #f0ad4e;
            }
            .className{
                background-color: #4d90fe;
            }
            .flm{
                background-color: #f0ad4e;
                text-decoration: none;
            }
  
        </style>
    </head>
    <body>
        <div class="side_nav" style="height:870px;">
            <div class="logo">
                <img src="https://hcmuni.fpt.edu.vn/Data/Sites/1/skins/default/img/og-image.png" alt="">
            </div>
            <ul class="nav-links">
                <li>

                    <a href="weeklyTimeTable?Date=${requestScope.currentdate}">Weekly Timetable</a>
                </li>
                <li>

                    <a href="list">Attendance Report</a>
                </li>
                <li>

                    <a href="..\logout">Logout</a>
                </li>

            </ul>

            <div class="storage">
                <a href="#">By Khieu Minh Duc</a>  
            </div>
        </div>
        
        <div class="main_content">
            <div class="header">
                <div class="user-menu">
                    <div class="dropdown">
                        <button class="dropbtn">${requestScope.userid}</button>
                        <div class="dropdown-content">
                            <a href="#">Setting</a>
                            <a href="../logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="schedule">
                <table>
                    <thead>
                    <th><form action="weeklyTimeTable" method="GET">
                            <input type="date" name="Date" id="Date" value="${requestScope.date}"><br>
                            
                            <input type="submit" value="search">
                        </form></th>
                        <c:forEach items="${requestScope.days}" var="d">
                            <c:if test="${d.dateCount eq 0}"><th>Mon<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 1}"><th>Tue<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 2}"><th>Wed<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 3}"><th>Thu<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 4}"><th>Fri<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 5}"><th>Sat<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        <c:if test="${d.dateCount eq 6}"><th>Sun<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></th></c:if>
                        </c:forEach>
                    </thead>
                    </tr>
                    <!--                <tr>
                                        <td>slot 1 <br> (7h30-9h50)</td>
                    <c:forEach items="${requestScope.sessions}" var="s">
                        <c:forEach items="${requestScope.days}" var="d">
                            
                        </c:forEach>
                    </c:forEach>
                </tr>-->
                    <c:forEach items="${requestScope.timeslots}" var ="t">
                        <tr>
                            <td>Slot ${t.slotNumber}</td>
                            <c:forEach items="${requestScope.days}" var="d">
                                <td>

                                    <c:forEach items="${requestScope.sessions}" var="s">
                                        <c:if test="${s.date eq d.date}">
                                            <c:if test="${s.slot.slotNumber eq t.slotNumber}">
                                                <div class="isHavingClass">
                                                    <p class="subject" style="font-weight: bold;">${s.group.course.id} at ${s.room.id}</p>
                                                    
                                                    <p class="className label">${s.group.name}</p>
                                                    <p class="attend_status">
                                                        <c:if test="${s.status}">
                                                            (<span class="attended">attended </span>)
                                                        </c:if>
                                                        <c:if test="${!s.status}">
                                                            (<span class="not-yet">not-yet </span>)
                                                        </c:if>
                                                    </p>
                                                    <a class="label flm" href="https://flm.fpt.edu.vn/DefaultSignin">View material</a><br>
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>


                </table>
            </div>
        </div>

    </body>
</html>
