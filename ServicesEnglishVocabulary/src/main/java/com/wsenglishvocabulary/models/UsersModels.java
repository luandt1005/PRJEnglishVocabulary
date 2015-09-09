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
public class UsersModels {

    //get All User
    public ArrayList<Users> getAllUsers() throws Exception {
        ArrayList<Users> data = new ArrayList<Users>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM users ORDER BY user_id DESC;";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Users u = new Users();
                u.setUser_id(resultSet.getLong(1));
                u.setUsername(resultSet.getString(2));
                u.setPassword(resultSet.getString(3));
                u.setFullname(resultSet.getString(4));

                data.add(u);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //add user
    public boolean addUser(Users u) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "INSERT INTO users VALUES (?, ?, ?);";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());

            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }

    public ResultLogin login(Users u) throws Exception {
        boolean check = false;
        long user_id = -1;
        String fullname = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM users WHERE username = " + "'" + u.getUsername() + "' and password = " + "'" + u.getPassword() + "';";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                check = true;
                user_id = resultSet.getLong(1);
                fullname = resultSet.getString(4);
            } else {
                check = false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(connection, statement, resultSet);
        }
        return new ResultLogin(check, user_id, fullname);
    }
    
    //check exist username
    public boolean checkUsername(String username) throws Exception {
        boolean check = false;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT Count(*) as count FROM users WHERE username = " + "'" + username + "';";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count > 0){
                    check = true;
                } else {
                    check = false;
                }
                
            } else {
                check = false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(connection, statement, resultSet);
        }
        return check;
    }
    
    //check exist username
    public boolean checkUserID(long user_id) throws Exception {
        boolean check = false;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT Count(*) as count FROM users WHERE user_id = " + "'" + user_id + "';";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count > 0){
                    check = true;
                } else {
                    check = false;
                }
                
            } else {
                check = false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(connection, statement, resultSet);
        }
        return check;
    }
    
}
