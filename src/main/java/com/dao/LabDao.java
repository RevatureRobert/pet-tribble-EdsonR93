package com.dao;

import com.model.Lab;
import com.model.Tribble;
import com.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LabDao {

    private Lab objectConstructor(ResultSet rs) throws SQLException {
        return new Lab(rs.getInt(1), rs.getString(2));
    }

    public List<Lab> getAllLabs(){

        List<Lab> l = new ArrayList<Lab>();

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "SELECT * FROM labs";
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

    //get lab by id
    public Lab getLabById(int id){

        Lab lab = new Lab();

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "SELECT * FROM labs where lab_id="+id+"";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()){
                lab= objectConstructor(rs);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return lab;
    }

    public int insertNew(Lab l){

        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "INSERT INTO labs(name) VALUES("+l.getName()+");";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateLab(Lab l){
        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "UPDATE labs SET name = "+l.getName()+";";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteLab(int labId){
        try(Connection c = ConnectionUtil.INSTANCE.getConnection()){
            String sql = "DELETE FROM labs WHERE lab_id="+labId+";";
            Statement s = c.createStatement();
            return s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
