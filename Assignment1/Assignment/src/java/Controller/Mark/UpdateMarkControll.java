/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Mark;

import Controller.Login.BaseAuthController;
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
import model.entity.Mark;
import model.entity.Student;
import model.entity.Subject;

/**
 *
 * @author ASUS
 */
public class UpdateMarkControll extends BaseAuthController {

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
        StudentDB stdb = new StudentDB();
        String stid = request.getParameter("stid");
        Student st = stdb.getStudent(stid);
        request.setAttribute("student", st);

        MarkDB mdb = new MarkDB();
        ArrayList<Mark> marks = mdb.getMarkByStudent(stid);
        request.setAttribute("marks", marks);
        request.getRequestDispatcher("../view/mark/update.jsp").forward(request, response);

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
        Mark m = new Mark();

        StudentDB stdb = new StudentDB();
        String stid = request.getParameter("stid");
        Student st = stdb.getStudent(stid);

        String sjid[] = request.getParameterValues("sjid");
        subjectDB sjdb = new subjectDB();

        for (String s : sjid) {
            Subject sj = sjdb.getSubject(s);
            m.setStudentid(st);
            
            m.setSubjectid(sj);
            
            String raw_sm1 = request.getParameter("sm1"+sj.getSubjectID());
            String raw_bi1 = request.getParameter("bi1"+sj.getSubjectID());
            String raw_fi1 = request.getParameter("fi1"+sj.getSubjectID());
            String raw_sm2 = request.getParameter("sm2"+sj.getSubjectID());
            String raw_bi2 = request.getParameter("bi2"+sj.getSubjectID());
            String raw_fi2 = request.getParameter("fi2"+sj.getSubjectID());

            if (raw_sm1 == null || raw_sm1.trim().length() == 0) {
                raw_sm1 = "-1";
            }
            int sm1 = Integer.parseInt(raw_sm1);

            if (raw_bi1 == null || raw_bi1.trim().length() == 0) {
                raw_bi1 = "-1";
            }
            int bi1 = Integer.parseInt(raw_bi1);

            if (raw_fi1 == null || raw_fi1.trim().length() == 0) {
                raw_fi1 = "-1";
            }
            int fi1 = Integer.parseInt(raw_fi1);

            if (raw_sm2 == null || raw_sm2.trim().length() == 0) {
                raw_sm2 = "-1";
            }
            int sm2 = Integer.parseInt(raw_sm2);

            if (raw_bi2 == null || raw_bi2.trim().length() == 0) {
                raw_bi2 = "-1";
            }
            int bi2 = Integer.parseInt(raw_bi2);

            if (raw_fi2 == null || raw_fi2.trim().length() == 0) {
                raw_fi2 = "-1";
            }
            int fi2 = Integer.parseInt(raw_fi2);

            m.setSmalltest1(sm1);
            m.setSmalltest2(sm2);
            m.setBigtest1(bi1);
            m.setBigtest2(bi2);
            m.setFinalltest1(fi1);
            m.setFinalltest2(fi2);

            MarkDB mdb = new MarkDB();

            mdb.updateMark(m);
        }
        response.sendRedirect("../mark/update?stid=" + st.getStudentID());
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
