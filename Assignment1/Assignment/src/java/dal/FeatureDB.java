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
import model.account.Feature;

/**
 *
 * @author ASUS
 */
public class FeatureDB extends DBContext {

    public ArrayList<Feature> getFeatures() {
        ArrayList<Feature> features = new ArrayList<>();
        try {
            String sql = "SELECT [fid]\n"
                    + "      ,[url]\n"
                    + "  FROM [dbo].[Feature]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Feature f = new Feature();
                f.setFid(rs.getInt("fid"));
                f.setUrl(rs.getString("url"));
                features.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeatureDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return features;
    }
}
