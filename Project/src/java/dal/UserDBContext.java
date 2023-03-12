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
import model.User;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class UserDBContext extends DBContext<User>{

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public User get(String username, String password) {
        String sql = "Select instructorId,instructorName From Instructor where instructorId = ? and [password] = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next())
            {
                User s = new User();
                s.setUsername(username);
                s.setDisplayname(rs.getString("instructorName"));
                s.setRole(1);
                return s;
            }
            String sql2 = "Select studentid,studentName,[password] From Student where studentid = ? and [password] = ?";
            stm = connection.prepareStatement(sql2);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            
            if(rs.next())
            {
                User s = new User();
                s.setUsername(rs.getString("studentid"));
                s.setDisplayname(rs.getString("studentName"));
                s.setRole(0);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
//    public User get(String username, String password){
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            String sql = "Select instructorId,instructorName From Instructor where instructorId = ? and [password] = ?";
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            stm.setString(2, password);
//            rs = stm.executeQuery();
//            if(rs.next())
//            {
//                User s = new User();
//                s.setUsername(rs.getString("instuctorId"));
//                s.setDisplayname(rs.getString("instuctorName"));
//                s.setRole(1);
//                return s;
//            }
//            
////            String sql2 = "Select studentid,studentName,[password] From Student where studentid = ? and [password] = ?";
////            stm = connection.prepareStatement(sql2);
////            stm.setString(1, username);
////            stm.setString(2, password);
////            rs = stm.executeQuery();
////            
////            if(rs.next())
////            {
////                User s = new User();
////                s.setUsername(rs.getString("studentid"));
////                s.setDisplayname(rs.getString("studentName"));
////                s.setRole(0);
////                return s;
////            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally {
//            try {
//                rs.close();
//                stm.close();
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }
}
