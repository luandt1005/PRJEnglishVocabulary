/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.models;

import java.io.Serializable;

/**
 *
 * @author LuanDT
 */
public class GcmMessage implements Serializable{
    private long id;
    private String title;
    private String content;
    private String url_image;
    private String link;
    private String date_create;
    private boolean check;

    public GcmMessage() {
    }

    public GcmMessage(long id, String title, String content, String url_image, String link, String date_create) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url_image = url_image;
        this.link = link;
        this.date_create = date_create;
    }

    
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }
    
}
