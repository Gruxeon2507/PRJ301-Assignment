<%-- 
    Document   : list.jsp
    Created on : Mar 3, 2023, 4:40:24 PM
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
        <form action="../weeklyTimeTable" method="POST">
            Nhập tên giảng viên: <input type="text" name="instuctorId" required><br>
            Chọn ngày: <input type="date" name="Date" id="Date" required><br>
            <input type="submit" value="search">
        </form>
        <table>
            <thead>
                <tr>
                    <c:forEach items="${requestScope.session.days}" var="d">
                        <c:if test="${requestScope.days.dateCount eq 0}"><td>Mon<br>${requestScope.days.date}</td></c:if>
                    <td>Tue<br>21/02/2023</td>
                    <td>Wed<br>22/02/2023</td>
                    <td>Thu<br>23/02/2023</td>
                    <td>Fri<br>24/02/2023</td>
                    <td>Sat<br>25/02/2023</td>
                    <td>Sun<br>26/02/2023</td>
                    </c:forEach>
                </tr>
            </thead>
        </table>
        <script>
            document.getElementById("Date").defaultValue = new Date().toDateInputValue();
        </script>
    </body>
</html>
