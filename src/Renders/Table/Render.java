/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders.Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Lucia Tortosa
 */
public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton btn = (JButton) value;

            if (isSelected){
                btn.setBackground(new Color(57,105,138));
            } else {
                if (row%2 == 0){
                    btn.setBackground(Color.white);
                } else {
                    btn.setBackground(new Color(242, 242, 242));
                }
            }
            return btn;
        }
        
        if (row%2 == 0){
                    setBackground(Color.white);
                } else {
                    setBackground(new Color(242, 242, 242));
                }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
