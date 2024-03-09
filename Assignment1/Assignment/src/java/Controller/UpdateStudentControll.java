/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Login.BaseAuthController;
import dal.ClassDB;
import dal.StudentDB;
import dal.subjectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class UpdateStudentControll extends BaseAuthController {

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
        String id = request.getParameter("id");
        StudentDB stdb = new StudentDB();
        Student student = stdb.getStudent(id);
        request.setAttribute("student", student);

        ClassDB cldb = new ClassDB();
        ArrayList<ClassStudent> classes = cldb.getListClass(-1);
        request.setAttribute("classes", classes);

        request.getRequestDispatcher("../view/student/update.jsp").forward(request, response);
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
        String raw_id = request.getParameter("id");
        String raw_first = request.getParameter("firstname");
        String raw_last = request.getParameter("lastname");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String raw_adress = request.getParameter("adress");
        String raw_classid = request.getParameter("classid");
        String raw_photo = request.getParameter("photo");

        String oldclass = request.getParameter("oldclass");

        boolean gender = raw_gender.equals("boy");
        Date dob = Date.valueOf(raw_dob);
        Student st = new Student();

        st.setStudentID(raw_id);
        st.setFirstname(raw_first);
        st.setLastname(raw_last);
        st.setGender(gender);
        st.setDob(dob);
        st.setAddress(raw_adress);

        ClassStudent cl = new ClassStudent();
        cl.setClassID(raw_classid);

        st.setClassID(cl);

        st.setPhoto(raw_photo);
        StudentDB stdb = new StudentDB();

        if (oldclass.subSequence(0, 1).equals(st.getClassID().getClassID().substring(0, 1))) {
            stdb.updateStudent(st);
            
        } else {
            subjectDB sjdb = new subjectDB();
            ArrayList<Subject> subjects = sjdb.listSubjectInClass(st.getClassID().getClassID());
            for (Subject sj : subjects) {
                Mark m = new Mark();
                m.setSubjectid(sj);
                m.setStudentid(st);
                st.getMarks().add(m);

            }
            stdb.UpdateStudentGradeChange(st);
        }
        response.sendRedirect("infor?studentid=" + st.getStudentID());
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
