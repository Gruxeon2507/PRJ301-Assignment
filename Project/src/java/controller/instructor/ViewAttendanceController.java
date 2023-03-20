/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerInstructor;
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
import model.User;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class ViewAttendanceController extends BaseRequiredAuthenticatedControllerInstructor{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp,User user) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp,User user) throws ServletException, IOException {
        String groupId = req.getParameter("groupId");
        String groupName = req.getParameter("groupName");
        String courseId = req.getParameter("courseId");
        int sessionId=Integer.parseInt(req.getParameter("sessionId"));
        String instructorId = user.getUsername();
        
        AttendDBContext attendDb = new AttendDBContext();
        SessionDBContext sessionDb = new SessionDBContext();
        ParticipateDBContext participateDb = new ParticipateDBContext();
        ArrayList<Student> students = participateDb.getClass(Integer.parseInt(groupId), instructorId, courseId);
        ArrayList<Session> sessions = sessionDb.getInstructor(courseId, instructorId);
        ArrayList<Attend> attends = attendDb.getAttendedSession(Integer.parseInt(groupId), courseId, instructorId, sessionId);
        
        req.setAttribute("sessionid", sessionId);
        req.setAttribute("attends", attends);
        req.setAttribute("sessions", sessions);
        req.setAttribute("coursename", courseId);
        req.setAttribute("groupname", groupName);
        req.setAttribute("students", students);
        req.setAttribute("sessions", sessions);
        req.setAttribute("userid", user.getDisplayname());
        req.getRequestDispatcher("../view/instructor/ViewAttendance.jsp").forward(req, resp);
    }
    
}
