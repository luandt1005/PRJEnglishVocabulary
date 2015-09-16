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
public class GcmMessageModel {

    //get All Feedback

    public ArrayList<GcmMessage> getAllMessage() throws Exception {
        ArrayList<GcmMessage> data = new ArrayList<GcmMessage>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM gcm_message ORDER BY id DESC;";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                GcmMessage gm = new GcmMessage();
                gm.setId(resultSet.getLong(1));
                gm.setTitle(resultSet.getString(2));
                gm.setContent(resultSet.getString(3));
                gm.setUrl_image(resultSet.getString(4));
                gm.setLink(resultSet.getString(5));
                gm.setDate_create(resultSet.getString(6));

                data.add(gm);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //add Feedback
    public long addMessage(GcmMessage message) throws SQLException {
        long id = -1;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "INSERT INTO gcm_message (title, content, url_image, link) VALUES (?, ?, ?, ?);";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getTitle());
            ps.setString(2, message.getContent());
            ps.setString(3, message.getUrl_image());
            ps.setString(4, message.getLink());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return id;
    }

    //delete list
    public boolean deleteList(ArrayList<GcmMessage> list) throws SQLException {
        boolean result = false;
        PreparedStatement statement = null;
        Connection c = null;
        try {
            c = DbPool.getConnection();
            c.setAutoCommit(false);
            for (GcmMessage message : list) {
                String SQL = "DELETE gcm_message WHERE id = ?;";
                statement = c.prepareStatement(SQL);
                statement.setLong(1, message.getId());
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
