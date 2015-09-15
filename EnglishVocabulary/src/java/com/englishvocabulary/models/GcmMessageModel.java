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
public class GcmMessageModel {
    //get all mesage
    public ArrayList<GcmMessage> getAllMesage() {
        ArrayList<GcmMessage> data = new ArrayList<GcmMessage>();
        Client client = new Client();
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/message");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus() != 200){
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String result = response.getEntity(String.class);
                JSONObject object = new JSONObject(result);
                JSONArray array = object.getJSONArray("row");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    long id = row.getLong("id");
                    String title = row.getString("title");
                    String content = row.getString("content");
                    String url_image = row.getString("url_image");
                    String link = row.getString("link");
                    String date_create = row.getString("date_create");
                    date_create = date_create.substring(0, 19);
                    data.add(new GcmMessage(id, title, content, url_image, link, date_create));
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            
        }
        return data;
    }
    
    //delete
    public boolean delete(String id) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("id", id);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/message/delete");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if(response.getStatus() != 200){
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if(object.getInt("success") == 1){
                    result = true;
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //add
    public boolean add(GcmMessage msg) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("title", msg.getTitle());
        form.add("content", msg.getContent());
        form.add("url_image", msg.getUrl_image());
        form.add("link", msg.getLink());
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/message/add ");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
        if(response.getStatus() != 200){
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String output = response.getEntity(String.class);
                JSONObject object = new JSONObject(output);
                if(object.getInt("success") == 1){
                    result = true;
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
