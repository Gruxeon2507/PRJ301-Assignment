<%-- 
    Document   : TakeAttendance
    Created on : Mar 10, 2023, 11:29:45 AM
    Author     : Nguyen Hoang Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="takeAttendance">
            <table>
                <thead>
                <td>Student Code</td>
                <td>Image</td>
                <td>Name</td>
                <td>Attendance Status</td>
                <td>Note</td>
                </thead>
                <c:forEach items="${requestScope.students}" var="s" varStatus="i">
                    <tr>
                        <td>${s.id}
                        <input type="text" hidden name="name${i.index}" value="${s.id}"></td>
                        <td>${s.image}</td>
                        <td>${s.name}</td>
                        <td>
                            <input type="radio" name="status${i.index}" value="true" checked>presented
                            <input type="radio" name="status${i.index}" value="false" >absent
                        </td>
                        <td>
                            <input type="input" name="comment${i.index}">
                        </td>
                    </tr>
                </c:forEach>
                    
            </table>
            <input type="text" hidden value="${requestScope.sessionId}" name ="sessionid">
            <input type="submit" value="save">
        </form>
    </body>
</html>
    