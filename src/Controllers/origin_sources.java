/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Renders.Table.Tabla;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.origin_source;
import java.sql.PreparedStatement;

/**
 *
 * @author Lucia Tortosa
 */
public class origin_sources {

    public final String table_name = "origin_sources";
    public final Object[] header = {"Nombre", "Descripcion", "", "", ""};

    private origin_source model;
    private Views.admin.origin_sources.form view;

    public ArrayList<origin_source> list = null;

    public origin_sources() {
        list = select();
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][5];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            Models.origin_source model = list.get(x);
            data[x][0] = model.getName();
            data[x][1] = model.getDescription();
            data[x][2] = button.getButton("s");
            data[x][3] = button.getButton("m");
            data[x][4] = button.getButton("d");
        }
        return data;
    }

    private ArrayList<origin_source> select() {
        ArrayList<origin_source> origin_source_list = new ArrayList<origin_source>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  *  FROM " + table_name + ";");
       
            while (rs.next()) {
                int x = 1;
                origin_source model = new Models.origin_source();
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
                origin_source_list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return origin_source_list;
    }

    public void modify(origin_source model, Views.admin.origin_sources.form view) {
        this.model = model;
        this.view = view;
        
        int id = -get_length();
        
        String name = view.field_name.getText();
        String description = view.field_description.getText();
        Date creation_date = getTime();
        Date update_date = getTime();
  
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            
            PreparedStatement stmt = null;
            int x = 1;
            
            if (model == null){
                model = new origin_source(id, name, description, creation_date, update_date);
                list.add(model);
                stmt = con.prepareStatement("insert into " + table_name + " values (?,?,?,?,?)");
                stmt.setInt(x++, -get_length());
            } else {
                stmt = con.prepareStatement("update " + table_name + " SET name=?, description=?, created_at=?, updated_at=? where id="+model.getId());
                creation_date = model.getCreated_at();
                
                model.setName(name);
                model.setDescription(description);
                model.setUpdated_at(update_date);
            }
            
            stmt.setString(x++, name);
            stmt.setString(x++, description);
            stmt.setObject(x++, creation_date);
            stmt.setObject(x++, update_date);
            
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int get_length() {
        int length = 0;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            String query = null;

            query = "SELECT count(*) FROM " + table_name + ";";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                length = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return length;
    }

    private java.sql.Timestamp getTime() {
        java.util.Date date = new Date();
        java.sql.Timestamp param = new java.sql.Timestamp(date.getTime());

        return param;
    }
    
    public Models.origin_source getModel(int row) {
        Models.origin_source model = list.get(row);

        return model;
    }
    
    public Object[][] get_Info() {
        Object[][] info = new String[list.size() + 1][3];
        info[0][0] = null;
        info[0][1] = null;
        info[0][2] = null;

        for (int x = 1; x < list.size() + 1; x++) {
            Models.origin_source model = list.get(x - 1);
            info[x][0] = model.getId()+"";
            info[x][1] = model.getName();
            info[x][2] = model.getDescription();

        }
        return info;
    }
    
    public void delete (int id) {
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();

            String sql = "DELETE FROM "+table_name+" WHERE id="+id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
