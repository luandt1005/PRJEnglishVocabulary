/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.models;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LuanDT
 */
public class AdministratorModels {

    //get all Administrator

    public ArrayList<Administrator> getAllAdminUser() {
        ArrayList<Administrator> data = new ArrayList<Administrator>();
        Client client = new Client();
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String result = response.getEntity(String.class);
                JSONObject object = new JSONObject(result);
                JSONArray array = object.getJSONArray("row");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    long id = row.getLong("id");
                    String username = row.getString("username");
                    String fullname = row.getString("fullname");
                    int access = row.getInt("access");
                    int status = row.getInt("status");
                    data.add(new Administrator(id, username, fullname, access, status));
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

        }
        return data;
    }

    //login
    public ResultLogin login(Administrator a) {
        boolean check = false;
        long id = -1;
        String fullname = "";
        int access = -1;
        Client client = new Client();
        Form form = new Form();
        form.add("username", a.getUsername());
        form.add("password", a.getPassword());
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/login");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    check = true;
                    id = object.getLong("user_id");
                    fullname = object.getString("fullname");
                    access = object.getInt("access");
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return new ResultLogin(check, id, fullname, access);
    }
    
    //info admin
    public ResultLogin infoAdmin(String username) {
        boolean check = false;
        long id = -1;
        String fullname = "";
        int access = -1;
        int status = -1;
        Client client = new Client();
        Form form = new Form();
        form.add("username", username);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/info");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    check = true;
                    id = object.getLong("user_id");
                    fullname = object.getString("fullname");
                    access = object.getInt("access");
                    status = object.getInt("status");
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return new ResultLogin(id, fullname, access, status);
    }

    //delete
    public boolean delete(String id) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("id", id);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/delete");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    result = true;
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //change status
    public boolean updateStatus(long id, int status) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("id", id);
        form.add("status", status);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/updateStatus");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    result = true;
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //change Access
    public boolean changeAccess(long id, int access) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("id", id);
        form.add("access", access);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/updateAccess");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    result = true;
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //add
    public ResultLogin registerAdmin(Administrator a) {
        boolean result = false;
        String error_msg = "";
        Client client = new Client();
        Form form = new Form();
        form.add("username", a.getUsername());
        form.add("password", a.getPassword());
        form.add("fullname", a.getFullname());
        form.add("access", 0);
        form.add("status", 0);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/administrator/add");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if (response.getStatus() != 200) {
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if (object.getInt("success") == 1) {
                    result = true;
                } else {
                    error_msg = object.getString("error_msg");
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return new ResultLogin(result, error_msg);
    }
}
