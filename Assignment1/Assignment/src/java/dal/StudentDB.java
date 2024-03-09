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
import model.entity.Mark;
import model.entity.Student;
import model.entity.Teacher;

/**
 *
 * @author ASUS
 */
public class StudentDB extends DBContext {

    public ArrayList<Student> getListStudentByClassandGrade(int gradeid, String classid, int pageindex, int pagesize, int action) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String text="order by firstName";
            if (action == 1) {
                text = "order by studentID";
            } else if (action == 3) {
                text = "order by firstName";
            } else if (action == 2) {
                text = "order by lastName";
            } else if (action == 4) {
                text = "order by gender";
            } else if (action == 5) {
                text = "order by dob";
            } else if (action == 6) {
                text = "order by Student.classID";
            }

            String sql = "SELECT    studentID, firstName, lastName, gender, dob,  photo, classID, tb1.teacherID , tb1.GradeID\n"
                    + "FROM         (select studentID, Student.classID, firstName, lastName, gender, dob, address, photo, Class.GradeID, Class.teacherID ,ROW_NUMBER() over (" + text + ") as row_index from Student\n"
                    + "			inner join Class on Student.classID = Class.classID	\n"
                    + "			inner join Grade on Grade.GradeID = Class.GradeID \n"
                    + " \n";

            if ((!classid.equals("0")) && gradeid > -1) {
                sql += " where Grade.GradeID = ? and class.classID = ? \n"
                        + " ) as tb1 where  row_index >= (?-1)*? + 1  and  row_index <= ?*? ";
            } else if (gradeid > -1 && classid.equals("0")) {
                sql += " where Grade.GradeID = ? "
                        + " ) as tb1 where  row_index >= (?-1)*? + 1  and  row_index <= ?*? ";
            } else if (gradeid == -1 && (!classid.equals("0"))) {
                sql += " where  class.classID = ?  \n"
                        + " ) as tb1 where  row_index >= (?-1)*? + 1  and  row_index <= ?*? ";
            } else {
                sql += " ) as tb1 where  row_index >= (?-1)*? + 1  and  row_index <= ?*? ";
            }
//            

            PreparedStatement stm = connection.prepareStatement(sql);
//          
//          

            if ((!classid.equals("0")) && gradeid > -1) {
                stm.setInt(1, gradeid);
                stm.setString(2, classid);
                stm.setInt(3, pageindex);
                stm.setInt(4, pagesize);
                stm.setInt(5, pageindex);
                stm.setInt(6, pagesize);
            } else if (gradeid > -1 && classid.equals("0")) {
                stm.setInt(1, gradeid);
                stm.setInt(2, pageindex);
                stm.setInt(3, pagesize);
                stm.setInt(4, pageindex);
                stm.setInt(5, pagesize);
            } else if (gradeid == -1 && (!classid.equals("0"))) {
                stm.setString(1, classid);
                stm.setInt(2, pageindex);
                stm.setInt(3, pagesize);
                stm.setInt(4, pageindex);
                stm.setInt(5, pagesize);
            } else {
                stm.setInt(1, pageindex);
                stm.setInt(2, pagesize);
                stm.setInt(3, pageindex);
                stm.setInt(4, pagesize);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentID(rs.getString(1));
                st.setFirstname(rs.getNString(2));
                st.setLastname(rs.getNString(3));
                st.setGender(rs.getBoolean(4));
                st.setDob(rs.getDate(5));
                st.setPhoto(rs.getString(6));

                ClassStudent c = new ClassStudent();
                c.setClassID(rs.getString(7));
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(8));

                c.setTeacherID(t);

                Grade d = new Grade(rs.getInt(9));
                c.setGradeID(d);

                st.setClassID(c);

