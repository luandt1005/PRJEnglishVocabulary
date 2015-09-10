/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.Categories;
import com.wsenglishvocabulary.models.CategoriesModels;
import com.wsenglishvocabulary.models.Result;
import com.wsenglishvocabulary.models.ResultInsertVoca;
import com.wsenglishvocabulary.models.UsersModels;
import com.wsenglishvocabulary.models.Vocabulary;
import com.wsenglishvocabulary.models.VocabularyModels;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author LuanDT
 */
@Path("vocabulary")
public class VocabularyServices {

    @Context
    private UriInfo context;
    private VocabularyModels vm;
    private CategoriesModels cm;
    private UsersModels um;

    /**
     * Creates a new instance of Vocabulary
     */
    public VocabularyServices() {
        vm = new VocabularyModels();
        cm = new CategoriesModels();
        um = new UsersModels();
    }

    @GET
    @Produces("application/json")
    public String getJson() {
        return "vocabulary";
    }

    @POST
    @Path("/alldata")
    public JSONObject getJson(@FormParam("username") String username, @FormParam("userId") String userId) {
        JSONObject json = new JSONObject();
        ArrayList<Vocabulary> arrVoca = new ArrayList<Vocabulary>();
        ArrayList<Categories> arrCate = new ArrayList<Categories>();
        try {
            boolean checkUserID = um.checkUserID(Long.parseLong(userId));
            if (checkUserID) {
                arrVoca = vm.getAllVocabularyByUserID(Long.parseLong(userId));
                arrCate = cm.getAllCategoriesByUserID(Long.parseLong(userId));
                json = Result.successAllData(arrVoca, arrCate);
            } else {
                json = Result.error("Tài khoản không tồn tại!");
            }

        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        return json;
    }

    @POST
    @Path("/insert")
    public JSONObject insert(@FormParam("username") String username, @FormParam("userId") String userId, @FormParam("json") String json) {
        JSONObject result = new JSONObject();
        ArrayList<ResultInsertVoca> arrResultInsert = new ArrayList<ResultInsertVoca>();
        try {
            boolean checkUserID = um.checkUserID(Long.parseLong(userId));
            if (checkUserID) {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String sql = "INSERT INTO " + object.getString("table") + " VALUES (" + object.getString("sql").substring(5) + ");";
                    long id_server = vm.addData(sql);
                    String id_clien = object.getString("id_clien");
                    String table = object.getString("table");
                    
                    if(id_server != -1){
                        arrResultInsert.add(new ResultInsertVoca(table, id_clien, "" + id_server));
                    }
                }
                result = Result.successInsertVoca(arrResultInsert);
            } else {
                result = Result.error("Tài khoản không tồn tại!");
            }
        } catch (Exception ex) {
            result = Result.error();
            ex.printStackTrace();
        }

        return result;
    }

    @POST
    @Path("/update")
    public JSONObject update(@FormParam("username") String username, @FormParam("userId") String userId, @FormParam("json") String json) {
        JSONObject result = new JSONObject();
        try {
            boolean checkUserID = um.checkUserID(Long.parseLong(userId));
            if (checkUserID) {
                String sql;
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String table = object.getString("table");
                    if (table.equals("vocabularies")) {
                        sql = "UPDATE " + table + " SET " + object.getString("sql") + " WHERE voca_id = " + object.getString("voca_id") + ";";
                    } else {
                        sql = "UPDATE " + table + " SET " + object.getString("sql") + " WHERE cate_id = " + object.getString("cate_id") + ";";
                    }
                    boolean check = vm.updateData(sql);
                }
                result = Result.success();
            } else {
                result = Result.error("Tài khoản không tồn tại!");
            }
        } catch (Exception ex) {
            result = Result.error();
            ex.printStackTrace();
        }

        return result;
    }

    @POST
    @Path("/delete")
    public JSONObject delete(@FormParam("username") String username, @FormParam("userId") String userId, @FormParam("json") String json) {
        JSONObject result = new JSONObject();
        try {
            boolean checkUserID = um.checkUserID(Long.parseLong(userId));
            if (checkUserID) {
                String sql;
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String table = object.getString("table");
                    if (table.equals("vocabularies")) {
                        sql = "DELETE FROM " + table + " WHERE voca_id IN(" + object.getString("sql") + ");";
                    } else {
                        sql = "DELETE FROM " + table + " WHERE cate_id IN(" + object.getString("sql") + ");";
                    }
                    boolean check = vm.updateData(sql);
                }
                result = Result.success();
            } else {
                result = Result.error("Tài khoản không tồn tại!");
            }
        } catch (Exception ex) {
            result = Result.error();
            ex.printStackTrace();
        }

        return result;
    }
}
