/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.center_resource_odat_diagnosis;
import Renders.Table.Tabla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia Tortosa
 */
public class center_resources_odat_diagnosis {

    public final Object[] header = {"", ""};

    String table_name = "center_resources_odat_diagnoses";

    private center_resource_odat_diagnosis model;

    public ArrayList<center_resource_odat_diagnosis> list = null;

    private int odat_diagnosis_id;

    public center_resources_odat_diagnosis(int odat_diagnosis, boolean modify) {
        if (modify) {
            this.odat_diagnosis_id = odat_diagnosis;
        }
        list = select(modify);
    }

    public center_resources_odat_diagnosis(int odat_diagnosis) {
        list = new ArrayList<center_resource_odat_diagnosis>();
        list.add(new center_resource_odat_diagnosis());
        modify(null, odat_diagnosis);
    }

    public void set_Odat_diagnosis_id(int id) {
        this.odat_diagnosis_id = id;
    }

    private ArrayList<center_resource_odat_diagnosis> select(boolean modify) {
        ArrayList<center_resource_odat_diagnosis> center_resources_list = new ArrayList<center_resource_odat_diagnosis>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            if (modify) {
                rs = stmt.executeQuery("SELECT  id, name, description, center_resource_id FROM center_resources "
                        + "LEFT JOIN center_resources_odat_diagnoses ON "
                        + "center_resource_id = id AND odat_diagnosis_id = " + odat_diagnosis_id);
            } else {
                rs = stmt.executeQuery("SELECT  id, name, description, center_resource_id FROM center_resources "
                        + "LEFT JOIN center_resources_odat_diagnoses ON "
                        + "center_resource_id = id AND odat_diagnosis_id = null");
            }
            while (rs.next()) {
                int x = 1;
                center_resource_odat_diagnosis model = new center_resource_odat_diagnosis();
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                Object id = rs.getObject(x++);
                System.out.println(id);
                if (id != null) {
                    model.setOdat_diagnosis(true);
                    model.setExists(true);
                } else {
                    model.setOdat_diagnosis(false);
                    model.setExists(false);
                }
                center_resources_list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return center_resources_list;
    }

    public Object[][] get_Info() {
        Object[][] data = new Object[list.size()][header.length];
        for (int x = 0; x < list.size(); x++) {
            center_resource_odat_diagnosis model = list.get(x);
            data[x][0] = model.isOdat_diagnosis();
            data[x][1] = model.getName();
        }
        return data;
    }

    public void modify(boolean[] selected, int id) {
        this.odat_diagnosis_id = id;
        try {

            Connection con = SQLiteJDBC.OpenDatabase();

            for (int x = 0; x < list.size(); x++) {

                PreparedStatement stmt = null;
                Models.center_resource_odat_diagnosis m = list.get(x);

                if (m.isExists() && !selected[x]) {
                    stmt = con.prepareStatement("delete from " + table_name + " where odat_diagnosis_id = ? AND center_resources_id = ?");
                } else if (!m.isExists() && selected[x]) {
                    stmt = con.prepareStatement("insert into " + table_name + " values (?,?)");
                }

                if (stmt != null) {
                    stmt.setInt(1, odat_diagnosis_id);
                    stmt.setInt(2, m.getId());

                    stmt.executeUpdate();
                    stmt.close();
                }
            }

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

}
