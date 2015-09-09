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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LuanDT
 */
public class CategoriesModels {

    //get All Vocabulary by user_id

    public ArrayList<Categories> getAllCategoriesByUserID(long user_id) throws Exception {
        ArrayList<Categories> data = new ArrayList<Categories>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM categories WHERE user_id = " + user_id + ";";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Categories categories = new Categories();
                categories.setCate_id(resultSet.getLong(1));
                categories.setId_clien(resultSet.getLong(2));
                categories.setName(resultSet.getString(3));
                categories.setUser_id(resultSet.getLong(4));

                data.add(categories);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //insert
    public long addCategori(String sql) throws Exception {
        long id = -1;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return id;
    }
}
