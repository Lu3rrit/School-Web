/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import dal.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.ParentAccount;
import model.account.TeacherAccount;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class LoginControll extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
         request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        AccountDB adb = new AccountDB();
        ParentAccount pacc = adb.getPA(username, password);
        TeacherAccount tacc = adb.getTA(username, password);
        String admin;
        if(pacc==null && tacc== null ){
            
            request.getSession().setAttribute("admin", null);
            request.getSession().setAttribute("account", null);
            request.setAttribute("message", "mật khẩu hoặc tài khoản không chính xác");
            processRequest(request, response);
            
        }else if(pacc!=null && tacc==null){
            admin = "0";
            request.getSession().setAttribute("account", pacc);
          
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("home");
        }else if(pacc==null && tacc!=null){
            admin = "1";
            request.getSession().setAttribute("account", tacc);      
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("home");
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
