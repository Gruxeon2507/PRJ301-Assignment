<%-- 
    Document   : AttendanceStatus
    Created on : Mar 8, 2023, 1:02:07 PM
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
        <h1>Attendance Status of ${requestScope.groupname} on ${requestScope.coursename}</h1>
        <table>
            <thead>
            <th class="name-col">Student Name</th>
            <th>Role Number</th>
            <td>% Absent</td>
            <c:forEach items="${requestScope.sessions}" var="s" varStatus="i">
                <c:if test="${s.group.name eq requestScope.groupname}">
                    <th>${i.index}
                    </th>

                </c:if>
            </c:forEach>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.name}</td>
                    <td>${s.id}</td>
                    <td class="missed-col">
                        <c:forEach items="${requestScope.absent}" var="a">
                            <c:if test="${s.id eq a.studentId}">
                            ${a.noSlot}%
                            </c:if>
                        </c:forEach>
                    </td>
                    <c:forEach items="${requestScope.sessions}" var="se" varStatus="i">

                        <c:if test="${se.group.name eq requestScope.groupname}">

                            <c:forEach items="${requestScope.status}" var="a">
                                <c:if test="${a.session.id eq se.id}">
                                    <c:if test="${a.student.id eq s.id}">
                                        <td><input type="checkbox" <c:if test="${a.status}">
                                                   checked
                                                </c:if> disabled></td>
                                        </c:if>
                                    </c:if>

                            </c:forEach>

                        </c:if>

                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</body>
</html>
