/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synch;

import DB.SQLiteJDBC;
import java.io.IOException;
import java.sql.Connection;
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
public class synch {

    public final String table_name = "sync";

    private Models.sync model;

    public ArrayList<Models.sync> list = null;

    public synch() {
        list = select();
        
    }

    private ArrayList<Models.sync> select() {
        ArrayList<Models.sync> sync_list = new ArrayList<Models.sync>();
        try {
            Connection con = SQLiteJDBC.OpenDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  sync.id, table_id, name, aux  FROM sync, tables");

            while (rs.next()) {
                int x = 1;
                Models.sync model = new Models.sync();
                model.setId(rs.getInt(x++));
                model.setTable(rs.getInt(x++));
                model.setTable_name(rs.getString(x++));
                model.setAux((Integer) rs.getObject(x++));
                sync_list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(synch.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sync_list;
    }

    public int getId() {
        int id = 0;
        long executionTime = System.currentTimeMillis();

        while (id == 0 && executionTime - System.currentTimeMillis() < 15000) {
            id = ODAT_Client.id;
        }

        return id;
    }

    public void update() {
        for (int x = 0; x < list.size(); x++) {
            try {
                model = list.get(x);

                switch (model.getTable()) {
                    case 1:
                        Controllers.center_resources controller = new Controllers.center_resources(model.getId());
                        ODAT_Client.c.dos.writeUTF("/0\t" + model.getTable_name() + "\t" + controller.model.getName() + "\t" + controller.model.getDescription() + "\t" + controller.model.getCreated_at() + "\t" + controller.model.getUpdated_at());
                        int id = getId();
                        controller.insert_sync(controller.model, getId(), 0, model.getId());
                        ODAT_Client.id = 0;
                        System.out.println("Se ha actualizado un recurso: "+id);
                }

            } catch (IOException ex) {
                Logger.getLogger(synch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
//            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
