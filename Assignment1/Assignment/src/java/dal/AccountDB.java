/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.account.ParentAccount;
import model.account.ParentFeature;
import model.account.TeacherAccount;
import model.entity.Student;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class AccountDB extends DBContext {

    public ParentAccount getPA(String username, String password) {
        try {
            String sql = "SELECT    ParentAccount.username, ParentAccount.password, Student.studentID, Student.firstName\n"
                    + "FROM         ParentAccount INNER JOIN\n"
                    + "                      Student ON ParentAccount.studentID = Student.studentID\n"
                    + "					  where username = ? and password = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                ParentAccount acc = new ParentAccount();
                acc.setUsername(rs.getString(1));
                acc.setPassword(rs.getString(2));

                Student s = new Student();
                s.setStudentID(rs.getString(3));
                s.setFirstname(rs.getNString(4));

                acc.setStudentID(s);
                return acc;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccountParent(ParentAccount p) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [ParentAccount]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[studentID])\n"
                    + "     VALUES\n"
                    + "           (? \n"
                    + "           ,? \n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getUsername());
            stm.setString(2, p.getPassword());
            stm.setString(3, p.getStudentID().getStudentID());
            stm.executeUpdate();

            for (ParentFeature pf : p.getFeatures()) {
                String sql_add_feature = "INSERT INTO [Group_Parent_Feature]\n"
                        + "           ([username]\n"
                        + "           ,[fid]) \n"
                        + "     VALUES\n"
                        + "           (? \n"
                        + "           ,?)";

                PreparedStatement stm_add_feature = connection.prepareStatement(sql_add_feature);
                stm_add_feature.setString(1, pf.getUsername().getUsername());
                stm_add_feature.setInt(2, pf.getFid().getFid());
                stm_add_feature.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getPermission(String username, String url) {
        try {
            String sql = "SELECT    COUNT(*) as total\n"
                    + "FROM         ParentAccount INNER JOIN\n"
                    + "                      Group_Parent_Feature ON ParentAccount.username = Group_Parent_Feature.username INNER JOIN\n"
                    + "                      Feature ON Group_Parent_Feature.fid = Feature.fid\n"
                    + "					  where ParentAccount.username = ? and [url]=?  ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public TeacherAccount getTA(String username, String password) {
        try {
            String sql = "SELECT    TeacherAccount.username, TeacherAccount.password, Teacher.teacherID, Teacher.firstname, Teacher.admin\n"
                    + "                    FROM         TeacherAccount INNER JOIN\n"
                    + "                                        Teacher ON TeacherAccount.teacherID = Teacher.teacherID"
                    + "					  where TeacherAccount.username = ? and [password] = ?  ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                TeacherAccount ta = new TeacherAccount();
                ta.setUssername(rs.getString(1));
                ta.setPassword(rs.getString(2));
                
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(3));
                t.setFirstname(rs.getNString(4));
                t.setAdmin(rs.getBoolean(5));
                ta.setTeacherid(t);
                
                return ta;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertTeacherAccount(Teacher t, String password) {
        String sql = "INSERT INTO [TeacherAccount]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[teacherID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, t.getTeacherID());
            stm.setNString(2, password);
            stm.setString(3, t.getTeacherID());

            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            

        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ChangepassTeacher(String username, String password) {
        String sql = "UPDATE [TeacherAccount]\n"
                + "  \n"
                + "      SET [password] = ?\n"
                + "      \n"
                + " WHERE [username] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, username);

            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            

        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ChangepassParent(String username, String password) {
        String sql = "UPDATE [ParentAccount]\n"
                + "   SET \n"
                + "      [password] = ?\n"
                + "      \n"
                + " WHERE [username] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, username);

            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            

        } catch (SQLException ex) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
