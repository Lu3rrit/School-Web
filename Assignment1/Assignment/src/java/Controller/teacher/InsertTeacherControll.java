/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.teacher;

import Controller.Login.BaseAuthController;
import dal.AccountDB;
import dal.TeacherDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class InsertTeacherControll extends BaseAuthController {

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../view/teacher/insert.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        Teacher t = new Teacher();
        boolean gender = request.getParameter("gender").equals("boy");
        t.setTeacherID(request.getParameter("teacherid"));
        t.setFirstname(request.getParameter("firstname"));
        t.setLastname(request.getParameter("lastname"));
        t.setGender(gender);
        t.setAdmin(false);
        
        TeacherDB tdb= new TeacherDB();
        tdb.insertTeacher(t);
        
        
        String password = "123b123b";
        AccountDB adb  = new AccountDB();
        adb.insertTeacherAccount(t, password);
        
        
        response.sendRedirect("../teacher/infor?id="+t.getTeacherID());
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
