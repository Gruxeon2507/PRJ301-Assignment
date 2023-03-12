/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.instructor;

import dal.DBContext;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Session;
import java.util.Calendar;
import util.MondayAndSundayOfWeek;
import java.sql.Date;
import model.Day;
import model.TimeSlot;
import controller.authentication.BaseRequiredAuthenticatedControllerInstructor;
import model.User;
/**
 *
 * @author Nguyen Hoang Minh
 */
public class WeeklyTimeTableController extends BaseRequiredAuthenticatedControllerInstructor {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        DBContext<Session> db = new SessionDBContext();
//        ArrayList<Session> sessions = db.all();
//        request.setAttribute("sessions", sessions);
//        request.getRequestDispatcher("view/list.jsp");
//
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response,User user)
            throws ServletException, IOException {
        String instructorId = request.getParameter("instuctorId");
        String raw_date = request.getParameter("Date");
//        response.getWriter().print(instructorId+raw_date);
        SessionDBContext db = new SessionDBContext();
        TimeSlotDBContext db2 = new TimeSlotDBContext();
        Date monday = MondayAndSundayOfWeek.getMonday(raw_date);
        Date sunday = MondayAndSundayOfWeek.getSunday(raw_date);
//        response.getWriter().println(monday.toString() + sunday.toString());
        ArrayList<Day> days = MondayAndSundayOfWeek.getWholeWeekFromDate(monday);
        ArrayList<TimeSlot> timeslots = db2.all();
        ArrayList<Session> sessions = db.getStatusSession(instructorId,monday,sunday);
//        response.getWriter().println(days.size()+" 2 3 4 5 ");
//        for(Day d: days){
//            response.getWriter().println(d.getDate()+" "+d.getDateCount());
//        }
        request.setAttribute("instructorId", instructorId);
        request.setAttribute("date", raw_date);
        request.setAttribute("timeslots", timeslots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("days", days);
        request.getRequestDispatcher("../view/instructor/WeeklyTimeTable.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response,User user)
        throws ServletException, IOException {
        String instructorId = request.getParameter("instuctorId");
        String raw_date = request.getParameter("Date");
        SessionDBContext db = new SessionDBContext();
        TimeSlotDBContext db2 = new TimeSlotDBContext();
        Date monday = MondayAndSundayOfWeek.getMonday(raw_date);
        Date sunday = MondayAndSundayOfWeek.getSunday(raw_date);
        ArrayList<Day> days = MondayAndSundayOfWeek.getWholeWeekFromDate(monday);
        ArrayList<TimeSlot> timeslots = db2.all();
        ArrayList<Session> sessions = db.getStatusSession(instructorId,monday,sunday);
        request.setAttribute("instructorId", instructorId);
        request.setAttribute("date", raw_date);
        request.setAttribute("timeslots", timeslots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("days", days);
        request.getRequestDispatcher("../view/instructor/WeeklyTimeTable.jsp").forward(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
