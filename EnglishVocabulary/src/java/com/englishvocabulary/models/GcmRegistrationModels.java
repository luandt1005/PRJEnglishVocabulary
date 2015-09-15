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
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LuanDT
 */
public class GcmRegistrationModels {
    //get all RegId
    public List<String> getAllRegId() {
        List<String> data = new ArrayList<String>();
        Client client = new Client();
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/gcm");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus() != 200){
            System.out.println("Connect fail: " + response.getStatus());
        } else {
            try {
                String result = response.getEntity(String.class);
                JSONObject object = new JSONObject(result);
                JSONArray array = object.getJSONArray("row");
                for (int i = 0; i < array.length(); i++) {
                    data.add(array.get(i).toString());
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            
        }
        return data;
    }
    
    //add
    public boolean addRegId(String reg_id) {
        boolean result = false;
        Client client = new Client();
        Form form = new Form();
        form.add("reg_id", reg_id);
        WebResource webResource = client.resource("http://localhost:8080/ServicesEnglishVocabulary/rest/gcm/add");
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
}
