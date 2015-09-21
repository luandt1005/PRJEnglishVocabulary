/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.models;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author LuanDT
 */
public class Result {

    //success no arr
    public static JSONObject success() {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    //success insert message
    public static JSONObject successInsertMsg(long id) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("id", id);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    //success user login
    public static JSONObject successLogin(long user_id, String fullname) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("user_id", user_id);
            object.put("fullname", fullname);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    //success login facebook
    public static JSONObject successLoginFace(long user_id) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("user_id", user_id);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    public static JSONObject successLoginAdmin(long id, String fullname, int access) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("user_id", id);
            object.put("fullname", fullname);
            object.put("access", access);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    public static JSONObject successInfoAdmin(long id, String fullname, int access, int status) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("user_id", id);
            object.put("fullname", fullname);
            object.put("access", access);
            object.put("status", status);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    //error
    public static JSONObject error() {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 0);
            object.put("error", 1);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    //error message
    public static JSONObject error(String msg) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", 0);
            object.put("error", 1);
            object.put("error_msg", msg);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    //success user
    public static JSONObject success(ArrayList<Users> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            for (int i = 0; i < arr.size(); i++) {
                row = new JSONObject();
                row.put("id", arr.get(i).getUser_id());
                row.put("username", arr.get(i).getUsername());
                row.put("fullname", arr.get(i).getFullname());
                jsonArray.put(row);
            }
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", jsonArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    
    //success Administrator
    public static JSONObject successAdmin(ArrayList<Administrator> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            for (int i = 0; i < arr.size(); i++) {
                row = new JSONObject();
                row.put("id", arr.get(i).getId());
                row.put("username", arr.get(i).getUsername());
                row.put("fullname", arr.get(i).getFullname());
                row.put("access", arr.get(i).getAccess());
                row.put("status", arr.get(i).getStatus());
                jsonArray.put(row);
            }
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", jsonArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    
    //success feedback
    public static JSONObject successFeedback(ArrayList<Feedback> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            for (int i = 0; i < arr.size(); i++) {
                row = new JSONObject();
                row.put("id", arr.get(i).getId());
                row.put("content", arr.get(i).getContent());
                row.put("date", arr.get(i).getDate());
                jsonArray.put(row);
            }
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", jsonArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    
    //success message
    public static JSONObject successGcmMsg(ArrayList<GcmMessage> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            for (int i = 0; i < arr.size(); i++) {
                row = new JSONObject();
                row.put("id", arr.get(i).getId());
                row.put("title", arr.get(i).getTitle());
                row.put("content", arr.get(i).getContent());
                row.put("url_image", arr.get(i).getUrl_image());
                row.put("link", arr.get(i).getLink());
                row.put("date_create", arr.get(i).getDate_create());
                row.put("sender", arr.get(i).getSender());
                jsonArray.put(row);
            }
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", jsonArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    
    //success RegId
    public static JSONObject successRegId(List<String> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", arr);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    
    //success all data
    public static JSONObject successAllData(ArrayList<Vocabulary> arrVoca, ArrayList<Categories> arrCate) {
        JSONObject object = new JSONObject();
        JSONObject objectData = new JSONObject();
        JSONArray jsonArrayVoca = new JSONArray();
        JSONArray jsonArrayCate = new JSONArray();
        JSONObject row;
        try {
            //data Vocabulary
            for (int i = 0; i < arrVoca.size(); i++) {
                row = new JSONObject();
                row.put("voca_id", "" + arrVoca.get(i).getVoca_id() + "");
                row.put("id_clien", "" + arrVoca.get(i).getId_clien() + "");
                row.put("cate_id", "" + arrVoca.get(i).getCate_id() + "");
                row.put("english", arrVoca.get(i).getEnglish());
                row.put("vietnamese", arrVoca.get(i).getVietnamese());
                jsonArrayVoca.put(row);
            }

            //data Categories
            for (int i = 0; i < arrCate.size(); i++) {
                row = new JSONObject();
                row.put("cate_id", "" + arrCate.get(i).getCate_id() + "");
                row.put("id_clien", "" + arrCate.get(i).getId_clien() + "");
                row.put("name", arrCate.get(i).getName());
                jsonArrayCate.put(row);
            }
            
            objectData.put("categories", jsonArrayCate);
            objectData.put("vocabularies", jsonArrayVoca);
            
            object.put("success", 1);
            object.put("error", 0);
            object.put("totalVoca", arrVoca.size());
            object.put("totalCate", arrCate.size());
            object.put("data", objectData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    //success insert voca, cate
    public static JSONObject successInsertVoca(ArrayList<ResultInsertVoca> arr) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject row;
        try {
            for (int i = 0; i < arr.size(); i++) {
                row = new JSONObject();
                row.put("table", arr.get(i).getTable());
                row.put("id_client", arr.get(i).getId_client());
                row.put("id_server", arr.get(i).getId_server());
                jsonArray.put(row);
            }
            object.put("success", 1);
            object.put("error", 0);
            object.put("total", arr.size());
            object.put("row", jsonArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
