/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.models;

import com.wsenglishvocabulary.utils.DbPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LuanDT
 */
public class FeedbackModel {

    //get All Feedback
    public ArrayList<Feedback> getAllFeedback() throws Exception {
        ArrayList<Feedback> data = new ArrayList<Feedback>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM feedback ORDER BY id DESC;";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Feedback f = new Feedback();
                f.setId(resultSet.getLong(1));
                f.setContent(resultSet.getString(2));

                data.add(f);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //add Feedback
    public boolean addFeedback(String content) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "INSERT INTO feedback VALUES (?);";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);

            ps.setString(1, content);

            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }

    //delete
    public boolean deleteFeedback(long id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String SQL = "DELETE feedback WHERE id = ?;";
            c = DbPool.getConnection();
            ps = c.prepareStatement(SQL);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            System.out.println("DELETE");
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }
    
    //delete list
    public boolean deleteList(ArrayList<Feedback> list) throws SQLException {
        boolean result = false;
        PreparedStatement statement = null;
        Connection c = null;
        try {
            c = DbPool.getConnection();
            c.setAutoCommit(false);
            for(Feedback f : list){
                String SQL = "DELETE feedback WHERE id = ?;";
                statement = c.prepareStatement(SQL);
                statement.setLong(1, f.getId());
                statement.executeUpdate();
            }
            c.commit();
            result = true;
        } catch (Exception e) {
            c.rollback();
            c.setAutoCommit(true);
            throw e;
        } finally {
            DbPool.releaseConnection(c, statement);
        }
        return result;
    }
}
