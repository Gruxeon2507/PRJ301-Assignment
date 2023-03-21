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
import controller.authentication.BaseRequiredAuthenticatedControllerInstructor;
import model.AbsentStudent;
import model.User;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class StatusController extends BaseRequiredAuthenticatedControllerInstructor {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String groupName = req.getParameter("groupName");
        String groupId = req.getParameter("groupId");
        String courseId = req.getParameter("courseId");
        String instructorId = ((User)req.getSession().getAttribute("user")).getUsername();
        AttendDBContext attendDb = new AttendDBContext();
        AttendDBContext attendDb1 = new AttendDBContext();
        SessionDBContext sessionDb = new SessionDBContext();
        ParticipateDBContext participateDb = new ParticipateDBContext();
        ArrayList<Student> students = participateDb.getClass(Integer.parseInt(groupId), instructorId, courseId);
        ArrayList<Attend> status = attendDb.status(groupName, courseId, instructorId);
        ArrayList<Session> sessions = sessionDb.getInstructor(courseId, instructorId);
        ArrayList<AbsentStudent> absentStudents = attendDb1.getAbsent(Integer.parseInt(groupId));
        float totalSlot = sessions.size();
        for (AbsentStudent a : absentStudents) {
            a.setNoSlot((int) (a.getNoSlot() / totalSlot * 100));
            resp.getWriter().println(a.getNoSlot());
        }
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        req.setAttribute("currentdate", currentDate);
        req.setAttribute("username", user.getUsername());
        req.setAttribute("status", status);
        req.setAttribute("sessions", sessions);
        req.setAttribute("coursename", courseId);
        req.setAttribute("groupname", groupName);
        req.setAttribute("students", students);
        req.setAttribute("userid", user.getDisplayname());
        req.setAttribute("absent", absentStudents);
        req.getRequestDispatcher("../view/instructor/AttendanceStatus.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
        ArrayList<Session> sessions = new SessionDBContext().getInstructor("PRJ301", "sonnt5");
        System.out.println(sessions.size());
    }
}
