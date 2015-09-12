/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Administrator;
import com.englishvocabulary.models.AdministratorModels;
import com.englishvocabulary.models.ResultLogin;
import com.englishvocabulary.utils.MessageUtil;
import com.englishvocabulary.utils.ValidatorUtils;
import java.io.Serializable;
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
public class LoginBean extends MessageUtil implements Serializable {

    private Administrator a;
    private AdministratorModels models;
    private String focus = "fullname";
            
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        a = new Administrator();
        models = new AdministratorModels();
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
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
                Cookie username = new Cookie("username", a.getUsername());
                System.out.println(">>>>>>>>addCookie");
                username.setMaxAge(60 * 60 * 48);
                username.setPath("/");
                username.setHttpOnly(true);
                response.addCookie(username);
                
            }

            return "pages/index?faces-redirect=true";
        } else {
            return "";
        }
    }

    public boolean validateData() throws Exception {
        ResultLogin login = models.login(a);
        if (login.isCheck()) {
            return true;
        } else {
            addErrorMsg("Sai tài khoản hoặc mật khẩu hoặc tài khoản của bạn không được kích hoạt");
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
    
    public void register(){
        if(validateRegister()){
            ResultLogin result = models.registerAdmin(a);
            if(result.isCheck()){
                addSuccessMsg("Đăng ký thành công");
                a.setFullname("");
                a.setUsername("");
                a.setPassword("");
                a.setRePassword("");
            } else {
                addErrorMsg(result.getFullname());
                focus = "username";
            }
        }
    }
    
    public boolean validateRegister(){
        if(!ValidatorUtils.isUsername(a.getUsername())){
            addErrorMsg("Tên đăng nhập không được chứa ký tự đặc biệt.");
            focus = "username";
            return false;
        }
        if(!a.getPassword().equals(a.getRePassword())){
            addErrorMsg("Hai mật khẩu bạn nhập không trùng khớp.");
            focus = "password";
            return false;
        } else {
            return true;
        } 
    }
}
