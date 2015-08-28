/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LuanDT
 */
@ManagedBean
@SessionScoped
public class SessionBean {

    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }
    
    public static HttpSession session(boolean value){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(value);
        return session;
    }
    
    public static String username(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        return username;
    }
}
