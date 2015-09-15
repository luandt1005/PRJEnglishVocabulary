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
import java.util.List;

/**
 *
 * @author LuanDT
 */
public class GcmRegistrationModel {
    //get All gcm_registration
    public List<String> getAllRegId() throws Exception {
        List<String> data = new ArrayList<String>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM gcm_registration ORDER BY id DESC;";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String reg_id = resultSet.getString(2);

                data.add(reg_id);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //add RegId
    public boolean addRegId(String reg_id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "INSERT INTO gcm_registration (reg_id) VALUES (?);";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, reg_id);
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }
}
