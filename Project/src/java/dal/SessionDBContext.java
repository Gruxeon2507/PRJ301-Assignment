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
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.TimeSlot;
import java.sql.Date;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class SessionDBContext extends DBContext<Session> {

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> getInstructor(String courseId, String instructorId) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select s.sessionId,s.sessionName,s.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId\n"
                    + "From [Session] s inner join Instructor i on s.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot t on s.slotId=t.slotId\n"
                    + "inner join Room r on s.roomId=r.roomId\n"
                    + "inner join [Group] g on s.groupId=g.groupId\n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "where i.instructorId= ? and c.courseId= ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            stm.setString(2, courseId);
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
                sessions.add(s);
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
        return sessions;
    }

    public ArrayList<Session> getSession(String instuctorId, Date monday, Date sunday) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select s.sessionId,s.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId\n"
                    + "From [Session] s inner join Instructor i on s.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot t on s.slotId=t.slotId\n"
                    + "inner join Room r on s.roomId=r.roomId\n"
                    + "inner join [Group] g on s.groupId=g.groupId\n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "where i.instructorId=? and s.[date] between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instuctorId);
            stm.setDate(2, monday);
            stm.setDate(3, sunday);
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
                sessions.add(s);
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
        return sessions;
    }

    public ArrayList<Session> getStatusSession(String instuctorId, Date monday, Date sunday) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select distinct s.sessionId,s.sessionName,s.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId,iif(a.[status] IS NULL ,'false','true') as [status]\n"
                    + "From [Session] s inner join Instructor i on s.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot t on s.slotId=t.slotId\n"
                    + "inner join Room r on s.roomId=r.roomId\n"
                    + "inner join [Group] g on s.groupId=g.groupId\n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "inner join Participate p on p.groupId=g.groupId\n"
                    + "left join Attend a on a.sessionId=s.sessionId\n"
                    + "where i.instructorId=? and s.[date] between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instuctorId);
            stm.setDate(2, monday);
            stm.setDate(3, sunday);
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

                s.setStatus(rs.getBoolean("status"));
                s.setGroup(g);
                s.setInstructor(i);
                s.setSlot(t);
                s.setRoom(r);
                sessions.add(s);
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
        return sessions;
    }

    public ArrayList<Session> getStudentSession(String studentId, Date monday, Date sunday) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select distinct s.sessionId,s.sessionName,s.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId,iif(a.[status] IS NULL ,'false','true') as [status]\n"
                    + "From [Session] s inner join Instructor i on s.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot t on s.slotId=t.slotId\n"
                    + "inner join Room r on s.roomId=r.roomId\n"
                    + "inner join [Group] g on s.groupId=g.groupId\n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "inner join Participate p on p.groupId=g.groupId\n"
                    + "inner join Student su on p.studentId=su.studentId\n"
                    + "left join Attend a on a.sessionId=s.sessionId\n"
                    + "where su.studentId= ? and s.[date] between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setDate(2, monday);
            stm.setDate(3, sunday);
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
                
                s.setStatus(rs.getBoolean("status"));
                s.setGroup(g);
                s.setInstructor(i);
                s.setSlot(t);
                s.setRoom(r);
                sessions.add(s);
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
        return sessions;
    }

    @Override
    public ArrayList<Session> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> getStudentSessionByStudentId(String studentId, String courseId) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select distinct ses.sessionId,ses.sessionName,ses.[date],g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName,t.slotId,t.slotNumber,t.startTime,t.endTime,r.roomId,iif(a.[status] IS NULL ,'false','true') as [status] from [session] ses \n"
                    + "inner join [Group] g on ses.groupId=g.groupId \n"
                    + "inner join Course c on g.courseId=c.courseId\n"
                    + "inner join Instructor i on ses.lecturerId=i.instructorId\n"
                    + "inner join TimeSlot  t on ses.slotId=t.slotId\n"
                    + "inner join Room r on ses.roomId=r.roomId\n"
                    + "left join Attend a on ses.sessionId=a.sessionId\n"
                    + "where c.courseId=? and g.groupId in (Select g.groupId from [Group]  g inner join Participate p on g.groupId=p.groupId\n"
                    + "inner join Student s on p.studentId=s.studentId\n"
                    + "inner join Course c on c.courseId=g.courseId\n"
                    + "where s.studentId = ? and c.courseId=?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, courseId);
            stm.setString(2, studentId);
            stm.setString(3, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("sessionId"));
                s.setDate(rs.getDate("date"));

                Instructor i = new Instructor();
                i.setId(rs.getString("instructorId"));
                i.setName(rs.getString("instructorName"));

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
                
                s.setStatus(rs.getBoolean("status"));
                s.setGroup(g);
                s.setInstructor(i);
                s.setSlot(t);
                s.setRoom(r);
                sessions.add(s);
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
        return sessions;
    }

    public static void main(String[] args) {
        ArrayList<Session> sessions = new SessionDBContext().getStudentSessionByStudentId("HE170996", "PRJ301");
        for (Session s : sessions) {
            System.out.println(s.isStatus()==false);
        }

    }
}
