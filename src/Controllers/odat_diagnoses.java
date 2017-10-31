/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.medical_record;
import Models.odat_diagnose;
import Renders.Table.Tabla;
import Views.medical_records.subtable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia Tortosa
 */
public class odat_diagnoses {

    public final String table_name = "odat_diagnoses";
    public final Object[] header = new Object[]{"Fecha", "", "", ""};

    private odat_diagnose model;
    private Views.odat_diagnoses.form view;

    public ArrayList<odat_diagnose> list = new ArrayList<odat_diagnose>();

    private int medical_record;


    public odat_diagnoses(int id) {
        this.medical_record = id;
        select(-1, false, false);
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][header.length];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            odat_diagnose model = list.get(x);
            data[x][0] = model.getCreated_at();
            data[x][1] = button.getButton("s");
            data[x][2] = button.getButton("m");
            data[x][3] = button.getButton("d");
        }
        return data;
    }

    public void select(int id, boolean new_record, boolean update) {
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            
            System.out.println("ID select: "+id);
            
            if (!new_record) {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " WHERE medical_record_id=" + medical_record + ";");
            } else {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " WHERE id=" + id + ";");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                int x = 1;
                Models.odat_diagnose model = new Models.odat_diagnose();

                model.setId(rs.getInt(x++));
                model.setMedical_record_id(rs.getInt(x++));
                model.setOrigin_source_id(rs.getInt(x++));
                model.setOrigin_cause_id(rs.getInt(x++));
                model.setConsultation_cause_id(rs.getInt(x++));
                model.setMain_diagnosis_item_id(rs.getInt(x++));
                model.setDescription(rs.getString(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
                
                System.out.println("Datos: " + update + ", " + model.getId());
                
                if (!update) {
                    list.add(model);
                } else {
                    this.model = model;
                }
                System.out.println("LISTA: "+list.size());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modify(odat_diagnose model, Views.odat_diagnoses.form view) {
        int center = 1;
        Boolean new_record = false;
        Boolean update = false;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            PreparedStatement stmt = null;
            int x = 1;
            Date creation_date = getTime();
            Date update_date = getTime();
            int id = get_id();
            if (model == null) {
                stmt = con.prepareStatement("insert into " + table_name + " (id, medical_record_id, origin_source_id, origin_cause_id,"
                        + " consultation_cause_id, main_diagnosis_item_id, description, created_at, updated_at) values (?,?,?,?,?,?,?,?,?)");
                new_record = true;
                stmt.setInt(x++, id);
            } else {
                id = model.getId();
                stmt = con.prepareStatement("update " + table_name + " SET medical_record_id=?, origin_source_id=?, origin_cause_id=?,"
                        + " consultation_cause_id=?, main_diagnosis_item_id=?, description=?, created_at=?, updated_at=? WHERE id=" + id);
                creation_date = model.getCreated_at();
                update = true;
            }

            stmt.setInt(x++, medical_record);
            stmt.setObject(x++, view.ids_origin_sources[view.field_origin_source_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_origin_causes[view.field_origin_cause_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_consultation_causes[view.field_consultation_causes_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.mainId);
            stmt.setString(x++, view.field_description.getText());
            stmt.setObject(x++, creation_date);
            stmt.setObject(x++, update_date);

            stmt.executeUpdate();
            stmt.close();
            select(id, new_record, update);
            System.out.println("LISTA: "+list.size());
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int get_length(Boolean all_data) {
        int length = 0;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            String query = null;

            if (all_data) {
                query = "SELECT count(*) FROM " + table_name + ";";
            } else {
                query = "SELECT count(*) FROM " + table_name + " WHERE medical_record_id=" + medical_record + ";";
            }
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

    private String getDate(Date date) {
        Format f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String d = f.format(date);
        return d;
    }

    public Models.odat_diagnose getModel(int row) {
        Models.odat_diagnose model = list.get(row);

        return model;
    }

    public void delete(int id) {
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
        System.out.println("ID: "+(length-1));
        return length-1;
    }

}
