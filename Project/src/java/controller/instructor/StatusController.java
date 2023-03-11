/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import dal.AttendDBContext;
import dal.ParticipateDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Attend;
import model.Session;
import model.Student;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class StatusController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName =  (String)req.getParameter("groupName");
        String courseId = (String)req.getParameter("courseId");
        String instructorId = "sonnt5";
        AttendDBContext attendDb = new  AttendDBContext();
        SessionDBContext sessionDb = new SessionDBContext();
        ParticipateDBContext participateDb = new ParticipateDBContext();
        ArrayList<Student> students = participateDb.getClass(groupName, instructorId, courseId);
        ArrayList<Attend> status = attendDb.status(groupName, courseId, instructorId);
        ArrayList<Session> sessions = sessionDb.get(courseId, instructorId);
        req.setAttribute("status", status);
        req.setAttribute("sessions", sessions);
        req.setAttribute("coursename", courseId);
        req.setAttribute("groupname",groupName);
       req.setAttribute("students", students);
        req.getRequestDispatcher("/view/instructor/AttendanceStatus.jsp").forward(req, resp);
    }
    
}
