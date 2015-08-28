/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luand_000
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username;
        username = (String) req.getSession().getAttribute("username");

        //cookie
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                username = cookies[i].getValue();
                System.out.println("Cookie--MyFilter: " + cookies[i].getName() + "--" + username);
            }
        }

        if (username != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }
    }

    @Override
    public void destroy() {

    }

}
