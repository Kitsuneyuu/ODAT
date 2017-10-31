/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders.Subtable;

import Renders.Table.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lucia Tortosa
 */
public class HeaderRender extends DefaultTableCellRenderer {

    public HeaderRender () {
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        if (value instanceof JButton) {
            setSize(30, 30);
        } 
        
        setFont(getFont().deriveFont(Font.BOLD, 14));
        setBackground(new java.awt.Color(83, 135, 175));
        setForeground(new java.awt.Color(239, 243, 246));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return this;
    }
}
