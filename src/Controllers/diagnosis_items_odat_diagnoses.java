/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.center_resource_odat_diagnosis;
import Models.diagnosis_item;
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
public class diagnosis_items_odat_diagnoses {

    public final Object[] header = {"", ""};

    String table_name = "diagnosis_items_odat_diagnoses";

    private diagnosis_item model;

    public ArrayList<diagnosis_item> list = null;

    private int odat_diagnosis_id;

    public diagnosis_items_odat_diagnoses(int odat_diagnosis, boolean modify) {
        if (modify) {
            this.odat_diagnosis_id = odat_diagnosis;
        }
        list = select(modify);
    }

    public diagnosis_items_odat_diagnoses(int odat_diagnosis) {
        list = new ArrayList<diagnosis_item>();
        list.add(new diagnosis_item());
        modify(null, odat_diagnosis);
    }

    public void set_Odat_diagnosis_id(int id) {
        this.odat_diagnosis_id = id;
    }

    private ArrayList<diagnosis_item> select(boolean modify) {
        ArrayList<diagnosis_item> diagnosis_items_list = new ArrayList<diagnosis_item>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            if (modify) {
                rs = stmt.executeQuery("SELECT  id, name, description, parent_id, position,"
                        + "classification_level,lvl,childs,diagnosis_items_odat_diagnoses.diagnosis_item_id  FROM "
                        + "diagnosis_items LEFT JOIN diagnosis_items_odat_diagnoses ON diagnosis_item_id = id "
                        + "AND odat_diagnosis_id = " + odat_diagnosis_id + " ORDER BY position");
            } else {
                rs = stmt.executeQuery("SELECT  id, name, description, parent_id, position,"
                        + "classification_level,lvl,childs,diagnosis_items_odat_diagnoses.diagnosis_item_id  FROM "
                        + "diagnosis_items LEFT JOIN diagnosis_items_odat_diagnoses ON diagnosis_item_id = id AND "
                        + "odat_diagnosis_id = null ORDER BY position");
            }
            while (rs.next()) {
                int x = 1;
                diagnosis_item model = new diagnosis_item();
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                model.setParent_id((Integer) rs.getObject(x++));
                model.setPosition(rs.getInt(x++));
                model.setClassification_level(rs.getInt(x++));
                model.setLevel(rs.getInt(x++));
                model.setChilds(rs.getInt(x++));
                model.setOdat_diagnosis_id((Integer) rs.getObject(x++));

                diagnosis_items_list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return diagnosis_items_list;
    }

    public void modify(ArrayList<Integer> diagnosis_items, int id) {
        this.odat_diagnosis_id = id;
        try {

            Connection con = SQLiteJDBC.OpenDatabase();

            PreparedStatement stmt = null;
            stmt = con.prepareStatement("delete from " + table_name + " where odat_diagnosis_id = ?");
            stmt.setInt(1, odat_diagnosis_id);
            stmt.executeUpdate();
            stmt.close();

            for (int x = 0; x < diagnosis_items.size(); x++) {

                stmt = null;

                stmt = con.prepareStatement("insert into " + table_name + " values (?,?)");

                if (stmt != null) {

                    stmt.setInt(1, diagnosis_items.get(x));
                    stmt.setInt(2, odat_diagnosis_id);

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
