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
import javax.naming.spi.DirStateFactory;
import model.entity.Grade;

/**
 *
 * @author ASUS
 */
public class GradeDB extends DBContext{
    public ArrayList<Grade> getListGrade(){
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "select * from Grade";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                g.setGradeID(rs.getInt("GradeID"));
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }
   

}
