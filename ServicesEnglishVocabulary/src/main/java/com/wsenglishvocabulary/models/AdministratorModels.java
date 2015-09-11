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
public class AdministratorModels {

    //get All Admin
    public ArrayList<Administrator> getAllAdmin() throws Exception {
        ArrayList<Administrator> data = new ArrayList<Administrator>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection c = null;
        try {
            String sql = "SELECT * FROM administrator ORDER BY id DESC;";
            c = DbPool.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Administrator a = new Administrator();
                a.setId(resultSet.getLong(1));
                a.setUsername(resultSet.getString(2));
                a.setPassword(resultSet.getString(3));
                a.setFullname(resultSet.getString(4));
                a.setAccess(resultSet.getInt(5));
                a.setStatus(resultSet.getInt(6));

                data.add(a);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbPool.releaseConnection(c, statement, resultSet);
        }
        return data;
    }

    //add Admin
    public boolean addAdmin(Administrator a) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "INSERT INTO administrator VALUES (?, ?, ?, ?, ?);";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);

            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFullname());
            ps.setInt(4, a.getAccess());
            ps.setInt(5, a.getStatus());
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }

    public ResultLogin login(Administrator a) throws Exception {
        boolean check = false;
        long id = -1;
        int access = -1;
        String fullname = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM administrator WHERE username = " + "'" + a.getUsername() + "' and password = " + "'" + a.getPassword() + "' and status = 1;";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                check = true;
                id = resultSet.getLong(1);
                fullname = resultSet.getString(4);
                access = resultSet.getInt(5);
            } else {
                check = false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(connection, statement, resultSet);
        }
        return new ResultLogin(check, id, fullname, access);
    }
    
    //get infor Admin by username
    public ResultLogin infoAdmin(String username) throws Exception {
        long id = -1;
        int access = -1;
        int status = -1;
        String fullname = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM administrator WHERE username = " + "'" + username + "';";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getLong(1);
                fullname = resultSet.getString(4);
                access = resultSet.getInt(5);
                status = resultSet.getInt(6);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(connection, statement, resultSet);
        }
        return new ResultLogin(id, fullname, access, status);
    }

    //check exist username
    public boolean checkUsername(String username) throws Exception {
        boolean check = false;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT Count(*) as count FROM administrator WHERE username = " + "'" + username + "';";
            connection = DbPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
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

    //delete
    public boolean delete(ArrayList<Administrator> list) throws SQLException {
        boolean result = false;
        PreparedStatement statement = null;
        Connection c = null;
        try {
            c = DbPool.getConnection();
            c.setAutoCommit(false);
            for (Administrator a : list) {
                String SQL = "DELETE administrator WHERE id = ?;";
                statement = c.prepareStatement(SQL);
                statement.setLong(1, a.getId());
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

    //update access
    public boolean updateAccess(long id, int access) throws Exception {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "UPDATE administrator SET access = ? WHERE id = ?;";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, access);
            ps.setLong(2, id);
            
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            DbPool.releaseConnection(c, ps);
        }
        return result;
    }
    
    //update status
    public boolean updateStatus(long id, int status) throws Exception {
        boolean result = false;
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String sql = "UPDATE administrator SET status = ? WHERE id = ?;";
            c = DbPool.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setLong(2, id);
            
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
