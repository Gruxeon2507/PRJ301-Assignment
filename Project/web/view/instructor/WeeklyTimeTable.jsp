<%-- 
    Document   : list.jsp
    Created on : Mar 3, 2023, 4:40:24 PM
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
            .schedule table tr td{
                width: 150px;
                text-align: center;
                height: 130px;
            }
            .schedule table th td{
                height: 80px;
            }
            .isHavingClass{
                color: white;
                background-color: orange;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="side-bar">
                <a href="#">Weekly Timetable</a>
                <a href="list">View Student Attendance Status</a>
            </div>
            <div class="schedule">
                <table border="1px">
                    <thead>
                    <td><form action="weeklyTimeTable" method="POST">
                            Nhập tên giảng viên: <input type="text" name="instuctorId" value="${requestScope.instructorId}"><br>
                            Chọn ngày: <input type="date" name="Date" id="Date" value="${requestScope.date}"><br>
                            <input type="submit" value="search">
                        </form></td>
                        <c:forEach items="${requestScope.days}" var="d">
                            <c:if test="${d.dateCount eq 0}"><td>Mon<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 1}"><td>Tue<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 2}"><td>Wed<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 3}"><td>Thu<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 4}"><td>Fri<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 5}"><td>Sat<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
                        <c:if test="${d.dateCount eq 6}"><td>Sun<br><fmt:formatDate pattern = "dd-MM-yyyy" value = "${d.date}" /></td></c:if>
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
                                                    <p class="subject">${s.group.course.id}</p>
                                                    <p class="className">${s.group.name}</p>
                                                    <p class="location">at ${s.room.id}</p>
                                                    <p class="attend_status">
                                                        <c:if test="${s.status}">
                                                            attended
                                                        </c:if>
                                                        <c:if test="${!s.status}">
                                                            not-yet<br>
                                                            <a href="takeAttendance?groupName=${s.group.name}&courseId=${s.group.course.id}">take attendance</a>
                                                        </c:if>
                                                    </p>
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
