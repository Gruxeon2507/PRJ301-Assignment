/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import model.User;

/**
 *
 * @author Nguyen Hoang Minh
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBContext db = new UserDBContext();
        User user = db.getFromUser(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            if (new UserDBContext().isAuthorized(user, "/instructor/weeklyTimeTable")) {
                response.sendRedirect("../../Project/instructor/weeklyTimeTable?Date=" + currentDate + "&instuctorId=" + user.getUsername());
            } else {
                response.sendRedirect("../../Project/student/weeklyTimeTable?Date=" + currentDate + "&studentId=" + user.getUsername());
            }
            //        else if(user != null && user.getRole()==0){
            //            request.getSession().setAttribute("user", user);
            //            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            //            
            //        } 
        } else {
            response.sendRedirect("view/authentication/wrongpassword.jsp");
//            response.sendRedirect("login");
        }
    }
}
