/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.odat_diagnoses;

import Controllers.diagnosis_items_odat_diagnoses;
import Models.diagnosis_item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author Lucia Tortosa
 */
public class diagnostics_tree extends javax.swing.JPanel {

    public int option;
    public boolean changes = false;
    public int main;

    Views.odat_diagnoses.form view;

    JTree tree = null;

    /**
     * Creates new form NewJPanel
     */
    public diagnostics_tree(int odat_diagnostic, boolean modify, int option, int main, ArrayList<diagnosis_item> list, Views.odat_diagnoses.form view) {
        this.option = option;
        this.main = main;
        this.view = view;

        initComponents();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Diagnósticos");
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree = new JTree(model);

        DefaultMutableTreeNode grandfather = null;
        DefaultMutableTreeNode father = null;
        DefaultMutableTreeNode child = null;

        for (int x = 0; x < list.size(); x++) {
            diagnosis_item m = list.get(x);
            if (option != 2 || (option == 2 && m.getClassification_level() == 2)) {
                if (m.getLevel() == 0) {
                    grandfather = new DefaultMutableTreeNode(m.getName());
                    root.add(grandfather);
                } else if (m.getLevel() == 1) {
                    father = new DefaultMutableTreeNode(m.getName());
                    grandfather.add(father);
                } else {
                    if (m.getOdat_diagnosis_id() != null || main == m.getId()) {
                        child = new DefaultMutableTreeNode(new CheckBoxNode(m.getName(), true, m.getId()));
                        view.mainText = m.getName();
                    } else{
                        child = new DefaultMutableTreeNode(new CheckBoxNode(m.getName(), false, m.getId()));
                    }
                    father.add(child);
                }
            }

        }

        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);

        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBounds(0, 0, 500, 500);
        add(scrollPane);
    }

    class CheckBoxNodeRenderer implements TreeCellRenderer {

        private JCheckBox leafRenderer = new JCheckBox();

        private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();

        Color selectionBorderColor, selectionForeground, selectionBackground,
                textForeground, textBackground;

        protected JCheckBox getLeafRenderer() {
            return leafRenderer;
        }

        public CheckBoxNodeRenderer() {
            Font fontValue;
            fontValue = UIManager.getFont("Tree.font");
            if (fontValue != null) {
                leafRenderer.setFont(fontValue);
            }
            Boolean booleanValue = (Boolean) UIManager
                    .get("Tree.drawsFocusBorderAroundIcon");
            leafRenderer.setFocusPainted((booleanValue != null)
                    && (booleanValue.booleanValue()));

            selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
            selectionForeground = UIManager.getColor("Tree.selectionForeground");
            selectionBackground = UIManager.getColor("Tree.selectionBackground");
            textForeground = UIManager.getColor("Tree.textForeground");
            textBackground = UIManager.getColor("Tree.textBackground");
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {

            Component returnValue;
            if (leaf) {

                String stringValue = tree.convertValueToText(value, selected,
                        expanded, leaf, row, false);
                leafRenderer.setText(stringValue);
                leafRenderer.setSelected(false);

                leafRenderer.setEnabled(tree.isEnabled());

                if (selected) {
                    leafRenderer.setForeground(selectionForeground);
                    leafRenderer.setBackground(selectionBackground);
                } else {
                    leafRenderer.setForeground(textForeground);
                    leafRenderer.setBackground(textBackground);
                }

                if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
                    Object userObject = ((DefaultMutableTreeNode) value)
                            .getUserObject();
                    if (userObject instanceof CheckBoxNode) {
                        CheckBoxNode node = (CheckBoxNode) userObject;
                        leafRenderer.setText(node.getText());

                        if (option == 0 && node.getText().equals(view.mainText)) {
                            node.setSelected(true);
                        } else if (option == 0 && !node.getText().equals(view.mainText)) {
                            node.setSelected(false);
                        } else if (node.isSelected() && !view.selectedItems.contains(node.getText())) {

                            view.selectedItems.add(node.getText());

                        } else if (!node.isSelected() && view.selectedItems.contains(node.getText())) {
                            view.selectedItems.remove(node.getText());

                        }

                        leafRenderer.setSelected(node.isSelected());

                    }
                }
                returnValue = leafRenderer;
            } else {
                returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree,
                        value, selected, expanded, leaf, row, hasFocus);
            }
            return returnValue;
        }
    }

    class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

        ChangeEvent changeEvent = null;

        JTree tree;

        public CheckBoxNodeEditor(JTree tree) {
            this.tree = tree;
        }

        public Object getCellEditorValue() {
            JCheckBox checkbox = renderer.getLeafRenderer();
            CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
                    checkbox.isSelected());
            return checkBoxNode;
        }

        public boolean isCellEditable(EventObject event) {
            boolean returnValue = false;
            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                TreePath path = tree.getPathForLocation(mouseEvent.getX(),
                        mouseEvent.getY());
                if (path != null) {
                    Object node = path.getLastPathComponent();
                    if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                        Object userObject = treeNode.getUserObject();
                        CheckBoxNode n = (CheckBoxNode) userObject;

                        if (option == 0) {
                            view.mainText = n.getText();
                            changes = true;
                        }
                        returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));

                    }
                }
            }
            return returnValue;
        }

        public Component getTreeCellEditorComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row) {

            Component editor = renderer.getTreeCellRendererComponent(tree, value,
                    true, expanded, leaf, row, true);

            // editor always selected / focused
            ItemListener itemListener = new ItemListener() {
                public void itemStateChanged(ItemEvent itemEvent) {
                    if (stopCellEditing()) {
                        fireEditingStopped();
                    }
                }
            };
            if (editor instanceof JCheckBox) {
                ((JCheckBox) editor).addItemListener(itemListener);
            }

            return editor;
        }
    }

    class CheckBoxNode {

        String text;

        boolean selected;

        int id;

        public CheckBoxNode(String text, boolean selected) {
            this.text = text;
            this.selected = selected;
        }

        public CheckBoxNode(String text, boolean selected, int id) {
            this.text = text;
            this.selected = selected;
            this.id = id;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean newValue) {
            selected = newValue;
        }

        public String getText() {
            return text;
        }

        public void setText(String newValue) {
            text = newValue;
        }

        public String toString() {
            return getClass().getName() + "[" + text + "/" + selected + "]";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
