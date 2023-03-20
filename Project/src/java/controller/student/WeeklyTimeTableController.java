/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Day;
import model.Session;
import model.TimeSlot;
import model.User;
import util.MondayAndSundayOfWeek;
import controller.authentication.BaseRequiredAuthenticatedControllerStudent;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class WeeklyTimeTableController extends BaseRequiredAuthenticatedControllerStudent {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String studentId = user.getUsername();
        String raw_date = request.getParameter("Date");
        SessionDBContext db = new SessionDBContext();
        TimeSlotDBContext db2 = new TimeSlotDBContext();
        Date monday = MondayAndSundayOfWeek.getMonday(raw_date);
        Date sunday = MondayAndSundayOfWeek.getSunday(raw_date);
        ArrayList<Day> days = MondayAndSundayOfWeek.getWholeWeekFromDate(monday);
        ArrayList<TimeSlot> timeslots = db2.all();
        ArrayList<Session> sessions = db.getStudentSession(studentId, monday, sunday);
        request.setAttribute("studentId", studentId);
        request.setAttribute("date", raw_date);
        request.setAttribute("timeslots", timeslots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("days", days);
        request.setAttribute("userid", user.getDisplayname());
        java.sql.Date currentDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        request.setAttribute("userid", user.getDisplayname());
        request.setAttribute("currentdate", currentDate);
        request.getRequestDispatcher("../view/student/WeeklyTimeTable.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String studentId = user.getUsername();
        String raw_date = request.getParameter("Date");
                        java.sql.Date currentDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        request.setAttribute("userid", user.getDisplayname());
        request.setAttribute("currentdate", currentDate);
        SessionDBContext db = new SessionDBContext();
        TimeSlotDBContext db2 = new TimeSlotDBContext();
        Date monday = MondayAndSundayOfWeek.getMonday(raw_date);
        Date sunday = MondayAndSundayOfWeek.getSunday(raw_date);
        ArrayList<Day> days = MondayAndSundayOfWeek.getWholeWeekFromDate(monday);
        ArrayList<TimeSlot> timeslots = db2.all();
        ArrayList<Session> sessions = db.getStudentSession(studentId, monday, sunday);

        request.setAttribute("userid", user.getDisplayname());
        request.setAttribute("studentId", studentId);
        request.setAttribute("date", raw_date);
        request.setAttribute("timeslots", timeslots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("days", days);
        
        request.getRequestDispatcher("../view/student/WeeklyTimeTable.jsp").forward(request, response);
    }

}
