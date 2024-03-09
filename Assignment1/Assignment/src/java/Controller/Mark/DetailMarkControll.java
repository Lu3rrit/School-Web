/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Mark;

import Controller.Login.BaseAuthController;
import dal.CommentDB;
import dal.MarkDB;
import dal.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.ParentAccount;
import model.entity.Comment;
import model.entity.Mark;
import model.entity.Student;

/**
 *
 * @author ASUS
 */
public class DetailMarkControll extends BaseAuthController {

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
        String studentid = request.getParameter("studentid");
        if (admin.equals("0")) {
            ParentAccount acc = (ParentAccount) request.getSession().getAttribute("account");
            if (!acc.getStudentID().getStudentID().equals(studentid)) {
                response.sendRedirect("../mark/detail?studentid="+acc.getStudentID().getStudentID());
            } else {
                
                StudentDB stdb = new StudentDB();
                Student st = stdb.getStudent(studentid);
                request.setAttribute("student", st);

                MarkDB mdb = new MarkDB();
                ArrayList<Mark> marks = mdb.getMarkByStudent(studentid);
                request.setAttribute("marks", marks);
                String hk1 = rank1(marks);
                String hk2 = rank2(marks);
                request.setAttribute("hk1", hk1);
                request.setAttribute("hk2", hk2);

                CommentDB cdb = new CommentDB();
                ArrayList<Comment> cmts = cdb.getComment(studentid);

                request.setAttribute("cmts", cmts);

                request.getRequestDispatcher("../view/mark/detail").forward(request, response);

            }

        } else {

            StudentDB stdb = new StudentDB();
            Student st = stdb.getStudent(studentid);
            request.setAttribute("student", st);

            MarkDB mdb = new MarkDB();
            ArrayList<Mark> marks = mdb.getMarkByStudent(studentid);
            request.setAttribute("marks", marks);
            String hk1 = rank1(marks);
            String hk2 = rank2(marks);
            request.setAttribute("hk1", hk1);
            request.setAttribute("hk2", hk2);

            CommentDB cdb = new CommentDB();
            ArrayList<Comment> cmts = cdb.getComment(studentid);

            request.setAttribute("cmts", cmts);

            request.getRequestDispatcher("/view/mark/detail.jsp").forward(request, response);
        }

    }

    public String rank1(ArrayList<Mark> m) {
        String hk2 = "";
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getFinalltest1() == -1) {
                hk2 += "1";
            }
            if (m.get(i).getFinalltest1() < 5) {
                hk2 += "2";
            }
            if (m.get(i).getFinalltest1() >= 5 && m.get(i).getFinalltest1() < 7) {
                hk2 += "3";
            }
            if (m.get(i).getFinalltest1() >= 7 && m.get(i).getFinalltest1() < 9) {
                hk2 += "4";
            }
            if (m.get(i).getFinalltest1() >= 9 && m.get(i).getFinalltest1() <= 10) {
                hk2 += "5";
            }

        }
        if (hk2.contains("1")) {
            return "";
        } else if (hk2.contains("2")) {
            return "chưa hoàn thành";
        } else if (hk2.contains("3")) {
            return "hoàn thành";
        } else if (hk2.contains("4")) {
            return "hoàn thành tốt";
        } else if (hk2.contains("5")) {
            return "hoan thành suất sắc";
        }
        return "";
    }

    public String rank2(ArrayList<Mark> m) {
        String hk2 = "";
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getFinalltest2() == -1) {
                hk2 += "1";
            }
            if (m.get(i).getFinalltest2() < 5) {
                hk2 += "2";
            }
            if (m.get(i).getFinalltest2() >= 5 && m.get(i).getFinalltest2() < 7) {
                hk2 += "3";
            }
            if (m.get(i).getFinalltest2() >= 7 && m.get(i).getFinalltest2() < 9) {
                hk2 += "4";
            }
            if (m.get(i).getFinalltest2() >= 9 && m.get(i).getFinalltest2() <= 10) {
                hk2 += "5";
            }

        }
        if (hk2.contains("1")) {
            return "";
        } else if (hk2.contains("2")) {
            return "chưa hoàn thành";
        } else if (hk2.contains("3")) {
            return "hoàn thành";
        } else if (hk2.contains("4")) {
            return "hoàn thành tốt";
        } else if (hk2.contains("5")) {
            return "hoan thành suất sắc";
        }
        return "";
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String sid = request.getParameter("id");
        String per = request.getParameter("per");
        String content = request.getParameter("content");

        Comment cmt = new Comment();

        Student st = new Student();
        st.setStudentID(sid);
        cmt.setNameuser(username);
        cmt.setStudentid(st);
        cmt.setContent(content);
        cmt.setTeacher(per.equals("1"));

        CommentDB cdb = new CommentDB();
        cdb.insertComment(cmt);
        response.sendRedirect("../mark/detail?studentid=" + st.getStudentID());
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