                students.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void insertStudent(Student s) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [dbo].[Student]\n"
                    + "           ([studentID]\n"
                    + "           ,[classID]\n"
                    + "           ,[firstName]\n"
                    + "           ,[lastName]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[address]\n"
                    + "           ,[photo])\n"
                    + "     VALUES\n"
                    + "           (?"
                    + "           ,?"
                    + "           ,?"
                    + "           ,?"
                    + "           ,?"
                    + "           ,?"
                    + "           ,?"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getStudentID());
            stm.setString(2, s.getClassID().getClassID());
            stm.setString(3, s.getFirstname());
            stm.setString(4, s.getLastname());
            stm.setBoolean(5, s.isGender());
            stm.setDate(6, s.getDob());
            stm.setString(7, s.getAddress());
            stm.setString(8, s.getPhoto());
            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            

            for (Mark mark : s.getMarks()) {
                String sql_add_mark = "INSERT INTO [dbo].[Mark]\n"
                        + "           ([studentID]\n"
                        + "           ,[subjectID])\n"
                        + "\n"
                        + "     VALUES \n"
                        + "           ( ? \n"
                        + "            ,? )";
                PreparedStatement stm_add_mark = connection.prepareStatement(sql_add_mark);
                stm_add_mark.setString(1, s.getStudentID());
                stm_add_mark.setString(2, mark.getSubjectid().getSubjectID());
                stm_add_mark.executeUpdate();

            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Student getStudent(String id) {

        try {
            String sql = "SELECT studentID, firstName, lastName , gender , dob, address,photo, Class.classID, teacherID, Grade.GradeID   \n"
                    + "FROM         Class INNER JOIN\n"
                    + "                      Grade ON Class.GradeID = Grade.GradeID INNER JOIN\n"
                    + "                      Student ON Class.classID = Student.classID\n"
                    + " where studentID= ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentID(rs.getString(1));
                st.setFirstname(rs.getNString(2));
                st.setLastname(rs.getNString(3));
                st.setGender(rs.getBoolean(4));
                st.setDob(rs.getDate(5));
                st.setAddress(rs.getNString(6));
                st.setPhoto(rs.getString(7));

                ClassStudent c = new ClassStudent();
                c.setClassID(rs.getString(8));

                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(9));
                c.setTeacherID(t);

                Grade d = new Grade(rs.getInt(10));
                c.setGradeID(d);

                st.setClassID(c);

                return st;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteStudent(Student s) {
        String sql = "DELETE FROM [Group_Parent_Feature]\n"
                + "      WHERE username = ? \n"
                + "	  DELETE FROM [ParentAccount]\n"
                + "      WHERE username= ? "
                + " delete from Mark where studentID = ? \n"
                + " delete from Comment where studentID = ?\n"
                + "delete from Student where studentID = ? ";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getStudentID());
            stm.setString(5, s.getStudentID());
            stm.setString(2, s.getStudentID());
            stm.setString(3, s.getStudentID());
            stm.setString(4, s.getStudentID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateStudent(Student s) {
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET  \n"
                + "      [classID] = ?\n"
                + "      ,[firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[photo] = ?\n"
                + " WHERE studentID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getClassID().getClassID());
            stm.setNString(2, s.getFirstname());
            stm.setNString(3, s.getLastname());
            stm.setBoolean(4, s.isGender());
            stm.setDate(5, s.getDob());
            stm.setNString(6, s.getAddress());
            stm.setString(7, s.getPhoto());
            stm.setString(8, s.getStudentID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UpdateStudentGradeChange(Student s) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [dbo].[Student]\n"
                    + "   SET [classID] = ? \n"
                    + "      ,[firstName] = ? \n"
                    + "      ,[lastName] = ? \n"
                    + "      ,[gender] = ? \n"
                    + "      ,[dob] = ? \n"
                    + "      ,[address] = ? \n"
                    + "      ,[photo] = ? \n"
                    + " WHERE [studentID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, s.getClassID().getClassID());
            stm.setString(2, s.getFirstname());
            stm.setString(3, s.getLastname());
            stm.setBoolean(4, s.isGender());
            stm.setDate(5, s.getDob());
            stm.setString(6, s.getAddress());
            stm.setString(7, s.getPhoto());
            stm.setString(8, s.getStudentID());
            stm.executeUpdate();
//            String sql_get_id ="select studentid as sid";
//            ResultSet rs = stm.executeQuery();
//            
            String sql_delete = "delete from Mark where studentID = ? \n";
            PreparedStatement stm_delete = connection.prepareStatement(sql_delete);
            stm_delete.setString(1, s.getStudentID());
            stm_delete.executeUpdate();
            for (Mark mark : s.getMarks()) {
                String sql_add_mark = "INSERT INTO [dbo].[Mark]\n"
                        + "           ([studentID]\n"
                        + "           ,[subjectID])\n"
                        + "\n"
                        + "     VALUES \n"
                        + "           ( ? \n"
                        + "            ,? )";
                PreparedStatement stm_add_mark = connection.prepareStatement(sql_add_mark);
                stm_add_mark.setString(1, s.getStudentID());
                stm_add_mark.setString(2, mark.getSubjectid().getSubjectID());
                stm_add_mark.executeUpdate();

            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public int count(int gradeid, String classid) {

        try {
            String sql = "SELECT count(*)   \n"
                    + "FROM         Class INNER JOIN\n"
                    + "                      Grade ON Class.GradeID = Grade.GradeID INNER JOIN\n"
                    + "                      Student ON Class.classID = Student.classID\n";

            if ((!classid.equals("0")) && gradeid > -1) {
                sql += " where Grade.GradeID = ? and Class.classID = ? \n"
                        + " ";
            } else if (gradeid > -1 && classid.equals("0")) {
                sql += " where Grade.GradeID = ? "
                        + " ";
            } else if (gradeid == -1 && (!classid.equals("0"))) {
                sql += "  where Class.classID = ? \n"
                        + " ";
            } else {
                sql += " ";
            }
//            
//            
            PreparedStatement stm = connection.prepareStatement(sql);
//          
//          
            if ((!classid.equals("0")) && gradeid > -1) {
                stm.setInt(1, gradeid);
                stm.setString(2, classid);
            } else if (gradeid > -1 && classid.equals("0")) {
                stm.setInt(1, gradeid);
            } else if (gradeid == -1 && (!classid.equals("0"))) {
                stm.setString(1, classid);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Student> getStudentsByClass(String classid) {

        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [studentID]\n"
                    + "      ,[classID]\n"
                    + "      ,[firstName]\n"
                    + "      ,[lastName]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[address]\n"
                    + "      ,[photo]\n"
                    + "  FROM [dbo].[Student]\n"
                    + "  where classID = ?  "
                    + "order by firstName";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getString("studentID"));

                ClassStudent cl = new ClassStudent();
                cl.setClassID(rs.getString("classID"));

                s.setClassID(cl);

                s.setFirstname(rs.getNString("firstName"));
                s.setLastname(rs.getNString("lastName"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setAddress(rs.getNString("address"));
                s.setPhoto(rs.getString("photo"));

                students.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<Student> getStudentAll() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT studentID, firstName, lastName , gender , dob, address,photo, Class.classID, teacherID, Grade.GradeID   \n"
                    + "FROM         Class INNER JOIN\n"
                    + "                      Grade ON Class.GradeID = Grade.GradeID INNER JOIN\n"
                    + "                      Student ON Class.classID = Student.classID\n";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentID(rs.getString(1));
                st.setFirstname(rs.getNString(2));
                st.setLastname(rs.getNString(3));
                st.setGender(rs.getBoolean(4));
                st.setDob(rs.getDate(5));
                st.setAddress(rs.getNString(6));
                st.setPhoto(rs.getString(7));

                ClassStudent c = new ClassStudent();
                c.setClassID(rs.getString(8));
                Teacher t = new Teacher();
                t.setTeacherID(rs.getString(9));
                c.setTeacherID(t);

                Grade d = new Grade(rs.getInt(10));
                c.setGradeID(d);

                st.setClassID(c);

                students.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public int countStudentInClass(String classid) {

        try {
            String sql = "SELECT COUNT(*)   \n"
                    + "FROM         Student INNER JOIN\n"
                    + "                      Class ON Student.classID = Class.classID\n"
                    + "					  where Class.classID = ?";

//            
//            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
//          
//          

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Student> getStudentsByName(String name) {

        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [studentID]\n"
                    + "      ,[classID]\n"
                    + "      ,[firstName]\n"
                    + "      ,[lastName]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[address]\n"
                    + "      ,[photo]\n"
                    + "  FROM [dbo].[Student]\n"
                    + "  where firstName like ? or lastName like ?\n"
                    + "  order by firstName";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, "%" + name + "%");
            stm.setNString(2, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getString("studentID"));

                ClassStudent cl = new ClassStudent();
                cl.setClassID(rs.getString("classID"));

                s.setClassID(cl);

                s.setFirstname(rs.getNString("firstName"));
                s.setLastname(rs.getNString("lastName"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setAddress(rs.getNString("address"));
                s.setPhoto(rs.getString("photo"));

                students.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
}
