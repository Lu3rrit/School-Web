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
import model.entity.Subject;

/**
 *
 * @author ASUS
 */
public class subjectDB extends DBContext {

    public ArrayList<Subject> listSubjectInClass(String classid) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "	SELECT    Class.classID, Subject.subjectID, Subject.subjectName\n"
                    + "	FROM         Class INNER JOIN\n"
                    + "						  Grade ON Class.GradeID = Grade.GradeID INNER JOIN\n"
                    + "						  SubjectGrade ON Grade.GradeID = SubjectGrade.GradeID INNER JOIN\n"
                    + "						  Subject ON SubjectGrade.subjectID = Subject.subjectID\n"
                    + "						  where classID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setSubjectID(rs.getString(2));
                sub.setSubjectName(rs.getNString(3));
                subjects.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(subjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public Subject getSubject(String sjid) {
        try {
            String sql = "SELECT [subjectID]\n"
                    + "      ,[subjectName]\n"
                    + "  FROM [dbo].[Subject]\n"
                    + "  where subjectID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sjid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Subject sj = new Subject();
                sj.setSubjectID(rs.getString(1));
                sj.setSubjectName(rs.getNString(2));
                return sj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(subjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
