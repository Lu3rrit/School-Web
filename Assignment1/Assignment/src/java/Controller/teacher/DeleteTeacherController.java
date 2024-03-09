/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.teacher;

import Controller.Login.BaseAuthController;
import dal.TeacherDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.TeacherAccount;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class DeleteTeacherController extends BaseAuthController {

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        TeacherAccount tacc =(TeacherAccount) request.getSession().getAttribute("account");
        
       String id = request.getParameter("id");
       TeacherDB db = new TeacherDB();
       
       Teacher t = db.getTeacherById(id);
       int per = db.teacherPer(id);
       if(t.isAdmin() || per>0 || tacc.getTeacherid().getTeacherID().equals(id)||!tacc.getTeacherid().isAdmin() ){
           response.sendRedirect("../teacher/infor?id="+t.getTeacherID());
       }else{
           db.deleteTeacher(id);
            response.sendRedirect("../teacher/list");
       }
    }

     
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
