/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders.List;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Lucia Tortosa
 */
public class ComboBoxRenderer extends BasicComboBoxRenderer {
    
    
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Object[] item = (Object[]) value;
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            if (-1 < index && item.length == 3) {
                list.setToolTipText(item[2] + "");
            }
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setText((String) item[1]);
        setFont(list.getFont());
        //setText((value == null) ? "" : value.toString());
        return this;
    }
    
//    String[] description = null;
//    public ComboBoxRenderer (String[] description){
//        this.description = description;
//    }
//
//        public Component getListCellRendererComponent(JList list, Object value,
//                int index, boolean isSelected, boolean cellHasFocus) {
//            Object[] item = (Object[])value;
//            if (isSelected) {
//                setBackground(list.getSelectionBackground());
//                setForeground(list.getSelectionForeground());
//                if (-1 < index && item.length == 3) {
//                    list.setToolTipText(description[index]);
//                }
//            } else {
//                setBackground(list.getBackground());
//                setForeground(list.getForeground());
//            }
//            
//            setText((String)item[0]);
//            setFont(list.getFont());
//            setText((value == null) ? "" : value.toString());
//            return this;
//        }
    }