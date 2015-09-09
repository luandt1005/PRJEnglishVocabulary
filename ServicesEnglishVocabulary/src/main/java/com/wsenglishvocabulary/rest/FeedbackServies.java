/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.Feedback;
import com.wsenglishvocabulary.models.FeedbackModel;
import com.wsenglishvocabulary.models.Result;
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
@Path("feedback")
public class FeedbackServies {

    @Context
    private UriInfo context;
    private FeedbackModel models;

    /**
     * Creates a new instance of FeedbackServies
     */
    public FeedbackServies() {
        models = new FeedbackModel();
    }

    /**
     * Retrieves representation of an instance of com.wsenglishvocabulary.rest.FeedbackServies
     * @return an instance of org.json.JSONObject
     */
    @GET
    @Produces("application/json")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        ArrayList<Feedback> arr = new ArrayList<Feedback>();
        try {
            arr = models.getAllFeedback();
        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        json = Result.successFeedback(arr);

        return json;
    }

    @POST
    @Path("/add")
    public JSONObject add(@FormParam("content") String content) {
        JSONObject json = new JSONObject();

        try {
            boolean inserted = models.addFeedback(content);
            if (inserted) {
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
    @Path("/delete")
    public JSONObject delete(@FormParam("id") long id) {
        JSONObject json = new JSONObject();

        try {
            boolean deleted = models.deleteFeedback(id);
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
    @Path("/deletelist")
    public JSONObject deletelist(@FormParam("id") String id) {
        JSONObject json = new JSONObject();

        String[] listId = id.split(",");
        ArrayList<Feedback> arr = new ArrayList<Feedback>();
        for (int i = 0; i < listId.length; i++) {
            arr.add(new Feedback(Long.parseLong(listId[i])));
        }
        try {
            boolean deleted = models.deleteList(arr);
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
