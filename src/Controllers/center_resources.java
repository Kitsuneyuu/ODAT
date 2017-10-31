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
import Models.center_resource;
import java.sql.PreparedStatement;
import Synch.ODAT_Client;

/**
 *
 * @author Lucia Tortosa
 */
public class center_resources {

    public final String table_name = "center_resources";
    public final Object[] header = {"Nombre", "Descripcion", "", "", ""};

    public center_resource model;
    private Views.admin.center_resources.form view;

    public ArrayList<center_resource> list = null;

    public center_resources(int id) {
        this.model = selectModel(id);
    }

    public center_resources() {
        list = select();
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][5];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            Models.center_resource model = list.get(x);
            data[x][0] = model.getName();
            data[x][1] = model.getDescription();
            data[x][2] = button.getButton("s");
            data[x][3] = button.getButton("m");
            data[x][4] = button.getButton("d");
        }
        return data;
    }

    private ArrayList<center_resource> select() {
        ArrayList<center_resource> center_resources_list = new ArrayList<center_resource>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  *  FROM " + table_name + ";");

            while (rs.next()) {
                int x = 1;
                center_resource model = new Models.center_resource();
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
                center_resources_list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return center_resources_list;
    }

    private Models.center_resource selectModel(int id) {
        Models.center_resource model = new Models.center_resource();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " where id = " + id);

            while (rs.next()) {
                int x = 1;
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public void modify(center_resource model, Views.admin.center_resources.form view) {
        this.model = model;
        this.view = view;

        int id = get_id();

        String name = view.field_name.getText();
        String description = view.field_description.getText();
        Date creation_date = getTime();
        Date update_date = getTime();

        try {
            Connection con = SQLiteJDBC.OpenDatabase();

            PreparedStatement stmt = null;
            int x = 1;

            if (model == null) {

                model = new center_resource(id, name, description, creation_date, update_date);
                list.add(model);
                stmt = con.prepareStatement("insert into " + table_name + " values (?,?,?,?,?)");
                stmt.setInt(x++, -get_length());
            } else {
                stmt = con.prepareStatement("update " + table_name + " SET name=?, description=?, created_at=?, updated_at=? where id=" + model.getId());
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

    public void insert_sync(center_resource model, int id, int option, int old) {
        this.model = model;
        this.view = view;

        if (id != 0) {
            try {
                Connection con = SQLiteJDBC.OpenDatabase();

                PreparedStatement stmt = null;

                int x = 1;

                if (option == 0) {
                    stmt = con.prepareStatement("update " + table_name + " SET id = ? WHERE id=" + old);
                    stmt.setInt(x++, id);
                } else {
                    stmt = con.prepareStatement("update " + table_name + " SET name=?, description=?, created_at=?, updated_at=? where id=" + model.getId());

                    stmt.setString(x++, model.getName());
                    stmt.setString(x++, model.getDescription());
                    stmt.setObject(x++, model.getCreated_at());
                    stmt.setObject(x++, model.getUpdated_at());
                }

                stmt.executeUpdate();

                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public Models.center_resource getModel(int row) {
        Models.center_resource model = list.get(row);

        return model;
    }

    public Object[][] get_Info() {
        Object[][] info = new String[list.size() + 1][3];
        info[0][0] = null;
        info[0][1] = null;
        info[0][2] = null;

        for (int x = 1; x < list.size() + 1; x++) {
            Models.center_resource model = list.get(x - 1);
            info[x][0] = model.getId() + "";
            info[x][1] = model.getName();
            info[x][2] = model.getDescription();

        }
        return info;
    }

    public void delete(int id) {
        cascade(id);
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();

            String sql = "DELETE FROM " + table_name + " WHERE id=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cascade(int id) {
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();

            String sql = "DELETE * FROM center_resources_odat_diagnoses WHERE odat_diagnoses=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int get_id() {
        int length = 0;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            String query = null;

            query = "SELECT min(id) FROM " + table_name + ";";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                length = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ID: " + (length - 1));
        return length - 1;
    }

    public int getId(String sentence) {
        //ODAT_Client.dos.writeUTF(sentence);
        return -1;
    }
}
