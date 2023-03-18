<%-- 
    Document   : AttendanceStatus
    Created on : Mar 18, 2023, 1:28:18 PM
    Author     : Nguyen Hoang Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                height: 50px;
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
                height: 5%;
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin-right: 10px
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
                background-color: whitesmoke;
                padding:2px;
                color:black;
                border:1px solid;
                margin-top: 10px;
            }
            .title{
                text-align: center;
            }
            .return-button a{
                background-color: #5cb85c;
                text-decoration: none;
                color:white;
                padding: 6px;
                margin-left: 3px;
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

                    <a href="#">Weekly Timetable</a>
                </li>
                <li>

                    <a href="list">View Student Attendance Status</a>
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
                <div class="return-button">
                    <a href="list">Back To Previous Page</a>
                </div>
                <div class="title">
                    <h1>Attendance Status For ${requestScope.displayname} on ${requestScope.courseid}</h1>
                </div>

                <table>
                    <thead>
                    <th>NO</th>
                    <th>DATE</th>
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP NAME</th>
                    <th>ATTENDANCE STATUS</th>
                    <th>INSTRUCTOR'S COMMENT</th>
                    </thead>
                    <c:forEach items="${requestScope.sessions}" var="s" varStatus="index">
                        <tr>
                            <td>${index.index+1}</td>
                            <td>${s.date}</td>
                            <td>${s.slot.slotNumber}</td>
                            <td>${s.room.id}</td>
                            <td>${s.instructor.id}</td>
                            <td>${s.group.name}</td>
                            <td>
                                <c:if test="${s.status}">
                                    <c:forEach items="${requestScope.attends}" var="a">
                                        <c:if test="${s.id eq a.session.id}">
                                            <c:if test="${a.status}">
                                                <span class="attended">attended</span>
                                            </c:if>
                                            <c:if test="${!a.status}">
                                                <span class="not-yet">absent</span>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${!s.status}">
                                    future
                                </c:if>    
                            </td>
                            <td>
                                <c:forEach items="${requestScope.attends}" var="a">
                                    <c:if test="${s.id eq a.session.id}">
                                        ${a.comment}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr> <td colspan="8"><p> you have absent ${requestScope.absent}% so far</p></td>
                    </tr>
                </table>
            </div>
        </div>

    </body>
</html>
