/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Administrator;
import com.englishvocabulary.utils.MessageUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LuanDT
 */
@ManagedBean
@RequestScoped
public class LoginBean extends MessageUtil{

    private Administrator a;
            
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        a = new Administrator();
    }

    public Administrator getA() {
        return a;
    }

    public void setA(Administrator a) {
        this.a = a;
    }

    public String checkUser() throws Exception {
        if (validateData()) {
            HttpSession session = SessionBean.session(true);
            session.setAttribute("username", a.getUsername());

            //cookie
            if (a.isRemember()) {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                Cookie user = new Cookie("username", a.getUsername());
                System.out.println(">>>>>>>>addCookie");
                user.setMaxAge(60 * 60 * 48);
                user.setPath("/");
                user.setHttpOnly(true);
                response.addCookie(user);
            }

            return "pages/index?faces-redirect=true";
        } else {
            return "";
        }
    }

    public boolean validateData() throws Exception {
//        boolean check = true;
        if (a.getEmail().equals("luandt")) {
            a.setUsername("Đinh Thế Luân");
            return true;
        } else {
            addErrorMsg("Sai tài khoản hoặc mật khẩu");
            return false;
        }
    }
    
    public String logout() {
        //hủy session
        HttpSession session = SessionBean.session(false);
        session.invalidate();

        //hủy cookie
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            cookie[i].setMaxAge(0);
            cookie[i].setPath("/");
            cookie[i].setHttpOnly(true);
            response.addCookie(cookie[i]);
        }

        return "/faces/login?faces-redirect=true";
    }
}
