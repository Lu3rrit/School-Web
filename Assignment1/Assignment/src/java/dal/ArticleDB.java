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
import model.entity.Article;

/**
 *
 * @author ASUS
 */
public class ArticleDB extends DBContext {

    public ArrayList<Article> getArticles(int pageindex, int pagesize) {
        ArrayList<Article> arts = new ArrayList<>();
        try {
            String sql = "SELECT    articleID, title, image, [content], date\n"
                    + "FROM         (select *, ROW_NUMBER() over (order by articleID desc ) as row_index from Article ) tbl\n"
                    + "where  row_index >= ( ? -1 )*? + 1 and row_index <= ? * ?\n"
                    + "";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                a.setId(rs.getInt(1));
                a.setTitle(rs.getNString(2));
                a.setImage(rs.getString(3));
                a.setContent(rs.getNString(4));
                a.setDate(rs.getDate(5));
                arts.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arts;
    }

    public Article insertArticle(Article art) {

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Article]\n"
                    + "           ([title]\n"
                    + "           ,[image]\n"
                    + "           ,[content]\n"
                    + "           ,[date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, art.getTitle());
            stm.setString(2, art.getImage());
            stm.setNString(3, art.getContent());

            stm.executeUpdate();

            String sql_get_id = " select @@IDENTITY as articleID ";
            PreparedStatement stm_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = stm_get_id.executeQuery();
            if (rs.next()) {
                art.setId(rs.getInt("articleID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return art;
    }

    public Article getArticleById(int aid) {
        try {
            String sql = "SELECT [articleID]\n"
                    + "      ,[title]\n"
                    + "      ,[image]\n"
                    + "      ,[content]\n"
                    + "      ,[date]\n"
                    + "  FROM [dbo].[Article]\n"
                    + "  where articleID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Article art = new Article();
                art.setId(rs.getInt(1));
                art.setTitle(rs.getNString(2));
                art.setImage(rs.getString(3));
                art.setContent(rs.getNString(4));
                art.setDate(rs.getDate(5));
                return art;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Article art) {

        String sql = "UPDATE [dbo].[Article]\n"
                + "   SET [title] = ? \n"
                + "      ,[image] = ? \n"
                + "      ,[content] = ?\n"
                + "      \n"
                + " WHERE articleID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setNString(1, art.getTitle());
            stm.setString(2, art.getImage());
            stm.setNString(3, art.getContent());
            stm.setInt(4, art.getId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int count() {
        try {
            String sql = "select COUNT(*) from Article";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
