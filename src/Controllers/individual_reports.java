/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.individual_report;
import Renders.Table.Tabla;
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
public class individual_reports {

    public final String table_name = "individual_reports";
    public final Object[] header = new Object[]{"Fecha creación", "", "", ""};

    private individual_report model;
    //private Views.individual_reports.form view;

    public ArrayList<individual_report> list = new ArrayList<individual_report>();

    private int medical_record;

    public individual_reports(int id) {
        this.medical_record = id;
        select(-1, false, false);
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][header.length];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            Models.individual_report model = list.get(x);
            data[x][0] = getDateFormat(model.getCreated_at());
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
            if (!new_record) {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " WHERE medical_record_id=" + medical_record + ";");
            } else {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " WHERE id=" + id + ";");
            }

            while (rs.next()) {
                int x = 1;
                individual_report model = new Models.individual_report();

                model.setId(rs.getInt(x++));
                model.setMedical_record_id(rs.getInt(x++));
                model.setOdat_diagnoses_id(x++);

                model.setTopic(rs.getString(x++));
                model.setTests(rs.getString(x++));
                model.setResults(rs.getString(x++));
                model.setOrientation(rs.getString(x++));

                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));

                model.setSignature(rs.getString(x++));
                model.setSigned_on(rs.getDate(x++));
                model.setShow_signature(rs.getBoolean(x++));
                model.setShow_signed_on(rs.getBoolean(x++));

                model.setConfigurable_view_id(rs.getInt(x++));

                model.setShow_medical_record_archive_date(rs.getBoolean(x++));
                model.setShow_birth_date(rs.getBoolean(x++));
                model.setShow_age(rs.getBoolean(x++));
                model.setShow_full_name(rs.getBoolean(x++));
                model.setShow_gender(rs.getBoolean(x++));
                model.setShow_birth_position(rs.getBoolean(x++));
                model.setShow_address(rs.getBoolean(x++));

                model.setShow_siblings_data(rs.getBoolean(x++));
                model.setShow_father_data(rs.getBoolean(x++));
                model.setShow_mother_data(rs.getBoolean(x++));
                model.setShow_phone_numbers(rs.getBoolean(x++));

                model.setShow_diagnosis_created_at(rs.getBoolean(x++));
                model.setShow_consultation_details(rs.getBoolean(x++));
                model.setShow_center_resources(rs.getBoolean(x++));
                model.setShow_detailed_diagnosis(rs.getBoolean(x++));
                model.setShow_coordination_services(rs.getBoolean(x++));
                model.setShow_main_diagnosis(rs.getBoolean(x++));

                model.setExtra_information(rs.getString(x++));

                model.setShow_handicap_data(rs.getBoolean(x++));
                model.setShow_level3(rs.getBoolean(x++));

                if (!update){
                    list.add(model);
                }else{
                    this.model = model;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modify(individual_report model, Views.individual_reports.form view) {
        int center = 1;
        Boolean new_record = false;
        Boolean update = false;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            PreparedStatement stmt = null;
            int x = 1;
            Date creation_date = getTime(null);
            Date update_date = getTime(null);
            int id = -get_length(true);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (model == null) {
                stmt = con.prepareStatement("insert into " + table_name + " (id, medical_record_id,odat_diagnosis_id,topic,tests,results,orientation,created_at,updated_at,signature,signed_on,show_signature,show_signed_on,show_medical_record_archive_date,show_birth_date,show_age,"
                        + "show_full_name,show_gender,show_birth_position,show_address,show_siblings_data,show_father_data,show_mother_data,show_phone_numbers,show_diagnosis_created_at,show_consultation_details,show_center_resources,show_detailed_diagnosis,show_coordination_services,show_main_diagnosis,"
                        + "show_handicap_data,show_level3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                new_record = true;
                stmt.setInt(x++, id);
            } else {
                id = model.getId();
                stmt = con.prepareStatement("update " + table_name + " SET medical_record_id=?,odat_diagnosis_id=?,topic=?,tests=?,results=?,orientation=?,created_at=?,updated_at=?,signature=?,signed_on=?,show_signature=?,show_signed_on=?,show_medical_record_archive_date=?,"
                        + "show_birth_date=?,show_age=?,show_full_name=?,show_gender=?,show_birth_position=?,show_address=?,show_siblings_data=?,show_father_data=?,show_mother_data=?,show_phone_numbers=?,show_diagnosis_created_at=?,show_consultation_details=?,show_center_resources=?,show_detailed_diagnosis=?,"
                        + "show_coordination_services=?,show_main_diagnosis=?,show_handicap_data=?,show_level3=? WHERE id=" + id);
                creation_date = model.getCreated_at();
                update = true;
            }

            stmt.setInt(x++, medical_record);
            stmt.setInt(x++, 0);
            stmt.setString(x++, view.field_topic.getText());
            stmt.setString(x++, view.field_tests.getText());
            stmt.setString(x++, view.field_results.getText());
            stmt.setString(x++, view.field_orientation.getText());

            stmt.setObject(x++, creation_date);
            stmt.setObject(x++, update_date);

            stmt.setString(x++, view.field_signature.getText());
            stmt.setObject(x++, getDate(view.field_signed_on.getDate()));
            stmt.setBoolean(x++, view.field_show_signature.isSelected());
            stmt.setBoolean(x++, view.field_show_signed_on.isSelected());

            stmt.setBoolean(x++, view.field_show_medical_record_archive_date.isSelected());
            stmt.setBoolean(x++, view.field_show_birth_date.isSelected());
            stmt.setBoolean(x++, view.field_show_age.isSelected());
            stmt.setBoolean(x++, view.field_show_gender.isSelected());
            stmt.setBoolean(x++, view.field_show_birth_position.isSelected());
            stmt.setBoolean(x++, view.field_show_address.isSelected());

            stmt.setBoolean(x++, view.field_show_siblings_data.isSelected());
            stmt.setBoolean(x++, view.field_show_father_data.isSelected());
            stmt.setBoolean(x++, view.field_show_mother_data.isSelected());
            stmt.setBoolean(x++, view.field_show_phone_numbers.isSelected());

            stmt.setBoolean(x++, view.field_show_diagnosis_created_at.isSelected());
            stmt.setBoolean(x++, view.field_show_consultation_details.isSelected());
            stmt.setBoolean(x++, view.field_show_center_resources.isSelected());
            stmt.setBoolean(x++, view.field_show_detailed_diagnosis.isSelected());
            stmt.setBoolean(x++, view.field_show_coordination_services.isSelected());
            stmt.setBoolean(x++, view.field_show_main_diagnosis.isSelected());
            stmt.setBoolean(x++, view.field_show_handicap_data.isSelected());
            stmt.setBoolean(x++, view.field_show_level3.isSelected());

            stmt.executeUpdate();
            stmt.close();

            select(id, new_record, update);
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

    private java.sql.Timestamp getTime(java.util.Date date) {
        if (date == null) {
            date = new Date();
        }

        java.sql.Timestamp param = new java.sql.Timestamp(date.getTime());

        return param;
    }

    public Models.individual_report getModel(int row) {
        Models.individual_report model = list.get(row);
        return model;
    }

    private String getDate(Date date) {
        String d = null;
        if (date != null) {
            java.sql.Date param = new java.sql.Date(date.getTime());
            d = param.toString();
        }
        return d;
    }

    private String getDateFormat(Date date) {
        Format f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String d = f.format(date);
        return d;
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

}
