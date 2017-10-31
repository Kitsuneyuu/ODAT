/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.SQLiteJDBC;
import Models.user;
import Renders.Table.Tabla;
import Synch.ODAT_Client;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Lucia Tortosa
 */
public class users {

    String table_name = "users";
    user model;
    Views.users.form view;


    public users() {

    }

    public void insert(Views.users.form view) {
        this.view = view;
        int id = -1;
        String user = view.field_user.getText();
        String password = DigestUtils.sha256Hex(new String(view.field_password.getPassword()));
        String name = view.field_name.getText();
        String rol = view.field_rol.getItemAt(view.field_rol.getSelectedIndex());
        String center = view.ids_centers[0][view.field_center.getSelectedIndex()] + "";
        String email = view.field_email.getText();

        Date creation_date = getTime();
        Date update_date = getTime();

        if (view.field_rol.getSelectedIndex() == 2) {
            id = -get_length();
        } else {
            try {
                ODAT_Client.c.dos.writeUTF("newUser" + "\t" + user + "\t" + password + "\t" + name + "\t" + email + "\t" + rol + "\t" + center + "\t" + creation_date + "\t" + update_date);
            } catch (IOException ex) {
                //Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            long executionTime = System.currentTimeMillis();
                
                
                while (id == -1 && executionTime - System.currentTimeMillis() < 15000) {
                    id = ODAT_Client.id;
                }
                
                System.out.println(executionTime - System.currentTimeMillis());

        }

        try {
            Connection con = SQLiteJDBC.OpenDatabase();

            PreparedStatement stmt = null;
            int x = 1;

            stmt = con.prepareStatement("insert into " + table_name + " (id, login, crypted_password, email, name, center_id, role, created_at, updated_at) values (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(x++, id);

            stmt.setString(x++, user);
            stmt.setString(x++, password);
            stmt.setString(x++, email);
            stmt.setString(x++, name);
            stmt.setObject(x++, center);
            stmt.setString(x++, rol);
            stmt.setObject(x++, creation_date);
            stmt.setObject(x++, update_date);

            stmt.executeUpdate();

            stmt.close();
            ODAT_Client.id = 0;
        } catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private java.sql.Timestamp getTime() {
        java.util.Date date = new Date();
        java.sql.Timestamp param = new java.sql.Timestamp(date.getTime());

        return param;
    }

    public int get_length() {
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
}
