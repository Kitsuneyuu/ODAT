/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders.Table;

import DB.SQLiteJDBC;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Lucia Tortosa
 */
public class Tabla {

    private int num_rows = 0;
    private int num_columns = 0;

    public void ver_tabla(JTable tabla, Object[] header, Object[][] data) {

        tabla.setDefaultRenderer(Object.class, new Render());
        tabla.getTableHeader().setDefaultRenderer(new HeaderRender());

        DefaultTableModel d = new DefaultTableModel(
                data,
                header
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        tabla.setModel(d);
        tabla.setRowHeight(30);

        TableColumn column = null;
        
        for (int x =0; x<header.length; x++){
            if (header[x].equals("")){
                column = tabla.getColumnModel().getColumn(x);
                column.setMaxWidth(30);
            }
        }
    } 
}
