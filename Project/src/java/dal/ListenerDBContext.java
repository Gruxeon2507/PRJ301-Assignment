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
import model.SessionCount;
import model.TimeSlot;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class ListenerDBContext extends DBContext<SessionCount>{

    @Override
    public void insert(SessionCount model) {
        PreparedStatement stm = null;
        try {
            String sql = "insert into SessionCount(UserId,SessionCount) values (?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUserId());
            stm.setInt(2, model.getCount());
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


    @Override
    public void update(SessionCount model) {
        PreparedStatement stm = null;
        try {
            String sql = "update SessionCount set [SessionCount]= ? where UserId = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getCount());
            stm.setString(2, model.getUserId());
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

    @Override
    public void delete(SessionCount model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SessionCount get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SessionCount getByUserId(String UserId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select UserId,SessionCount from SessionCount where UserId=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, UserId);
            rs = stm.executeQuery();
            if (rs.next()) {
                SessionCount sc = new SessionCount();
                sc.setUserId(rs.getString("UserId"));
                sc.setCount(rs.getInt("SessionCount"));
                return sc;
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
        return null;
    }

    @Override
    public ArrayList<SessionCount> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
