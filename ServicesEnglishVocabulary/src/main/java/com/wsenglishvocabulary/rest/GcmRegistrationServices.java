/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.rest;

import com.wsenglishvocabulary.models.GcmRegistrationModel;
import com.wsenglishvocabulary.models.Result;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
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
@Path("gcm")
public class GcmRegistrationServices {

    @Context
    private UriInfo context;
    private GcmRegistrationModel model;

    /**
     * Creates a new instance of GcmRegistrationServices
     */
    public GcmRegistrationServices() {
        model = new GcmRegistrationModel();
    }

    /**
     * Retrieves representation of an instance of com.wsenglishvocabulary.rest.GcmRegistrationServices
     * @return an instance of org.codehaus.jettison.json.JSONObject
     */
    @GET
    @Produces("application/json")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        List<String> arr = new ArrayList<String>();
        try {
            arr = model.getAllRegId();
        } catch (Exception ex) {
            json = Result.error();
            ex.printStackTrace();
        }

        json = Result.successRegId(arr);

        return json;
    }

    @POST
    @Path("/add")
    public JSONObject addRegId(@FormParam("reg_id") String reg_id) {
        JSONObject json = new JSONObject();

        try {
            boolean inserted = model.addRegId(reg_id);
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
}
