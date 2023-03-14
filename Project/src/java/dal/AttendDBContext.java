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
import model.AbsentStudent;
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

    public void insert(ArrayList<Attend> model) {

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

    public void TakeAttendance(ArrayList<Attend> model) {
        PreparedStatement stm = null;
        try {
            String sql = "Insert into Attend(studentId,sessionId,[status],recordTime,comment) values (?,?,?,?,?)";
            int s = model.size();
            for (int i = 1; i < s; i++) {
                sql += ",(?,?,?,?,?)";
            }
            sql += ";";
            stm = connection.prepareStatement(sql);
            int j = 1;
            for (Attend a : model) {
                stm.setString(j++, a.getStudent().getId());
                stm.setInt(j++, a.getSession().getId());
                stm.setBoolean(j++, a.isStatus());
                stm.setTimestamp(j++, a.getRecordTime());
                stm.setString(j++, a.getComment());
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAttendance(ArrayList<Attend> model) {
        PreparedStatement stm = null;
        try {
            String temp = "Update Attend set [status] = ?,comment = ?, recordTime = ? where studentId = ? and sessionId = ?";
            String sql = temp;
            int s = model.size();
            for (int i = 1; i < s; i++) {
                sql = sql + " " + temp;
            }
// 
//            for(Attend a:model){
//                sql+=temp;
//            }
            stm = connection.prepareStatement(sql);
            int j = 1;
//            stm.setBoolean(j++, model.get(0).isStatus());
//                stm.setString(j++,model.get(0).getComment());
//                stm.setTimestamp(j++, model.get(0).getRecordTime());
//                stm.setString(j++, model.get(0).getStudent().getId());
//                stm.setInt(j++, model.get(0).getSession().getId());
            for (Attend a : model) {
                stm.setBoolean(j++, a.isStatus());
                stm.setString(j++, a.getComment());
                stm.setTimestamp(j++, a.getRecordTime());
                stm.setString(j++, a.getStudent().getId());
                stm.setInt(j++, a.getSession().getId());
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Attend> status(String groupName, String courseId, String instuctorId) {
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

    public ArrayList<Attend> getAttendedSession(int groupId, String courseId, String instuctorId, int sessionId) {
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
                    + "where g.groupId= ? and c.courseId =?  and se.lecturerId=? and a.sessionId=?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            stm.setString(2, courseId);
            stm.setString(3, instuctorId);
            stm.setInt(4, sessionId);
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

    public ArrayList<AbsentStudent> getAbsent(int groupId) {
        ArrayList<AbsentStudent> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select s.studentId, sum(1 - CAST(a.[status] as int)) as absentCount from Student s left join Attend a on s.studentId = a.studentId left join [Session] ses on a.sessionId = ses.sessionId where ses.groupId = ? group by s.studentId";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs=stm.executeQuery();
            while(rs.next()){
                AbsentStudent s = new AbsentStudent();
                s.setStudentId(rs.getString("studentId"));
                s.setNoSlot(rs.getInt("absentCount"));
                students.add(s);
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
        return students;
    }

}
