/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Login.BaseAuthController;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import dal.ClassDB;
import dal.FeatureDB;
import dal.GradeDB;
import dal.AccountDB;
import dal.StudentDB;
import dal.subjectDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.account.Feature;
import model.account.ParentAccount;
import model.account.ParentFeature;
import model.entity.Grade;
import model.entity.ClassStudent;
import model.entity.Mark;
import model.entity.Student;
import model.entity.Subject;


/**
 *
 * @author ASUS
 */
public class InsertStudentControll extends BaseAuthController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ClassDB cdb = new ClassDB();
        ArrayList<ClassStudent> classes = cdb.getListClass(-1);

        request.setAttribute("classes", classes);

        request.getRequestDispatcher("../view/student/insert.jsp").forward(request, response);

    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Student st = new Student();

        boolean gender = request.getParameter("gender").equals("boy");

        String raw_id = (request.getParameter("studentid")).trim();
        String classid = request.getParameter("classid");

        StudentDB stdb = new StudentDB();
        int count = stdb.countStudentInClass(classid);

        Student s = stdb.getStudent(raw_id);

        if (s != null) {
            request.setAttribute("message", "id này đã tồn tại");
            processRequest(request, response);
        }

        String raw_firstname = request.getParameter("firstname").trim();
        if (raw_firstname == null || raw_firstname.trim().length() == 0) {
            request.setAttribute("message", "chưa nhập tên");
            processRequest(request, response);
        }

        String raw_lastname = (request.getParameter("lastname")).trim();
        if (raw_firstname == null || raw_firstname.trim().length() == 0) {
            request.setAttribute("message", "chưa nhập họ");
            processRequest(request, response);
        }
        st.setStudentID(raw_id);
        st.setFirstname(raw_firstname);
        st.setLastname(raw_lastname);

        st.setDob(Date.valueOf(request.getParameter("dob")));
        st.setAddress(request.getParameter("address"));

        st.setGender(gender);
        ClassStudent cl = new ClassStudent();
        if (count > 20) {
            request.setAttribute("message", "số lượng học sinh lớp này đã đạt tối đa");
            processRequest(request, response);
        }
        cl.setClassID(classid);

        st.setClassID(cl);
        st.setPhoto(request.getParameter("file"));
        subjectDB sjdb = new subjectDB();
        ArrayList<Subject> subjects = sjdb.listSubjectInClass(st.getClassID().getClassID());

        for (Subject sj : subjects) {
            Mark m = new Mark();
            m.setSubjectid(sj);
            m.setStudentid(st);
            st.getMarks().add(m);

        }

//        for (int i=0;i<st.getMarks().size();i++){
//            System.out.println(st.getMarks().get(i).getSubjectid().getSubjectName());
//        }
        ParentAccount pa = new ParentAccount();
        pa.setUsername(st.getStudentID());
        pa.setStudentID(st);
        pa.setPassword("123a123a");

        FeatureDB fdb = new FeatureDB();
        ArrayList<Feature> features = fdb.getFeatures();
        for (Feature f : features) {
            ParentFeature pf = new ParentFeature();
            pf.setUsername(pa);
            pf.setFid(f);
            pa.getFeatures().add(pf);
        }

        AccountDB padb = new AccountDB();
        stdb.insertStudent(st);
        padb.insertAccountParent(pa);

        response.sendRedirect("../student/infor?studentid=" + st.getStudentID());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean getString(String input, String Regex) {

        if (input.matches(Regex)) {
            return true;
        } else {
            return false;
        }

    }
}
