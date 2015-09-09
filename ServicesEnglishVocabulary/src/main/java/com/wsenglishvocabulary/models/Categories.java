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
public class Categories {
    private long cate_id;
    private long id_clien;
    private long user_id;
    private String name;

    public Categories() {
    }

    public long getCate_id() {
        return cate_id;
    }

    public void setCate_id(long cate_id) {
        this.cate_id = cate_id;
    }

    public long getId_clien() {
        return id_clien;
    }

    public void setId_clien(long id_clien) {
        this.id_clien = id_clien;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
