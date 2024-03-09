/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Mark;

import Controller.Login.BaseAuthController;
import dal.ClassDB;
import dal.MarkDB;
import dal.StudentDB;
import dal.subjectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ClassStudent;
import model.entity.Mark;
import model.entity.Student;
import model.entity.Subject;

/**
 *
 * @author ASUS
 */
public class ListMarkControll extends BaseAuthController {

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
        request.setCharacterEncoding("UTF-8");
        String classid = request.getParameter("classid");
        if(classid == null || classid.trim().length()==0){
            classid = "1A";
        }
        
        subjectDB sjdb = new subjectDB();
        ArrayList<Subject> subjects = sjdb.listSubjectInClass(classid);
        request.setAttribute("subjects", subjects);
        
        StudentDB stdb = new StudentDB();
        
        ArrayList<Student> students = stdb.getStudentsByClass(classid);
        MarkDB mdb= new MarkDB();
        
        
        for(int i = 0 ; i<students.size();i++){
            ArrayList<Mark> marks = mdb.getMarkByStudent(students.get(i).getStudentID());
            
            students.get(i).setMarks(marks);
             
        }
        
        ClassDB cdb = new ClassDB();
        ClassStudent cl = cdb.getClass(classid);
        request.setAttribute("cl", cl);
        request.setAttribute("classid", classid);
        request.setAttribute("students", students);
        request.getRequestDispatcher("../view/mark/list.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
