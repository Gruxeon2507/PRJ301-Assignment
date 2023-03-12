<%-- 
    Document   : ClassList
    Created on : Mar 9, 2023, 10:20:59 PM
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
        <ul>
            <c:forEach items="${requestScope.groups}" var="g">
                <li><a href="/Project/instructor/status?groupName=${g.name}&courseId=${g.course.id}">${g.name}-${g.course.id}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
