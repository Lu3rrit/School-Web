/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Login.BaseAuthController;
import dal.ClassDB;
import dal.GradeDB;
import dal.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ClassStudent;
import model.entity.Grade;
import model.entity.Student;

/**
 *
 * @author ASUS
 */
public class SearchStudentControll extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GradeDB graDB = new GradeDB();
        ArrayList<Grade> grades = graDB.getListGrade();
        String action = request.getParameter("action");
        if(action==null|| action.trim().length()==0){
            action = "3";
        }
        int ac = Integer.parseInt(action);
        
        request.setAttribute("grades", grades);

       
        int pagesize = 10;
        

        
         String raw_gradeID = request.getParameter("gradeID");
        if (raw_gradeID == null || raw_gradeID.length() == 0) {
            raw_gradeID = "-1";
        }
        int gradeID = Integer.parseInt(raw_gradeID);
        request.setAttribute("gradeID", gradeID);
        
        ClassDB cdb = new ClassDB();
        ArrayList<ClassStudent> classes = cdb.getListClass(gradeID);
        request.setAttribute("classes", classes);
        
        String classID = request.getParameter("classID");
        if (classID== null || classID.length() == 0) {
            classID = "0";}
        int cl = Integer.parseInt(classID.substring(0, 1));
        if(cl != gradeID && gradeID!=-1){
            classID = "0";
        }
//        if(!classID.equals("0")){
//
//         ClassStudent cdt = cdb.getClass(classID);
//         request.setAttribute("cdt", cdt);
//         
//        }


        String page = request.getParameter("page");
        if(page == null || page.trim().length()==0){
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        
        StudentDB sdb = new StudentDB();
        
        
        
        
        ArrayList<Student> students = sdb.getListStudentByClassandGrade(gradeID,classID ,pageindex,pagesize,ac);
        request.setAttribute("classID", classID);
        request.setAttribute("students", students);
        request.setAttribute("action", action);
        int count  = sdb.count(gradeID, classID);
        int totalpage = (count%pagesize==0)?(count/pagesize):(count/pagesize)+1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        
        request.getRequestDispatcher("../view/student/search.jsp").forward(request, response);

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
