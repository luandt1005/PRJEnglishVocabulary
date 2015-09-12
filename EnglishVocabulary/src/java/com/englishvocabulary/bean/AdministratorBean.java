/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.bean;

import com.englishvocabulary.models.Administrator;
import com.englishvocabulary.models.AdministratorModels;
import com.englishvocabulary.utils.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author LuanDT
 */
@ManagedBean
@ViewScoped
public class AdministratorBean extends MessageUtil implements Serializable {
    
    private Administrator admin, selectedAdmin;
    private AdministratorModels models;
    private ArrayList<Administrator> data, dataSelected;
    private boolean statusBtnDelete;

    /**
     * Creates a new instance of AdministratorBean
     */
    public AdministratorBean() {
        admin = new Administrator();
        models = new AdministratorModels();
        getAllData();
    }

    //get all data
    private void getAllData() {
        data = models.getAllAdminUser();
    }

    //delete
    public void delete() {
        boolean deleted = models.delete("" + selectedAdmin.getId());
        if (deleted) {
            addSuccessMsg("Xóa thành công");
            data.remove(selectedAdmin);
        } else {
            addErrorMsg("Lỗi!");
        }
    }
    
    //updateStatus
    public void updateStatus() {
        int status;
        if(selectedAdmin.getStatus() == 1){
            status = 0;
        } else {
            status = 1;
        }
        
        boolean check = models.updateStatus(selectedAdmin.getId(), status);
        if (check) {
            addSuccessMsg("Đổi trạng thái thành công");
            selectedAdmin.setStatus(status);
        } else {
            addErrorMsg("Lỗi!");
        }
    }
    
    //changeAccess
    public void changeAccess() {
        int access;
        if(selectedAdmin.getAccess() == 1){
            access = 0;
        } else {
            access = 1;
        }
        boolean check = models.changeAccess(selectedAdmin.getId(), access);
        if (check) {
            addSuccessMsg("Thay đổi quyền thành công");
            selectedAdmin.setAccess(access);
        } else {
            addErrorMsg("Lỗi!");
        }
    }
    
    //visible btn delete, edit
    public boolean isStatusBtnDelete(Administrator a) {
        if (InforAdminBean.access() > a.getAccess()) {
            statusBtnDelete = false;
        } else {
            statusBtnDelete = true;
        }
        return statusBtnDelete;
    }
    
    //trang thai thay doi
    public String trangThaiThayDoi(int status){
        String name = "";
        if(status == 1) {
            name = "Tạm dừng";
        } else {
            name = "Hoạt động";
        }
        return name;
    }
    
    //quyen thay doi
    public String quyenThayDoi(int access){
        String name = "";
        if(access == 1) {
            name = "Khách";
        } else {
            name = "Quản trị";
        }
        return name;
    }
    
    public Administrator getSelectedAdmin() {
        return selectedAdmin;
    }
    
    public void setSelectedAdmin(Administrator selectedAdmin) {
        this.selectedAdmin = selectedAdmin;
    }
    
    public ArrayList<Administrator> getDataSelected() {
        return dataSelected;
    }
    
    public void setDataSelected(ArrayList<Administrator> dataSelected) {
        this.dataSelected = dataSelected;
    }
    
    public Administrator getAdmin() {
        return admin;
    }
    
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
    public ArrayList<Administrator> getData() {
        return data;
    }
    
    public void setData(ArrayList<Administrator> data) {
        this.data = data;
    }
    
}
