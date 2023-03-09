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
import model.Student;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class GroupDBContext extends DBContext<Group> {

    @Override
    public void insert(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Group> all() {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName\n"
                    + "From [Group] g inner join Instructor i on g.instructorId=i.instructorId\n"
                    + "inner join Course c on g.courseId=c.courseId";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                Course c = new Course();
                Instructor i = new Instructor();

                g.setId(rs.getInt("groupId"));
                g.setName(rs.getString("groupName"));

                c.setId(rs.getString("courseId"));
                c.setName(rs.getString("courseName"));

                i.setId(rs.getString("instructorId"));
                i.setName(rs.getString("instructorName"));

                g.setCourse(c);
                g.setInstructor(i);

                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

    public ArrayList<Group> getInstuctorGroup(String instuctorId) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select g.groupId,g.groupName,c.courseId,c.courseName,i.instructorId,i.instructorName\n"
                    + "From [Group] g inner join Instructor i on g.instructorId=i.instructorId\n"
                    + "inner join Course c on g.courseId=c.courseId";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instuctorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                Course c = new Course();
                Instructor i = new Instructor();

                g.setId(rs.getInt("groupId"));
                g.setName(rs.getString("groupName"));

                c.setId(rs.getString("courseId"));
                c.setName(rs.getString("courseName"));

                i.setId(rs.getString("instructorId"));
                i.setName(rs.getString("instructorName"));

                g.setCourse(c);
                g.setInstructor(i);

                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

}
