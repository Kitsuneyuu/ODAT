/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders.Buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Lucia Tortosa
 */
public class Button {
    
    public JButton getButton (String name, String text) {
        JButton btn = getButton(name);
        btn.setText(text);
        
        return btn;
    }
    public JButton getButton(String name) {

        JButton btn1 = new JButton();
        String path = null;

        if (name.equals("d")) {
            path = "/images/icons/delete.gif";
        } else if (name.equals("m")) {
            path = "/images/icons/edit.png";
        } else if (name.equals("s")) {
            path = "/images/icons/show.gif";
        } else if (name.equals("+")){
            path = "/images/icons/down.png";
        } else if(name.equals("-")){
            path = "/images/icons/up.png";
        } else if(name.equals("minus")){
            //btn1.setHorizontalAlignment(SwingConstants.LEFT);
            path = "/images/tree/minus.png";
        } else if(name.equals("plus")){
            path = "/images/tree/plus.png";
        }

        btn1.setIcon(new ImageIcon(this.getClass().getResource(path)));
        btn1.setName(name);
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(false);

        btn1.setOpaque(true);

        return btn1;
    }

}
