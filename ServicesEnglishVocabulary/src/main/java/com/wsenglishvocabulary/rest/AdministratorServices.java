/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.Administrator;
import com.wsenglishvocabulary.models.AdministratorModels;
import com.wsenglishvocabulary.models.Result;
import com.wsenglishvocabulary.models.ResultLogin;
import com.wsenglishvocabulary.utils.DbPool;
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
@Path("administrator")
public class AdministratorServices {

    @Context
    private UriInfo context;
    private AdministratorModels models;

    /**
     * Creates a new instance of AdministratorServices
     */
    public AdministratorServices() {
        models = new AdministratorModels();
    }

    /**
     * Retrieves representation of an instance of
     * com.wsenglishvocabulary.rest.AdministratorServices
     *
     * @return an instance of org.codehaus.jettison.json.JSONObject
     */
    @GET
    @Produces("application/json")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        ArrayList<Administrator> arr = new ArrayList<Administrator>();
        try {
            arr = models.getAllAdmin();
        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        json = Result.successAdmin(arr);

        return json;
    }

    @POST
    @Path("/add")
    public JSONObject add(@FormParam("username") String username, @FormParam("password") String password, @FormParam("fullname") String fullname, @FormParam("access") String access, @FormParam("status") String status) {
        JSONObject json = new JSONObject();
        Administrator a = new Administrator();
        a.setUsername(username);
        a.setPassword(Utils.generateMD5(password));
        a.setFullname(fullname);
        a.setAccess(Integer.parseInt(access));
        a.setStatus(Integer.parseInt(status));

        try {
            boolean checkUsername = models.checkUsername(username);
            if (!checkUsername) {
                boolean inserted = models.addAdmin(a);
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
        Administrator a = new Administrator();
        a.setUsername(username);
        a.setPassword(Utils.generateMD5(password));

        try {
            ResultLogin login = models.login(a);
            if (login.isCheck()) {
                json = Result.successLoginAdmin(login.getUser_id(), login.getFullname(), login.getAccess());
                DbPool dbPool = new DbPool();
            } else {
                json = Result.error("Sai tài khoản hoặc mật khẩu.");
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/info")
    public JSONObject info(@FormParam("username") String username) {
        JSONObject json = new JSONObject();

        try {
            ResultLogin login = models.infoAdmin(username);
            json = Result.successInfoAdmin(login.getUser_id(), login.getFullname(), login.getAccess(), login.getStatus());
//            DbPool dbPool = new DbPool();
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/delete")
    public JSONObject delete(@FormParam("id") String id) {
        JSONObject json = new JSONObject();
        String[] listId = id.split(",");
        ArrayList<Administrator> arr = new ArrayList<Administrator>();
        for (int i = 0; i < listId.length; i++) {
            arr.add(new Administrator(Long.parseLong(listId[i])));
        }
        try {
            boolean deleted = models.delete(arr);
            if (deleted) {
                json = Result.success();
            } else {
                json = Result.error();
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/updateStatus")
    public JSONObject updateStatus(@FormParam("id") String id, @FormParam("status") String status) {
        JSONObject json = new JSONObject();
        try {
            boolean check = models.updateStatus(Long.parseLong(id), Integer.parseInt(status));
            if (check) {
                json = Result.success();
            } else {
                json = Result.error();
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/updateAccess")
    public JSONObject updateAccess(@FormParam("id") String id, @FormParam("access") String access) {
        JSONObject json = new JSONObject();
        try {
            boolean check = models.updateAccess(Long.parseLong(id), Integer.parseInt(access));
            if (check) {
                json = Result.success();
            } else {
                json = Result.error();
            }
        } catch (Exception e) {
            json = Result.error();
            e.printStackTrace();
        }

        return json;
    }
}
