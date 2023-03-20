<%-- 
    Document   : CourseList
    Created on : Mar 16, 2023, 9:46:17 PM
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
                height: 100px;
            }
            td, th{
                text-align: center;
                width: 170px;
                border:1px solid;
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
            .title{
                text-align: center;
            }
            .header{
                height: 10%;
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin-right: 10px
            }
            .no{
                width: 50px;
            }
            .group{
                width: 600px;
            }
            .group1{
                width: 600px;
                text-align: left;
            }
            .group1 a{
                text-decoration: none;
                padding-left:10px;
            }
            tr{
                height:50px;
            }
            th{
                background-color: #4d90fe;
                text-align: center;
            }
            .table{
                display: flex;
                justify-content: center;
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
                        <button class="dropbtn">${requestScope.username}</button>
                        <div class="dropdown-content">
                            <a href="#">Setting</a>
                            <a href="../logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="schedule">
                <div class="title"><h1>Choose Group</h1></div>
                <div class="table">
                    <table>
                        <thead>
                        <th class="no">NO</th>
                        <th class="group">Group</th>
                        </thead>

                        <c:forEach items="${requestScope.groups}" var="g" varStatus="index">
                            <tr>
                                <td class="no">${index.index+1}</td>
                                <td class="group1"><a href="/Project/student/status?studentid=${requestScope.userid}&courseid=${g.course.id}">${g.course.id} - ${g.course.name} - ${g.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>


            </div>
        </div>
    </body>
</html>
