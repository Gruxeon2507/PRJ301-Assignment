/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attend;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class AttendDBContext extends DBContext<Attend> {

    @Override
    public void insert(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attend get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attend> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attend> status(String groupName,String courseId,String instuctorId) {
        ArrayList<Attend> attends = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select a.studentId,a.sessionId,a.[status],a.recordTime,a.comment,s.studentName,s.studentImage,se.sessionName,se.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId From Attend a inner join Student s on a.studentId=s.studentId\n"
                    + "inner join [Session] se on a.sessionId=se.sessionId\n"
                    + "inner join Instructor i on se.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot t on se.slotId=t.slotId\n"
                    + "inner join Room r on se.roomId=r.roomId\n"
                    + "inner join [Group] g on se.groupId=g.groupId\n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "where groupName= ? and c.courseId =?  and se.lecturerId=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, groupName);
            stm.setString(2, courseId);
            stm.setString(3, instuctorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("sessionId"));
                s.setDate(rs.getDate("date"));

                Instructor i = new Instructor();
                i.setId(rs.getString("instructorId"));
                i.setId(rs.getString("instructorName"));

                Course c = new Course();
                c.setId(rs.getString("courseId"));
                c.setName(rs.getString("courseName"));

                Group g = new Group();
                g.setId(rs.getInt("groupId"));
                g.setName(rs.getString("groupName"));
                g.setCourse(c);
                g.setInstructor(i);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotId"));
                t.setSlotNumber(rs.getInt("slotNumber"));
                t.setStartTime(rs.getTime("startTime"));
                t.setEndTime(rs.getTime("endTime"));

                Room r = new Room();
                r.setId(rs.getString("roomId"));

                s.setGroup(g);
                s.setInstructor(i);
                s.setSlot(t);
                s.setRoom(r);
                
                Student st = new Student();     
                st.setId(rs.getString("studentId"));
                st.setName(rs.getString("studentName"));
                st.setImage(rs.getString("studentImage"));
                
                Attend a = new Attend();
                a.setStatus(rs.getBoolean("status"));
                a.setRecordTime(rs.getTimestamp("recordTime"));
                a.setComment(rs.getString("comment"));
                a.setStudent(st);
                a.setSession(s);
                
                attends.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attends;
    }

}
