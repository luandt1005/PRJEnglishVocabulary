/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.GcmMessage;
import com.englishvocabulary.models.GcmMessageModel;
import com.englishvocabulary.models.GcmRegistrationModels;
import com.englishvocabulary.models.ResultLogin;
import com.englishvocabulary.utils.MessageUtil;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private GcmMessage message, gcmMessage;
    private GcmMessageModel models;
    private GcmRegistrationModels grm;
    private ArrayList<GcmMessage> data, dataSelected;
    private int sizeDataSelected;
    private boolean disableBtnDelete = true;
    private boolean disableBtnNewMsg = true;
    private boolean visibleNewMsg;
    private String focus = "title";
    private List<String> listRegId;

    public boolean isDisableBtnNewMsg() {
        return disableBtnNewMsg;
    }

    public void setDisableBtnNewMsg(boolean disableBtnNewMsg) {
        this.disableBtnNewMsg = disableBtnNewMsg;
    }

    public GcmMessage getGcmMessage() {
        return gcmMessage;
    }

    public void setGcmMessage(GcmMessage gcmMessage) {
        this.gcmMessage = gcmMessage;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public boolean isVisibleNewMsg() {
        return visibleNewMsg;
    }

    public void setVisibleNewMsg(boolean visibleNewMsg) {
        this.visibleNewMsg = visibleNewMsg;
    }

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
        gcmMessage = new GcmMessage();
        models = new GcmMessageModel();
        grm = new GcmRegistrationModels();
        getAllData();
        getAllRegId();
    }

    private void getAllData() {
        data = models.getAllMesage();
        if (data.isEmpty()) {
            visible = true;
        } else {
            visible = false;
        }
    }

    //all reg_id
    private void getAllRegId() {
        listRegId = grm.getAllRegId();
        if(!listRegId.isEmpty()){
            disableBtnNewMsg = false;
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

    //lay tg he thong
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return "" + dateFormat.format(cal.getTime());
    }

    //new message
    public void newMessage() {
        if (vilidate()) {
            gcmMessage.setSender(InforAdminBean.fullname());
            ResultLogin result = models.add(gcmMessage);
            if (result.isCheck()) {
                addSuccessMsg("Thêm thành công");
                data.add(new GcmMessage(result.getUser_id(), gcmMessage.getTitle(), gcmMessage.getContent(), gcmMessage.getUrl_image(), gcmMessage.getLink(), getDate(), InforAdminBean.fullname()));
                visibleNewMsg = false;
                visible = false;

                //sen gcm mesage
                try {
                    sendMessage(gcmMessage.getTitle(), gcmMessage.getContent(), gcmMessage.getUrl_image(), gcmMessage.getLink(), listRegId);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                clearInputText();
            } else {
                addErrorMsg("Lỗi!");
                visibleNewMsg = true;
            }
        }
    }

    private boolean vilidate() {
        if (gcmMessage.getTitle().equals("")) {
            addErrorMsg("Bạn chưa nhập tiêu đề");
            focus = "title";
            visibleNewMsg = true;
            return false;
        }
        if (gcmMessage.getContent().equals("")) {
            addErrorMsg("Bạn chưa nhập nội dung");
            focus = "content";
            visibleNewMsg = true;
            return false;
        }
        if (gcmMessage.getUrl_image().equals("")) {
            addErrorMsg("Bạn chưa nhập link ảnh");
            focus = "image";
            visibleNewMsg = true;
            return false;
        }
        if (gcmMessage.getLink().equals("")) {
            addErrorMsg("Bạn chưa nhập link web");
            focus = "web";
            visibleNewMsg = true;
            return false;
        } else {
            return true;
        }
    }

    private void clearInputText() {
        gcmMessage.setTitle("");
        gcmMessage.setContent("");
        gcmMessage.setUrl_image("");
        gcmMessage.setLink("");
    }

    private static void sendMessage(String title, String content, String url_image, String link, List<String> listRegId) throws IOException {
        String API_KEY = "AIzaSyBF5wUUBph-rpjfxshQOwpBai53D9xFAoo";
        String collpaseKey = "gcm_message";

        Sender sender = new Sender(API_KEY);
        Message.Builder builder = new Message.Builder();

        builder.collapseKey(collpaseKey);
        builder.timeToLive(120);//tuoi tho tin nhan(s). khong set mac dinh la 28 ngay. set = 0 khong gui duoc thi se bi loai bo
        builder.delayWhileIdle(true);//delay gui lai message khi device hoat dong
        builder.addData("title", title);
        builder.addData("content", content);
        builder.addData("url_image", url_image);
        builder.addData("link", link);

        Message message = builder.build();
        MulticastResult result = null;

        //neu nhieu hon 1000 device => split ra nhieu listRegId
        if (listRegId.size() > 1000) {
            List<List> list = new ArrayList<List>();
            for (int i = 1; i <= listRegId.size() / 1000 + 1; i++) {
                List<String> l = new ArrayList<String>();
                if (i == listRegId.size() / 1000 + 1) {
                    l = listRegId.subList(i * 1000 - 1000, listRegId.size());
                } else {
                    l = listRegId.subList(i * 1000 - 1000, i * 1000);
                }
                list.add(l);
            }

            for (int i = 0; i < list.size(); i++) {
                result = sender.send(message, list.get(i), 1);
                System.out.println("result = " + result);
            }
        } else {
            result = sender.send(message, listRegId, 1);
            System.out.println("result = " + result);
        }

        if (result.getResults() != null) {
            int canonicalRegId = result.getCanonicalIds();
            System.out.println("canonicalRegId = " + canonicalRegId);

            if (canonicalRegId != 0) {
            }
        } else {
            int error = result.getFailure();
            System.out.println("Broadcast failure: " + error);
        }

    }

    //=================Test============================//
    
    public static void main(String[] args) {
        List<String> listRegId;
        GcmRegistrationModels grm = new GcmRegistrationModels();
        listRegId = grm.getAllRegId();
        try {
            sendMessage("Đinh Thế Luân", "Đinh Thế Luân", "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfa1/v/t1.0-1/p160x160/17107_704101356385807_347390781586995188_n.jpg?oh=0ac62d3bd661496eca84eac161e60aa5&oe=565F8C1F&__gda__=1453155506_b3c3a1577a7a5f5ec2122251e3056ff0", "https://www.facebook.com/luandt1005", listRegId);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
