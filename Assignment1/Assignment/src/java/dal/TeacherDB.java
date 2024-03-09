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
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class TeacherDB extends DBContext {

    public void insertTeacher(Teacher t) {

        String sql = "INSERT INTO [Teacher]\n"
                + "           ([teacherID]\n"
                + "           ,[firstname]\n"
                + "           ,[lastname]\n"
                + "           ,[gender]\n"
                + "           ,[admin])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, t.getTeacherID());
            stm.setNString(2, t.getFirstname());
            stm.setNString(3, t.getLastname());
            stm.setBoolean(4, t.isGender());
            stm.setBoolean(5, t.isAdmin());

            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Teacher getTeacherById(String id) {
        try {
            String sql = "	SELECT [teacherID]\n"
                    + "		  ,[firstname]\n"
                    + "		  ,[lastname]\n"
                    + "		  ,[gender]\n"
                    + "		  ,[admin]\n"
                    + "	  FROM [dbo].[Teacher]\n"
                    + "	  where [teacherID] = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(1));
                t.setFirstname(rs.getNString(2));
                t.setLastname(rs.getNString(3));
                t.setGender(rs.getBoolean(4));
                t.setAdmin(rs.getBoolean(5));
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteTeacher(String id) {
        try {
            String sql = "	DELETE FROM [TeacherAccount]\n"
                    + "      WHERE  teacherID = ?\n"
                    + "\n"
                    + "DELETE FROM [Teacher]\n"
                    + "      WHERE teacherID = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, id);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Teacher> getTeacher() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            String sql = "	SELECT [teacherID]\n"
                    + "		  ,[firstname]\n"
                    + "		  ,[lastname]\n"
                    + "		  ,[gender]\n"
                    + "		  ,[admin]\n"
                    + "	  FROM [dbo].[Teacher]\n";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(1));
                t.setFirstname(rs.getNString(2));
                t.setLastname(rs.getNString(3));
                t.setGender(rs.getBoolean(4));
                t.setAdmin(rs.getBoolean(5));
                teachers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
    }

    public void updateTeacher(Teacher t) {
        try {
            String sql = "UPDATE [dbo].[Teacher]\n"
                    + "   SET "
                    + "      [firstname] = ?\n"
                    + "      ,[lastname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + " WHERE  [teacherID]= ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, t.getFirstname());
            stm.setNString(2, t.getLastname());
            stm.setBoolean(3, t.isGender());
            stm.setString(4, t.getTeacherID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int teacherPer(String t) {
        try {
            String sql = "SELECT    Count(*)\n"
                    + "FROM         Class INNER JOIN\n"
                    + "                      Teacher ON Class.teacherID = Teacher.teacherID\n"
                    + "					  where  Teacher.teacherID = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, t);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
