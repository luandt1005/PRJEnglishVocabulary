/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.models;

import java.io.Serializable;

/**
 *
 * @author LuanDT
 */
public class Feedback implements Serializable {

    private long id;
    private String content;
    private String date;
    private boolean check;

    public Feedback() {
    }

    public Feedback(long id, String content, String date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
