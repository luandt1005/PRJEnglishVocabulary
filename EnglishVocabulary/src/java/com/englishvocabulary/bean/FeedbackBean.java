/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Feedback;
import com.englishvocabulary.models.FeedbackModels;
import com.englishvocabulary.utils.MessageUtil;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author LuanDT
 */
@ManagedBean
@ViewScoped
public class FeedbackBean extends MessageUtil{

    private Feedback feedback;
    private FeedbackModels models;
    private ArrayList<Feedback> data, dataSelected;
    private int sizeDataSelected;
    private boolean disableBtnDelete = true;

    public ArrayList<Feedback> getDataSelected() {
        return dataSelected;
    }

    public void setDataSelected(ArrayList<Feedback> dataSelected) {
        this.sizeDataSelected = dataSelected.size();
        if(dataSelected.size() > 0){
            disableBtnDelete = false;
        } else {
            disableBtnDelete = true;
        }
        this.dataSelected = dataSelected;
    }

    public int getSizeDataSelected() {
        return sizeDataSelected;
    }

    public void setSizeDataSelected(int sizeDataSelected) {
        this.sizeDataSelected = sizeDataSelected;
    }

    public boolean isDisableBtnDelete() {
        return disableBtnDelete;
    }

    public void setDisableBtnDelete(boolean disableBtnDelete) {
        this.disableBtnDelete = disableBtnDelete;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public ArrayList<Feedback> getData() {
        return data;
    }

    public void setData(ArrayList<Feedback> data) {
        this.data = data;
    }

    public FeedbackBean() {
        feedback = new Feedback();
        models = new FeedbackModels();
        getAllData();
    }

    private void getAllData() {
        data = models.getAllFeedback();
    }
    
    //select checkbox row table
    public void onRowCheckboxSelect(SelectEvent event) {
        sizeDataSelected = dataSelected.size();
        feedback = (Feedback) event.getObject();
        System.out.println("feedback.getId(): " + feedback.getId());
        System.out.println("dataSelected.size():" + dataSelected.size());
    }
    
}
