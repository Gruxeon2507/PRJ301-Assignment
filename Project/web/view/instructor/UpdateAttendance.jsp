<%-- 
    Document   : UpdateAttendance
    Created on : Mar 12, 2023, 3:54:08 PM
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
            img{
                width: 100px;
                border-radius: 10px;
            }

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
            tr{
                height: 100px;
            }
            td{
                /*                text-align: center;*/
                width: 250px;
                border:1px solid;
            }
            .header{
                height: 10%;
                display: flex;
                justify-content: center;
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
        </style>
    </head>
    <body>
        <div class="side_nav">
            <div class="logo">
                <img src="https://hcmuni.fpt.edu.vn/Data/Sites/1/skins/default/img/og-image.png" alt="">
            </div>

        </div>
        <div class="main_content">
            <div class="header">
                <h1>Update Attendance for ${requestScope.groupname} ${requestScope.coursename}</h1>
            </div>

            <div class="schedule">
                <form method="POST" action="updateAttendance">
                    <table>
                        <thead>
                        <td>Student Code</td>
                        <td>Image</td>
                        <td>Name</td>
                        <td>Attendance Status</td>
                        <td>Note</td>
                        </thead>
                        <c:forEach items="${requestScope.attends}" var="a" varStatus="i">
                            <tr>
                                <td>${a.student.id}
                                    <input type="text" hidden name="name${i.index}" value="${a.student.id}"></td>
                                <td><img src="${a.student.image}" class="image" onerror="this.src='https://media.istockphoto.com/id/1214428300/vector/default-profile-picture-avatar-photo-placeholder-vector-illustration.jpg?s=612x612&w=0&k=20&c=vftMdLhldDx9houN4V-g3C9k0xl6YeBcoB_Rk6Trce0=';"></td>
                                <td>${a.student.name}</td>
                                <td>
                                    <input type="radio" name="status${i.index}" value="true" 
                                           <c:if test="${a.status}">checked</c:if>
                                               >presented
                                           <input type="radio" name="status${i.index}" value="false" 
                                           <c:if test="${!a.status}">checked</c:if>
                                               >absent
                                    </td>
                                    <td>
                                        <input type="input" name="comment${i.index}" value="${a.comment}">
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                    <input type="text" hidden value="${requestScope.sessionid}" name ="sessionid">
                    <div class="save-button">
                        <input type="submit" value="update" >
                    </div>
                </form>
            </div>
        </div>


    </body>
</html>
