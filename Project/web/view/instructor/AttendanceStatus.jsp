<%-- 
    Document   : AttendanceStatus
    Created on : Mar 8, 2023, 1:02:07 PM
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
        <style>
            img{
                width: 100px;
                border-radius: 10px;
            }
            table{
                text-align: center;
                border-collapse: collapse;
                grid-column: 2 / 3;
                display: flex;
                flex-direction: column;
                margin: 20px;
                text-align: center;

            }

            .main_content{
                width: 80%;
                display: flex;
                flex-direction: column;
            }
            body{
                display: flex;
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
                height: 870px;
            }
            .side-nav {
                grid-column: 1 / 2;
                grid-row: 1 / 4;
                display: flex;
                justify-content: space-between;
                flex-direction: column;
                padding: 24px;
                border-right: 1px solid #dadce0;
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
            .save-button{
                text-align: center;
            }
            .save-button input{
                font-size:130%;
            }
            td,th{
                width: 45px;
                border: 1px solid;
            }
            .studentName{
                width: 200px;
                text-align: left;
            }
            .studentId{
                width: 100px;
            }
            .missed-col{
                width: 100px;
            }
            .title{
                text-align: center;
            }
            th{
                background-color: #66ccff;
            }
            .table{
                display: flex;
                justify-content: center;
            }
            .header{
                height: 10%;
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin-right: 10px
            }
            .absent{
                background-color: red;
                color: white;
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
        <div class="side_nav">
            <div class="logo">
                <img src="https://hcmuni.fpt.edu.vn/Data/Sites/1/skins/default/img/og-image.png" alt="">
            </div>
            <ul class="nav-links">
                <li>

                    <a href="weeklyTimeTable?Date=${requestScope.currentdate}&instuctorId=${requestScope.username}">Weekly Timetable</a>
                </li>
                <li>

                    <a href="list">View Student Attendance Status</a>
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
                <div class="return-button">
                    <a href="list">Back To Previous Page</a>
                </div>
                <div class="title">
                    <h1>Attendance Status of ${requestScope.groupname} on ${requestScope.coursename}</h1>
                </div>
                <div class="table">
                    <table>
                        <thead>
                        <th class="studentName">Student Name</th>
                        <th class="studentId">Role Number</th>
                        <th class="missed-col">% Absent</th>
                            <c:forEach items="${requestScope.sessions}" var="s" varStatus="i">
                                <c:if test="${s.group.name eq requestScope.groupname}">
                                <th>${i.index+1} <br>
                                    <fmt:formatDate pattern="dd-MM" value="${s.date}"></fmt:formatDate>
                                </th>

                            </c:if>
                        </c:forEach>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.students}" var="s">
                                <tr>
                                    <td class="studentName">${s.name}</td>
                           
                                    <td class="studentId">${s.id}</td>

                                    <c:forEach items="${requestScope.absent}" var="a">
                                        <c:if test="${s.id eq a.studentId}">
                                            <td class="missed-col<c:if test="${a.noSlot >= 20.00}"> absent</c:if>">
                                                ${a.noSlot}%
                                            </td>
                                        </c:if>
                                    </c:forEach>

                                    <c:forEach items="${requestScope.sessions}" var="se" varStatus="i">

                                        <c:if test="${se.group.name eq requestScope.groupname}">
                                            <td class="slot">
                                                <c:forEach items="${requestScope.status}" var="a">
                                                    <c:if test="${a.session.id eq se.id}">
                                                        <c:if test="${a.student.id eq s.id}">
                                                            <c:if test="${a.status}">
                                                                <span style="color:green;">P</span>
                                                            </c:if> 
                                                            <c:if test="${!a.status}">
                                                                <span style="color:red;">A</span>
                                                            </c:if> 
                                                        </c:if>
                                                    </c:if>

                                                </c:forEach>
                                            </td>       
                                        </c:if>

                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>



    </body>
</html>
