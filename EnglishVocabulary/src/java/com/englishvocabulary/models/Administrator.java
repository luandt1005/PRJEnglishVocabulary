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
public class Administrator implements Serializable {

    private long id;
    private String username;
    private String fullname;
    private String password;
    private String rePassword;
    private int access;
    private int status;
    private boolean remember;
    private String quyen = "";
    private String trangthai = "";

    public String getTrangthai() {
        if (status == 1) {
            trangthai = "Hoạt động";
        } else {
            trangthai = "Tạm dừng";
        }
        return trangthai;
    }

    public String getQuyen() {
        if (access == 0) {
            quyen = "Khách";
        } else if (access == 1) {
            quyen = "Quản trị";
        } else {
            quyen = "Administrator";
        }
        return quyen;
    }

    public Administrator() {
    }

    public Administrator(long id, String username, String fullname, int access, int status) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.access = access;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
