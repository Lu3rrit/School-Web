/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Class;

import dal.ClassDB;
import dal.StudentDB;
import dal.TeacherDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ClassStudent;
import model.entity.Student;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class UpdateClass extends HttpServlet {

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
        String clid = request.getParameter("clid");
        StudentDB sdb  = new StudentDB();
        
        
        ClassDB cdb = new ClassDB();        
        ClassStudent cl = cdb.getClass(clid);
        
       
        request.setAttribute("cl", cl);
        request.getRequestDispatcher("../view/class/update.jsp").forward(request, response);
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
        
        String tid = request.getParameter("tid");
        String cid = request.getParameter("cid");
        TeacherDB tdb = new TeacherDB();
        ClassDB cdb = new ClassDB(); 
        
        Teacher t = tdb.getTeacherById(tid);
        if(t == null){
             request.setAttribute("message", "id này không tồn tại");
             processRequest(request, response);
        }else{
             ClassStudent cl = cdb.getClass(cid);
             cdb.updateClass(cl, t);
             response.sendRedirect("../class/detail?clid="+cl.getClassID());
             
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
