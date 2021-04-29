package com.service;

import com.dao.TribbleDao;
import com.model.Tribble;

import java.util.List;

public class TribbleService {

    private final TribbleDao TRIBBLE_DAO = new TribbleDao();

    public List<Tribble> getAll(){
        return TRIBBLE_DAO.getAllTribbles();
    }

    public Tribble getById(int id){
        return TRIBBLE_DAO.getTribbleById(id);
    }

    public String insertNew(Tribble t){
        return giveResultString(TRIBBLE_DAO.insertNew(t));
    }

    public String update(Tribble t){
        return giveResultString(TRIBBLE_DAO.updateTribble(t));
    }

    public String delete(Tribble tribble){
        return giveResultString(TRIBBLE_DAO.deleteTribble(tribble.getTribbleId()));
    }

    public String giveResultString(int res){
        if(res==1){
            return "Success";
        }else if(res==0){
            return "Tribble not found";
        }else{
            return "an Error has occurred, please contact DB manager";
        }
    }
}
