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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import model.Attend;
import model.Session;
import model.Student;
import model.User;
import controller.authentication.BaseRequiredAuthenticatedControllerInstructor;
import model.User;
/**
 *
 * @author Nguyen Hoang Minh
 */
public class UpdateAttendanceController extends BaseRequiredAuthenticatedControllerInstructor{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp,User user) throws ServletException, IOException {
        ArrayList<Attend> attends = new ArrayList<>();
        int i = 0;
        String SessionId=req.getParameter("sessionid");
        String StudentId;
        do {
            Attend a = new Attend();
            StudentId = req.getParameter("name" + i);
            
            String comment = req.getParameter("comment"+i);
            boolean status = Boolean.parseBoolean(req.getParameter("status" + i));
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            
            Student s = new Student();
            s.setId(StudentId);
            Session se = new Session();
            se.setId(Integer.parseInt(SessionId));
            a.setStudent(s);
            a.setSession(se);
            a.setStatus(status);
            a.setComment(comment);
            a.setRecordTime(timestamp);
            attends.add(a);
            i++;
        } while (StudentId!=null);
        attends.remove(i-1);
//        for(Attend a: attends){
//            resp.getWriter().println(a.isStatus()+" "+a.getComment()+" "+a.getRecordTime()+" "+a.getStudent().getId()+" "+a.getSession().getId());
//        }
        AttendDBContext AttendDb = new AttendDBContext();
        AttendDb.updateAttendance(attends);
        
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        resp.sendRedirect("../instructor/weeklyTimeTable?Date="+currentDate+"&instuctorId="+((User)req.getSession().getAttribute("user")).getUsername());
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
        req.getRequestDispatcher("../view/instructor/UpdateAttendance.jsp").forward(req, resp);
    }
    
}
