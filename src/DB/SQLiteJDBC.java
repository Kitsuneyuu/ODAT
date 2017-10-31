/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia Tortosa
 */
public class SQLiteJDBC {
    
    public static boolean conectado = false;
    public static int center;

    public static Connection OpenDatabase() {
        String url = "jdbc:sqlite:ODAT.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
