/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.models;

/**
 *
 * @author LuanDT
 */
public class ResultInsertVoca {
    private String table;
    private String id_client;
    private String id_server;

    public ResultInsertVoca(String table, String id_client, String id_server) {
        this.table = table;
        this.id_client = id_client;
        this.id_server = id_server;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getId_server() {
        return id_server;
    }

    public void setId_server(String id_server) {
        this.id_server = id_server;
    }
    
}
