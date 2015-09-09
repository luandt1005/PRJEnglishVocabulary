/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author luand_000
 */
public class DbPool {
    private static Log log = new Log("DbPool");
    private static LinkedList pool = new LinkedList();
    public final static int MAX_CONNECTIONS = 20;
    public final static int INIT_CONNECTIONS = 10;

    public DbPool() {
        build(INIT_CONNECTIONS);
    }
    
//    public static void main(String[] args) {
//        makeDbConnection();
//    }

    private static void build(int number) {
        log.Log("Init " + number + "connection");
        Connection c = null;
        release(); //đóng tất cả các kết nối nếu còn khi khởi tạo.
        //init connection
        for(int i = 0; i < number; i++){
            try {
                c = makeDbConnection(); //tạo connection mới.
            } catch (Exception e) {
                log.LogError(e.getMessage());
            }
            if(c != null){
                pool.addLast(c);
            }
        }
        log.Log("Number connection " + number);
    }
    //tạo connection
    private static Connection makeDbConnection() {
        Connection c = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=EnglishVocabulary";
            String user = "luandt";
            String pass = "luandt1005";
            
            c = DriverManager.getConnection(dbUrl, user, pass);
            log.Log("Connected DB");
        } catch (Exception e) {
            log.LogError(e.getMessage());
        }
        return c;
    }
    
    //xoa tat ca cac connection trong pool.
    private static void release() {
        synchronized (pool){
            for (Iterator iterator = pool.iterator(); iterator.hasNext();) {
                Connection c = (Connection) iterator.next();
                try {
                    c.close();
                    log.Log("Closing connection.");
                } catch (Exception e) {
                    log.LogError("Cannot close connection.");
                }
            }
            pool.clear();
        }
    }
    
    public static Connection getConnection(){
        Connection c = null;
        try {
            synchronized(pool){
                c = (Connection) pool.removeFirst();
            }
            if(c == null){
                c = makeDbConnection();
            }
            try {
                c.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.LogError("getConnection(): Error executing ----->" + e.toString());
            try {
                log.Log("Make connection again.");
                c = makeDbConnection();
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return c;
    }
    
    //close connection
    public static void closeConnection(Connection c){
        try {
            if(c == null || c.isClosed()){
                log.Log("Connection is null or closed: " + c);
                return;
            }
            if(pool.size() > MAX_CONNECTIONS){
                c.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        synchronized(pool){
            pool.addLast(c);
            pool.notify();
        }
    }
    
    //phương thức close một connection và preparedStatement
    public static void releaseConnection(Connection conn, PreparedStatement preStmt) {
        closeConnection(conn);
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //close connection, preparedstatement, resultset
    public static void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        releaseConnection(conn, preStmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(Connection conn, Statement Stmt, ResultSet rs) {
        closeConnection(conn);
        try {
            if (Stmt != null) {
                Stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        releaseConnection(conn, preStmt, rs);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(Connection conn, CallableStatement cs, ResultSet rs) {
        closeConnection(conn);
        try {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
