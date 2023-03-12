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
        </style>
    </head>
    <body>
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
            <input type="submit" value="update">
        </form>
    </body>
</html>
