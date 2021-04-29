package com.service;

import com.dao.LabDao;
import com.model.Lab;

import java.util.List;

public class LabService {

    private final LabDao LAB_DAO = new LabDao();

    public List<Lab> getAll(){
        return LAB_DAO.getAllLabs();
    }

    public Lab getById(int id){
        return LAB_DAO.getLabById(id);
    }

    public String insertNew(Lab l){
        return giveResultString(LAB_DAO.insertNew(l));
    }

    public String update(Lab l){
        return giveResultString(LAB_DAO.updateLab(l));
    }

    public String delete(Lab lab){
        return giveResultString(LAB_DAO.deleteLab(lab.getLabId()));
    }

    public String giveResultString(int res){
        if(res==1){
            return "Success";
        }else if(res==0){
            return "Lab not found";
        }else{
            return "an Error has occurred, please contact DB manager";
        }
    }
}

