/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequiredAuthenticatedControllerStudent;
import dal.AttendDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Attend;
import model.Session;
import model.User;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class StatusController extends BaseRequiredAuthenticatedControllerStudent {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String studentId = req.getParameter("studentid");
        String courseId = req.getParameter("courseid");
        SessionDBContext sessionDb = new SessionDBContext();
        AttendDBContext attendDb = new AttendDBContext();
        ArrayList<Session> sessions = sessionDb.getStudentSessionByStudentId(studentId, courseId);
        ArrayList<Attend> attends = attendDb.getStudentAttendedSessions(studentId, courseId);

        req.setAttribute("sessions", sessions);
        req.setAttribute("attends", attends);
        int absentcount = 0;
        for (Attend attend : attends) {
            if (!attend.isStatus()) {
                absentcount++;
            }
        }
        java.sql.Date currentDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        req.setAttribute("currentdate", currentDate);
        req.setAttribute("displayname", user.getDisplayname());
        req.setAttribute("courseid", courseId);
        req.setAttribute("userid", user.getDisplayname());
        req.setAttribute("absent", (float) absentcount / sessions.size() * 100);
        req.getRequestDispatcher("../view/student/AttendanceStatus.jsp").forward(req, resp);
    }

}
