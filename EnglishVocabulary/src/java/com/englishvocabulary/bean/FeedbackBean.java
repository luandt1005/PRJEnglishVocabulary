/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Feedback;
import com.englishvocabulary.models.FeedbackModels;
import com.englishvocabulary.utils.MessageUtil;
import java.io.Serializable;
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
public class FeedbackBean extends MessageUtil implements Serializable {

    private boolean visible;
    private Feedback feedback;
    private FeedbackModels models;
    private ArrayList<Feedback> data, dataSelected;
    private int sizeDataSelected;
    private boolean disableBtnDelete = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ArrayList<Feedback> getDataSelected() {
        return dataSelected;
    }

    public void setDataSelected(ArrayList<Feedback> dataSelected) {
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
        if (data.isEmpty()) {
            visible = true;
        } else {
            visible = false;
        }
    }

    //select checkbox row table
    public void onRowCheckboxSelect(SelectEvent event) {
        feedback = (Feedback) event.getObject();
        if (feedback.isCheck()) {
            feedback.setCheck(false);
        } else {
            feedback.setCheck(true);
        }

        int chon = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isCheck()) {
                chon++;
            }
        }
        if (chon > 0) {
            disableBtnDelete = false;
        } else {
            disableBtnDelete = true;
        }
        sizeDataSelected = chon;
    }

    public void selectCheckbox() {
        int chon = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isCheck()) {
                chon++;
            }
        }
        if (chon > 0) {
            disableBtnDelete = false;
        } else {
            disableBtnDelete = true;
        }
        sizeDataSelected = chon;
    }

    //delete
    public void delete() {
        if (InforAdminBean.access() > 0) {
            String id = "";
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).isCheck()) {
                    id += data.get(i).getId() + ",";
                }
            }
            boolean deleted = models.delete(id);
            if (deleted) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isCheck()) {
                        data.remove(i);
                        i--;
                    }
                }
                if (data.isEmpty()) {
                    visible = true;
                } else {
                    visible = false;
                }

                addSuccessMsg("Xóa thành công");
                disableBtnDelete = true;
            } else {
                addErrorMsg("Lỗi!");
            }
        } else {
            addErrorMsg("Bạn không có quyền xóa.");
        }
    }
}
