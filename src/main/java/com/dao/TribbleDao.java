package com.dao;

import com.model.Tribble;
import com.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TribbleDao {


    private Tribble objectConstructor(ResultSet rs) throws SQLException {
        return new Tribble(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getInt(4));
    }

    //get all Tribbles
    public List<Tribble> getAllTribbles(){

        List<Tribble> l = new ArrayList<Tribble>();

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "SELECT * FROM tribbles";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()){
                l.add(objectConstructor(rs));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return l;
    }

    //get Tribble by id
    public Tribble getTribbleById(int id){

        Tribble tribble = new Tribble();

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "SELECT * FROM tribbles where tribble_id="+id+"";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()){
                tribble= objectConstructor(rs);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return tribble;
    }

    public int insertNew(Tribble t){

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "INSERT INTO tribbles(age,color,lab_id) VALUES("+t.getAge()+
                    ", \""+t.getColor()+"\", "+t.getLabId()+");";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateTribble(Tribble t){
        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "UPDATE tribbles SET age = "+t.getAge()+
                    ", color=\""+t.getColor()+"\", lab_id="+t.getLabId()+" WHERE tribble_id = "+t.getTribbleId()+";";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteTribble(int tribbleId){
        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "DELETE FROM tribbles WHERE tribble_id="+tribbleId+";";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
