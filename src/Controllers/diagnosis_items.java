/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.diagnosis_item;
import Renders.Table.Tabla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia Tortosa
 */
public class diagnosis_items {

    public final String table_name = "diagnosis_items";
    public final Object[] header = {"Nombre", "", "", "", ""};

    private diagnosis_item model;
    private Views.admin.diagnosis_items.index view;

    public ArrayList<diagnosis_item> list = null;
    
    public int roots = 0;
    
    public diagnosis_items() {
        list = select();
    }

    public diagnosis_items(Views.admin.diagnosis_items.index view) {
        this.view = view;
        list = select();
    }

    public diagnosis_item insert(Views.admin.diagnosis_items.form form, int row) {
        model = new diagnosis_item();

        model.setChilds(0);
        model.setId(get_id());
        model.setName(form.field_name.getText());
        model.setDescription(form.field_description.getText());
        model.setExpand(true);

        if (row == -1) {
            model.setParent_id(null);
            model.setLevel(0);
            model.setPosition(list.size());
            list.add(model);
            model.setClassification_level(form.field_level.getValue());
        } else {
            diagnosis_item parent = list.get(row);
            parent.setChilds(parent.getChilds() + 1);
            model.setClassification_level(parent.getClassification_level());
            model.setParent_id(parent.getId());
            model.setLevel(parent.getLevel() + 1);
            model.setPosition(row + parent.getChilds());

            list.add(model.getPosition(), model);
        }

        insert(model);
        updatePositions(model);
        showList();
        return model;
    }

    private void insert(diagnosis_item m) {

        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            PreparedStatement stmt = null;

            stmt = con.prepareStatement("insert into " + table_name + " (id, name, description, parent_id, position,"
                    + " created_at, updated_at, multiple_select, classification_level, childs, lvl) values (?,?,?,?,?,?,?,?,?,?,?)");
            int i = 1;

            stmt.setInt(i++, m.getId());
            stmt.setString(i++, m.getName());
            stmt.setString(i++, m.getDescription());
            stmt.setObject(i++, m.getParent_id());

            stmt.setInt(i++, m.getPosition());
            stmt.setObject(i++, m.getCreated_at());
            stmt.setObject(i++, m.getUpdated_at());

            stmt.setBoolean(i++, m.isMultiple_select());
            stmt.setInt(i++, m.getClassification_level());

            stmt.setInt(i++, m.getChilds());
            stmt.setInt(i++, m.getLevel());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePositions(diagnosis_item model) {
        int position = model.getPosition();
        if (position != list.size() - 1) {
            for (int x = position; x < list.size(); x++) {
                diagnosis_item m = list.get(x);
                m.setPosition(x);
                try {
                    Connection con = SQLiteJDBC.OpenDatabase();
                    PreparedStatement stmt = null;
                    stmt = con.prepareStatement("update " + table_name + " SET position=?, childs=? where id=" + m.getId());
                    System.out.println("Posicion: "+m.getPosition()+". Objeto: "+m.getName());
                    stmt.setInt(1, m.getPosition());
                    stmt.setInt(2, m.getChilds());
                    stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Object[][] get_Data() {
        Object[][] data = new Object[list.size()][header.length];
        Renders.Buttons.Button button = new Renders.Buttons.Button();
        for (int x = 0; x < list.size(); x++) {
            Models.diagnosis_item model = list.get(x);
            String name = model.getName();
            for (int i = 1; i <= model.getLevel(); i++) {
                name = "    " + name;
            }
            data[x][0] = name;
            data[x][1] = button.getButton("plus");
            data[x][2] = button.getButton("s");
            data[x][3] = button.getButton("m");
            data[x][4] = button.getButton("d");
        }
        return data;
    }

    private ArrayList<diagnosis_item> select() {
        ArrayList<diagnosis_item> diagnosis_item_list = new ArrayList<diagnosis_item>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, description, parent_id, position,"
                    + " created_at, updated_at, classification_level, lvl, childs FROM " + table_name + " ORDER BY position;");

            while (rs.next()) {
                int x = 1;
                diagnosis_item model = new Models.diagnosis_item();
                model.setId(rs.getInt(x++));
                model.setName(rs.getString(x++));
                model.setDescription(rs.getString(x++));
                model.setParent_id((Integer) rs.getObject(x++));
                model.setPosition(rs.getInt(x++));
                model.setCreated_at(rs.getDate(x++));
                model.setUpdated_at(rs.getDate(x++));
                model.setClassification_level(rs.getInt(x++));
                model.setLevel(rs.getInt(x++));
                model.setChilds(rs.getInt(x++));

                diagnosis_item_list.add(model);
                
                if (model.getParent_id()==null){
                    roots++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return diagnosis_item_list;
    }

    public void modify(diagnosis_item model, Views.admin.diagnosis_items.form view) {
        this.model = model;

        String name = view.field_name.getText();
        String description = view.field_description.getText();
        Date update_date = getTime();

        try {
            Connection con = SQLiteJDBC.OpenDatabase();

            PreparedStatement stmt = null;
            int x = 1;

            stmt = con.prepareStatement("update " + table_name + " SET name=?, description=?, created_at=?, updated_at=? where id=" + model.getId());
            Date creation_date = model.getCreated_at();

            model.setName(name);
            model.setDescription(description);
            model.setUpdated_at(update_date);

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

    private java.sql.Timestamp getTime() {
        java.util.Date date = new Date();
        java.sql.Timestamp param = new java.sql.Timestamp(date.getTime());

        return param;
    }

    public Models.diagnosis_item getModel(int pos) {
        Models.diagnosis_item model = list.get(pos);

        return model;
    }

    public diagnosis_item searchParent(int id) {
        diagnosis_item m = null;
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).getId() == id) {
                m = list.get(x);
            }
        }
        return m;
    }

    public Object[][] get_Info() {
        Object[][] info = new String[list.size() + 1][3];
        info[0][0] = null;
        info[0][1] = null;
        info[0][2] = null;

        for (int x = 1; x < list.size() + 1; x++) {
            Models.diagnosis_item model = list.get(x - 1);
            info[x][0] = model.getId() + "";
            info[x][1] = model.getName();
            info[x][2] = model.getDescription();
        }
        return info;
    }

    public void delete(diagnosis_item m) {
        if (m.getParent_id() != null) {
            diagnosis_item parent = searchParent(m.getParent_id());
            parent.setChilds(parent.getChilds() - 1);
        }
        int position = m.getPosition();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM " + table_name + " WHERE id=" + m.getId();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.remove(position);
        showList();
        updatePositions(list.get(position - 1));
    }

    private void showList() {
        for (int x = 0; x < list.size(); x++) {
            System.out.println("Posicion:" + list.get(x).getPosition() + ". Objeto: " + list.get(x).getName());
        }
        System.out.println("------------------------------------------");
    }
}
