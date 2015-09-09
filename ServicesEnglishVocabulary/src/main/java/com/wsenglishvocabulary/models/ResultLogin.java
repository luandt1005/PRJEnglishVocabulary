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
public class ResultLogin {

    private boolean check;
    private long user_id;
    private String fullname;

    public ResultLogin(boolean check, long user_id, String fullname) {
        this.check = check;
        this.user_id = user_id;
        this.fullname = fullname;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
