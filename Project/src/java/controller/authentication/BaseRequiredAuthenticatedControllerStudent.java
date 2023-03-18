/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author sonnt
 */
public abstract class BaseRequiredAuthenticatedControllerStudent extends HttpServlet {

    private boolean isAuthenticatedAsStudent(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(isAuthenticatedAsStudent(request))
        {
            User user = (User)request.getSession().getAttribute("user");
            String path = request.getServletPath();
            boolean isAuthorized = new UserDBContext().isAuthorized(user, path);
            if(isAuthorized){
                doGet(request, response, (User)request.getSession().getAttribute("user"));
            }else{
                response.sendRedirect("../../../../Project/view/authentication/AccessDenied.jsp");
            }

        }
        else
        {
            response.sendRedirect("../../../../Project/view/authentication/Login.jsp");
        }
    }
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response,User user)
            throws ServletException, IOException;
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response,User user)
            throws ServletException, IOException;

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
        if(isAuthenticatedAsStudent(request))
        {
            User user = (User)request.getSession().getAttribute("user");
            String path = request.getServletPath();
            boolean isAuthorized = new UserDBContext().isAuthorized(user, path);
            if(isAuthorized){
                doPost(request, response, (User)request.getSession().getAttribute("user"));
            }else{
                response.sendRedirect("../../../../Project/view/authentication/AccessDenied.jsp");
            }

        }
        else
        {
            response.sendRedirect("../../../../Project/view/authentication/Login.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
