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
import model.entity.ClassStudent;
import model.entity.Grade;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class ClassDB extends DBContext {

    public ArrayList<ClassStudent> getListClass(int gradeID) {
        ArrayList<ClassStudent> classes = new ArrayList<>();
        try {
            String sql = "select classID, teacherID, Grade.GradeID\n"
                    + "from Class inner join Grade on Class.GradeID = Grade.GradeID \n";

            if (gradeID > -1) {
                sql += " where Grade.GradeID = ? ";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (gradeID > -1) {
                stm.setInt(1, gradeID);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassStudent c = new ClassStudent();
                c.setClassID(rs.getString("classID"));
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString("teacherID"));

                c.setTeacherID(t);
                Grade gr = new Grade(rs.getInt("GradeID"));
                c.setGradeID(gr);
                classes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }

    public ClassStudent getClass(String classid) {
        try {
            String sql = "SELECT    Class.classID, Teacher.firstname, Teacher.lastname, Class.teacherID, gender\n"
                    + "FROM         Teacher INNER JOIN\n"
                    + "                      Class ON Teacher.teacherID = Class.teacherID\n"
                    + "					  where classID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ClassStudent cl = new ClassStudent();
                cl.setClassID(rs.getString(1));

                Teacher t = new Teacher();
                t.setFirstname(rs.getNString(2));
                t.setLastname(rs.getNString(3));
                t.setTeacherID(rs.getString(4));
                t.setGender(rs.getBoolean(5));
                cl.setTeacherID(t);

                return cl;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void updateClass(ClassStudent cl, Teacher t) {
        try {
            String sql = "UPDATE [Class]\n"
                    + "   SET \n"
                    + "      [teacherID] = ?\n"
                    + "      \n"
                    + " WHERE classID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, t.getTeacherID());
            stm.setString(2, cl.getClassID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
