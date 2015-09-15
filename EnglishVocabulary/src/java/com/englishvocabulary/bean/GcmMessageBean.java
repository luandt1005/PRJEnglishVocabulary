/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Feedback;
import com.englishvocabulary.models.FeedbackModels;
import com.englishvocabulary.models.GcmMessage;
import com.englishvocabulary.models.GcmMessageModel;
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
public class GcmMessageBean extends MessageUtil implements Serializable {

    private boolean visible;
    private GcmMessage message;
    private GcmMessageModel models;
    private ArrayList<GcmMessage> data, dataSelected;
    private int sizeDataSelected;
    private boolean disableBtnDelete = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ArrayList<GcmMessage> getDataSelected() {
        return dataSelected;
    }

    public void setDataSelected(ArrayList<GcmMessage> dataSelected) {
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

    public GcmMessage getMessage() {
        return message;
    }

    public void setMessage(GcmMessage message) {
        this.message = message;
    }

    public ArrayList<GcmMessage> getData() {
        return data;
    }

    public void setData(ArrayList<GcmMessage> data) {
        this.data = data;
    }

    public GcmMessageBean() {
        message = new GcmMessage();
        models = new GcmMessageModel();
        getAllData();
    }

    private void getAllData() {
        data = models.getAllMesage();
        if (data.isEmpty()) {
            visible = true;
        } else {
            visible = false;
        }
    }

    //select checkbox row table
    public void onRowCheckboxSelect(SelectEvent event) {
        message = (GcmMessage) event.getObject();
        if (message.isCheck()) {
            message.setCheck(false);
        } else {
            message.setCheck(true);
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
