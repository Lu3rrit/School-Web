/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Mark;
import model.entity.Student;
import model.entity.Subject;

/**
 *
 * @author ASUS
 */
public class MarkDB extends DBContext {

    public ArrayList<Mark> getMarks() {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "SELECT    studentID, subjectID, smalltest1, bigtest1, finaltest1, smalltest2, bigtest2, finaltest2\n"
                    + "FROM         Mark";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();

                Student st = new Student();
                st.setStudentID(rs.getString(1));
                m.setStudentid(st);

                Subject sj = new Subject();
                sj.setSubjectID(rs.getString(2));
                m.setSubjectid(sj);

                m.setSmalltest1(rs.getInt(3));
                m.setBigtest1(rs.getInt(4));
                m.setFinalltest1(rs.getInt(5));
                m.setSmalltest2(rs.getInt(6));
                m.setBigtest2(rs.getInt(7));
                m.setFinalltest2(rs.getInt(8));

                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }

    public ArrayList<Mark> getMarkByStudent(String studentid) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "SELECT   studentID, Subject.subjectID , Mark.smalltest1, Mark.bigtest1, Mark.finaltest1, Mark.smalltest2, Mark.bigtest2, Mark.finaltest2, subjectName\n"
                    + "                    FROM         Mark INNER JOIN\n"
                    + "                                          Subject ON Mark.subjectID = Subject.subjectID"
                    + "             where studentID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();

                Student st = new Student();
                st.setStudentID(rs.getString("studentID"));
                m.setStudentid(st);

                Subject sj = new Subject();
                sj.setSubjectID(rs.getString("subjectID"));
                sj.setSubjectName(rs.getNString("subjectName"));
                m.setSubjectid(sj);

                String raw_small1 = rs.getString("smalltest1");
                if (raw_small1 == null) {
                    raw_small1 = "-1";
                }
                m.setSmalltest1(Integer.parseInt(raw_small1));

                String raw_big1 = rs.getString(4);
                if (raw_big1 == null) {
                    raw_big1 = "-1";
                }
                m.setBigtest1(Integer.parseInt(raw_big1));

                String raw_fi1 = rs.getString(5);
                if (raw_fi1 == null) {
                    raw_fi1 = "-1";
                }
                m.setFinalltest1(Integer.parseInt(raw_fi1));

                String raw_small2 = rs.getString(6);
                if (raw_small2 == null) {
                    raw_small2 = "-1";
                }
                m.setSmalltest2(Integer.parseInt(raw_small2));

                String raw_big2 = rs.getString(7);
                if (raw_big2 == null) {
                    raw_big2 = "-1";
                }
                m.setBigtest2(Integer.parseInt(raw_big2));

                String raw_fi2 = rs.getString(8);
                if (raw_fi2 == null) {
                    raw_fi2 = "-1";
                }
                m.setFinalltest2(Integer.parseInt(raw_fi2));

                marks.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MarkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }

    public void updateMark(Mark m) {
        String sql = "UPDATE [dbo].[Mark]\n"
                + "   SET \n"
                + "	[smalltest1] = ? \n"
                + "      ,[bigtest1] = ? \n"
                + "      ,[finaltest1] = ? \n"
                + "      ,[smalltest2] = ? \n"
                + "      ,[bigtest2] = ? \n"
                + "      ,[finaltest2] = ? \n"
                + " WHERE studentID = ? and subjectID = ? ";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, m.getSmalltest1());
            stm.setInt(2, m.getBigtest1());
            stm.setInt(3, m.getFinalltest1());
            stm.setInt(4, m.getSmalltest2());
            stm.setInt(5, m.getBigtest2());
            stm.setInt(6, m.getFinalltest2());
            stm.setString(7, m.getStudentid().getStudentID());
            stm.setString(8, m.getSubjectid().getSubjectID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarkDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Mark> getMarksByStudents(Student s) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "SELECT   studentID, Subject.subjectID , Mark.smalltest1, Mark.bigtest1, Mark.finaltest1, Mark.smalltest2, Mark.bigtest2, Mark.finaltest2, subjectName\n"
                    + "                                      FROM         Mark INNER JOIN\n"
                    + "                                                           Subject ON Mark.subjectID = Subject.subjectID\n"
                    + "                               where studentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getStudentID());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();

                Student st = new Student();
                st.setStudentID(rs.getString("studentID"));
                m.setStudentid(st);

                Subject sj = new Subject();
                sj.setSubjectID(rs.getString("subjectID"));
                sj.setSubjectName(rs.getNString("subjectName"));
                m.setSubjectid(sj);

                String raw_small1 = rs.getString("smalltest1");
                if (raw_small1 == null) {
                    raw_small1 = "-1";
                }
                m.setSmalltest1(Integer.parseInt(raw_small1));

                String raw_big1 = rs.getString(4);
                if (raw_big1 == null) {
                    raw_big1 = "-1";
                }
                m.setBigtest1(Integer.parseInt(raw_big1));

                String raw_fi1 = rs.getString(5);
                if (raw_fi1 == null) {
                    raw_fi1 = "-1";
                }
                m.setFinalltest1(Integer.parseInt(raw_fi1));

                String raw_small2 = rs.getString(6);
                if (raw_small2 == null) {
                    raw_small2 = "-1";
                }
                m.setSmalltest2(Integer.parseInt(raw_small2));

                String raw_big2 = rs.getString(7);
                if (raw_big2 == null) {
                    raw_big2 = "-1";
                }
                m.setBigtest2(Integer.parseInt(raw_big2));

                String raw_fi2 = rs.getString(8);
                if (raw_fi2 == null) {
                    raw_fi2 = "-1";
                }
                m.setFinalltest2(Integer.parseInt(raw_fi2));

                marks.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MarkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }
}
