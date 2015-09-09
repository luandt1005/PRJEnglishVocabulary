/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.Result;
import com.wsenglishvocabulary.models.ResultLogin;
import com.wsenglishvocabulary.models.Users;
import com.wsenglishvocabulary.models.UsersModels;
import com.wsenglishvocabulary.utils.Utils;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author LuanDT
 */
@Path("user")
public class UsersServices {

    @Context
    private UriInfo context;
    private UsersModels models;

    /**
     * Creates a new instance of UsersServices
     */
    public UsersServices() {
        models = new UsersModels();
    }

    /**
     * Retrieves representation of an instance of
     * com.wsenglishvocabulary.rest.UsersServices
     *
     * @return an instance of java.util.ArrayList
     */
    @GET
    @Produces("application/json")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        ArrayList<Users> arr = new ArrayList<Users>();
        try {
            arr = models.getAllUsers();
        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        json = Result.success(arr);

        return json;
    }

    @POST
    @Path("/add")
    public JSONObject add(@FormParam("username") String username, @FormParam("password") String password, @FormParam("fullname") String fullname) {
        JSONObject json = new JSONObject();
        Users u = new Users();
        u.setUsername(username);
        u.setPassword(Utils.generateMD5(password));
        u.setFullname(fullname);

        try {
            boolean checkUsername = models.checkUsername(username);
            if (!checkUsername) {
                boolean inserted = models.addUser(u);
                if (inserted) {
                    json = Result.success();
                } else {
                    json = Result.error();
                }
            } else {
                json = Result.error("Tên đăng nhập đã tồn tại bạn vui lòng chọn một tên đăng nhập khác.");
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/login")
    public JSONObject login(@FormParam("username") String username, @FormParam("password") String password) {
        JSONObject json = new JSONObject();
        Users u = new Users();
        u.setUsername(username);
        u.setPassword(Utils.generateMD5(password));

        try {
            ResultLogin login = models.login(u);
            if (login.isCheck()) {
                json = Result.successLogin(login.getUser_id(), login.getFullname());
            } else {
                json = Result.error("Sai tài khoản hoặc mật khẩu.");
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }
}
