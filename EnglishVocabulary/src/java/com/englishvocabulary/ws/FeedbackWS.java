/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.ws;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:FeedbackServies [feedback]<br>
 * USAGE:
 * <pre>
 *        FeedbackWS client = new FeedbackWS();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LuanDT
 */
public class FeedbackWS {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServicesEnglishVocabulary/rest";

    public FeedbackWS() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("feedback");
    }

    public <T> T add(Class<T> responseType) throws UniformInterfaceException {
        return webResource.path("add").post(responseType);
    }

    public <T> T deletelist(Class<T> responseType) throws UniformInterfaceException {
        return webResource.path("deletelist").post(responseType);
    }

    public <T> T delete(Class<T> responseType) throws UniformInterfaceException {
        return webResource.path("delete").post(responseType);
    }

    public <T> T getJson(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
