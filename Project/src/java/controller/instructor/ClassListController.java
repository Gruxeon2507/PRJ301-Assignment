/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import dal.GroupDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Group;
import model.User;
import controller.authentication.BaseRequiredAuthenticatedControllerInstructor;
/**
 *
 * @author Nguyen Hoang Minh
 */
public class ClassListController extends BaseRequiredAuthenticatedControllerInstructor{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp,User user) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp,User user) throws ServletException, IOException {
        String instuctorId = user.getUsername(); 
        GroupDBContext GroupDB = new  GroupDBContext();
        ArrayList<Group> groups = GroupDB.getInstuctorGroup(instuctorId);
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        req.setAttribute("currentdate", currentDate);
        req.setAttribute("username", user.getUsername());
        req.setAttribute("groups", groups);
        req.setAttribute("userid", user.getDisplayname());
        req.getRequestDispatcher("../view/instructor/ClassList.jsp").forward(req, resp);
    }
    
}
