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
public class Vocabulary {
    private long voca_id;
    private long id_clien;
    private long cate_id;
    private long user_id;
    private String english;
    private String vietnamese;

    public Vocabulary() {
    }

    public long getVoca_id() {
        return voca_id;
    }

    public void setVoca_id(long voca_id) {
        this.voca_id = voca_id;
    }

    public long getId_clien() {
        return id_clien;
    }

    public void setId_clien(long id_clien) {
        this.id_clien = id_clien;
    }

    public long getCate_id() {
        return cate_id;
    }

    public void setCate_id(long cate_id) {
        this.cate_id = cate_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }
    
}
