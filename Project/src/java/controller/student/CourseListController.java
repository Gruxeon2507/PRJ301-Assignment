package controller.student;

import controller.authentication.BaseRequiredAuthenticatedControllerStudent;
import dal.CourseDBContext;
import dal.GroupDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.Group;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nguyen Hoang Minh
 */
public class CourseListController extends BaseRequiredAuthenticatedControllerStudent {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = groupDB.getStudentGroupByStudentId(user.getUsername());
        
        req.setAttribute("userid", user.getUsername());
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("../view/student/CourseList.jsp").forward(req, resp);

    }

}
