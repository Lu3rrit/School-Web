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
import model.account.TeacherAccount;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class UpdateTeacherControll extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateTeacherControll</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTeacherControll at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");

        String id = request.getParameter("id");
        TeacherDB db = new TeacherDB();
        Teacher t = db.getTeacherById(id);
        if (tacc.getTeacherid().isAdmin()) {

            request.setAttribute("t", t);
            request.getRequestDispatcher("../view/teacher/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("../teacher/infor?id=" + id);
        }

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
        t.setTeacherID(request.getParameter("studentid"));
        t.setFirstname(request.getParameter("firstname"));
        t.setLastname(request.getParameter("lastname"));
        t.setGender(gender);

        TeacherDB tdb = new TeacherDB();
        tdb.updateTeacher(t);
        
        AccountDB adb = new AccountDB();
        TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");
        String username = tacc.getUssername();
        String password = tacc.getPassword();
        
        
        TeacherAccount nacc = adb.getTA(username, password);
        request.getSession().removeAttribute("account");
        request.getSession().setAttribute("account", nacc);
        
        response.sendRedirect("../teacher/infor?id=" + t.getTeacherID());
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
