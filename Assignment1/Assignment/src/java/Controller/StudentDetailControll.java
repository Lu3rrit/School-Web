/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Login.BaseAuthController;
import dal.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.ParentAccount;
import model.entity.Student;

/**
 *
 * @author ASUS
 */
public class StudentDetailControll extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String admin = (String) request.getSession().getAttribute("admin");
        StudentDB stdb = new StudentDB();
        if (admin.equals("0")) {
            ParentAccount acc = (ParentAccount) request.getSession().getAttribute("account");
            String studentid = acc.getStudentID().getStudentID();
            Student st = stdb.getStudent(studentid);
            request.setAttribute("st", st);
            request.getRequestDispatcher("../view/student/infor.jsp").forward(request, response);

        } else {
            String studentid = request.getParameter("studentid");
            Student st = stdb.getStudent(studentid);
            request.setAttribute("st", st);
            request.getRequestDispatcher("../view/student/infor.jsp").forward(request, response);
        }

    }

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
