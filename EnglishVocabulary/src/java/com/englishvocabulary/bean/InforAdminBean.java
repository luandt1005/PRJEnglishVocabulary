/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.AdministratorModels;
import com.englishvocabulary.models.ResultLogin;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author LuanDT
 */
@ManagedBean
@ViewScoped
public class InforAdminBean implements Serializable{

    private AdministratorModels models;
    private static ResultLogin info;
    /**
     * Creates a new instance of InforAdminBean
     */
    public InforAdminBean() {
        models = new AdministratorModels();
        info = models.infoAdmin(SessionBean.username());
    }

    public static String fullname(){
        String fullname = info.getFullname();
        return fullname;
    }
    
    public static int access(){
        int access = info.getAccess();
        return access;
    }
    
    public static int status(){
        int status = info.getStatus();
        return status;
    }
    
    public static String trangThai(){
        String name = "";
        if(info.getStatus() == 1) {
            name = "Hoạt động";
        } else {
            name = "Tạm dừng";
        }
        return name;
    }
    
    public static String quyen(){
        String name = "";
        if(info.getAccess() == 0) {
            name = "Khách";
        } if(info.getAccess() == 1) {
            name = "Quản trị";
        } else {
            name = "Administrator";
        }
        return name;
    }
}
