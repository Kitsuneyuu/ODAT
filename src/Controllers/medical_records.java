/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.medical_record;
import Renders.Table.Tabla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.lang.Integer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia Tortosa
 */
public class medical_records {

    public final String table_name = "medical_records";
    public final Object[] header = new Object[]{"", "Nombre", "Fecha creación", "Diagnósticos", "Informes", "", "", ""};

    private medical_record model;
    private Views.medical_records.form view;

    public ArrayList<medical_record> list = new ArrayList<medical_record>();

    public medical_records() {
        select(-1, false);
    }

    public medical_records(medical_record model, Views.medical_records.form view, int[] id) {
        this.model = model;
        this.view = view;
        modify(model, view);
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][8];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            Models.medical_record model = list.get(x);
            data[x][0] = button.getButton("+");
            data[x][1] = model.getName() + " " + model.getSurname();
            data[x][2] = model.getArchive_date();
            data[x][3] = (new Controllers.odat_diagnoses(model.getId())).get_length(false);
            data[x][4] = (new Controllers.individual_reports(model.getId())).get_length(false);

            data[x][5] = button.getButton("s");
            data[x][6] = button.getButton("m");
            data[x][7] = button.getButton("d");
        }
        return data;
    }

    private void select(int id, boolean new_record) {
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            if (id == -1) {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + ";");
            } else {
                rs = stmt.executeQuery("SELECT  *  FROM " + table_name + " WHERE id=" + id + ";");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                int x = 1;
                medical_record model = new Models.medical_record();

                model.setId(rs.getInt(x++));
                model.setCenter_id(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setSurname(rs.getString(x++));
                model.setBirth_date(rs.getDate(x++));
                model.setArchive_date(java.sql.Date.valueOf(rs.getString(x++)));
                model.setCity(rs.getString(x++));
                model.setAddress(rs.getString(x++));
                model.setBirth_position(rs.getInt(x++));
                model.setGender(rs.getString(x++));
                model.setFather_name(rs.getString(x++));
                model.setFather_surname(rs.getString(x++));
                model.setFather_birth_date(rs.getDate(x++));
                model.setFather_job_id(rs.getInt(x++));
                model.setFather_civil_state_id((Integer) rs.getObject(x++));
                model.setFather_job_status_id(rs.getInt(x++));
                model.setFather_formation_level_id(rs.getInt(x++));
                model.setFather_email(rs.getString(x++));
                model.setFather_extra_information(rs.getString(x++));
                model.setMother_name(rs.getString(x++));
                model.setMother_surname(rs.getString(x++));
                model.setMother_birth_date(rs.getDate(x++));
                model.setMother_job_id(rs.getInt(x++));
                model.setMother_civil_state_id(rs.getInt(x++));
                model.setMother_job_status_id(rs.getInt(x++));
                model.setMother_formation_level_id(rs.getInt(x++));
                model.setMother_email(rs.getString(x++));
                model.setMother_extra_information(rs.getString(x++));
                model.setHome_phone(rs.getString(x++));
                model.setPortable_phone(rs.getString(x++));
                model.setWork_phone(rs.getString(x++));
                model.setTotal_siblings_amount(rs.getInt(x++));
                model.setPostal(rs.getString(x++));
                model.setSanitary_services(rs.getBoolean(x++));
                model.setSocial_services(rs.getBoolean(x++));
                model.setEducative_services(rs.getBoolean(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
                model.setMultiple_birth(rs.getBoolean(x++));
                model.setProvince_id(rs.getInt(x++));
                model.setPosition_in_siblings(rs.getInt(x++));
                model.setHandicap(rs.getBoolean(x++));
                model.setDependency_degree(rs.getInt(x++));
                model.setSchool_type_id(rs.getInt(x++));

                if (id == -1) {
                    list.add(model);
                } else if (new_record) {
                    list.add(model);
                } else {
                    this.model = model;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modify(medical_record model, Views.medical_records.form view) {
        int center = 1;
        Boolean new_record = false;
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            PreparedStatement stmt = null;
            int x = 1;
            Date creation_date = getTime();
            Date update_date = getTime();
            int id = -get_length();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (model == null) {
                stmt = con.prepareStatement("insert into " + table_name + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                new_record = true;

            } else {
                id = model.getId();
                stmt = con.prepareStatement("update " + table_name + " SET center_id=?,name=?,surname=?,birth_date=?,archive_date=?,city=?,address=?,birth_position=?,gender=?,"
                        + "father_name=?,father_surname=?,father_birth_date=?,father_job_id=?,father_civil_state_id=?,father_job_status_id=?,father_formation_level_id=?,father_email=?,"
                        + "father_extra_information=?,mother_name=?,mother_surname=?,mother_birth_date=?,mother_job_id=?,mother_civil_state_id=?,mother_job_status_id=?,mother_formation_level_id=?,"
                        + "mother_email=?,mother_extra_information=?,home_phone=?,portable_phone=?,work_phone=?,total_siblings_amount=?,postal=?,sanitary_services=?,social_services=?,educative_services=?,"
                        + "created_at=?,updated_at=?,multiple_birth=?,province_id=?,position_in_siblings=?,handicap=?,dependency_degree=?,school_type_id=? WHERE id=" + model.getId());
                creation_date = model.getCreated_at();
            }

            stmt.setInt(x++, id);
            stmt.setInt(x++, center);
            stmt.setString(x++, view.field_name.getText());
            stmt.setString(x++, view.field_surname.getText());
            stmt.setObject(x++, getDate(view.field_birth_date.getDate()));
            stmt.setObject(x++, getDate(view.field_archive_date.getDate()));
            stmt.setString(x++, view.field_city.getText());
            stmt.setString(x++, view.field_address.getText());
            stmt.setInt(x++, (int) view.field_birth_position.getValue());
            stmt.setString(x++, view.field_gender.getSelectedItem().toString());
            stmt.setString(x++, view.field_father_name.getText());
            stmt.setString(x++, view.field_father_surname.getText());
            stmt.setObject(x++, getDate(view.field_father_birth_date.getDate()));
            stmt.setObject(x++, view.ids_jobs[view.field_father_job_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_civil_states[view.field_father_civil_state_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_job_statuses[view.field_father_job_status_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_formation_levels[view.field_father_formation_level_id.getSelectedIndex()][0]);
            stmt.setString(x++, view.field_father_email.getText());
            stmt.setString(x++, view.field_father_extra_information.getText());
            stmt.setString(x++, view.field_father_name.getText());
            stmt.setString(x++, view.field_mother_surname.getText());
            stmt.setObject(x++, getDate(view.field_mother_birth_date.getDate()));
            stmt.setObject(x++, view.ids_jobs[view.field_mother_job_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_civil_states[view.field_mother_civil_state_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_job_statuses[view.field_mother_job_status_id.getSelectedIndex()][0]);
            stmt.setObject(x++, view.ids_formation_levels[view.field_mother_formation_level_id.getSelectedIndex()][0]);
            stmt.setString(x++, view.field_mother_email.getText());
            stmt.setString(x++, view.field_mother_extra_information.getText());
            stmt.setString(x++, view.field_home_phone.getText());
            stmt.setString(x++, view.field_portable_phone.getText());
            stmt.setString(x++, view.field_work_phone.getText());
            stmt.setInt(x++, (int) view.field_total_siblings_amount.getValue());
            stmt.setString(x++, view.field_postal.getText());
            stmt.setInt(x++, view.field_sanitary_services.getSelectedIndex());
            stmt.setInt(x++, view.field_social_services.getSelectedIndex());
            stmt.setInt(x++, view.field_educative_services.getSelectedIndex());
            stmt.setObject(x++, creation_date);
            stmt.setObject(x++, update_date);
            stmt.setInt(x++, view.field_multiple_birth.isSelected() ? 1 : 0);
            stmt.setObject(x++, view.ids_provinces[view.field_province.getSelectedIndex()][0]);
            stmt.setInt(x++, (int) view.field_position_in_siblings.getValue());
            stmt.setInt(x++, view.field_handicap.isSelected() ? 1 : 0);
            stmt.setInt(x++, (int) view.field_dependency_degree.getValue());
            stmt.setObject(x++, view.ids_school_types[view.field_school_type.getSelectedIndex()][0]);

            stmt.executeUpdate();
            stmt.close();

            select(id, new_record);
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

    public Models.medical_record getModel(int row) {
        Models.medical_record model = list.get(row);

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

    public void delete(int id) {
        cascade("delete * from center_resources_odat_diagnoses where odat_diagnosis_id in (SELECT id from odat_diagnoses where odat_diagnoses.medical_record_id="+id+")");
        cascade("delete * from odat_diagnoses where medical_record_id="+id);
        cascade("delete * from individual_reports where medical_record_id="+id);

        
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
    
    public void cascade (String sql) {
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
