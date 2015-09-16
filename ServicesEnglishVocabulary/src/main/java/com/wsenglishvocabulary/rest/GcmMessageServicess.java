/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.GcmMessage;
import com.wsenglishvocabulary.models.GcmMessageModel;
import com.wsenglishvocabulary.models.Result;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author LuanDT
 */
@Path("message")
public class GcmMessageServicess {

    @Context
    private UriInfo context;
    private GcmMessageModel model;

    /**
     * Creates a new instance of GcmMessageServicess
     */
    public GcmMessageServicess() {
        model = new GcmMessageModel();
    }

    /**
     * Retrieves representation of an instance of
     * com.wsenglishvocabulary.rest.GcmMessageServicess
     *
     * @return an instance of org.codehaus.jettison.json.JSONObject
     */
    @GET
    @Produces("application/json")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        ArrayList<GcmMessage> arr = new ArrayList<GcmMessage>();
        try {
            arr = model.getAllMessage();
        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        json = Result.successGcmMsg(arr);

        return json;
    }

    @POST
    @Path("/add")
    public JSONObject add(@FormParam("title") String title, @FormParam("content") String content, @FormParam("url_image") String url_image, @FormParam("link") String link) {
        JSONObject json = new JSONObject();
        GcmMessage msg = new GcmMessage();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setUrl_image(url_image);
        msg.setLink(link);

        try {
            long id = model.addMessage(msg);
            if (id != -1) {
                json = Result.successInsertMsg(id);
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
    @Path("/delete")
    public JSONObject deletelist(@FormParam("id") String id) {
        JSONObject json = new JSONObject();

        String[] listId = id.split(",");
        ArrayList<GcmMessage> arr = new ArrayList<GcmMessage>();
        for (int i = 0; i < listId.length; i++) {
            if (listId[i] != "") {
                arr.add(new GcmMessage(Long.parseLong(listId[i])));
            }
        }
        try {
            boolean deleted = model.deleteList(arr);
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

}
