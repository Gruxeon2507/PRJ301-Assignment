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

/**
 *
 * @author Nguyen Hoang Minh
 */
public class ClassListController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String instuctorId = user.getUsername(); 
        GroupDBContext GroupDB = new  GroupDBContext();
        ArrayList<Group> groups = GroupDB.getInstuctorGroup(instuctorId);
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("../view/instructor/ClassList.jsp").forward(req, resp);
    }
    
}
