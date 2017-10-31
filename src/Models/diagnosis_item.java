/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Lucia Tortosa
 */
public class diagnosis_item {
    private int id;
    private String name;
    private String description;
    private Integer parent_id;
    private int lft;
    private int right;
    Date created_at;
    Date updated_at;
    boolean multiple_select;
    private int classification_level;
    private boolean expand;
    private int childs;
    private int level;
    private String show_name;
    private int position;
    private boolean selected;
    
    private Integer odat_diagnosis_id;

    public diagnosis_item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isMultiple_select() {
        return multiple_select;
    }

    public void setMultiple_select(boolean multiple_select) {
        this.multiple_select = multiple_select;
    }

    public int getClassification_level() {
        return classification_level;
    }

    public void setClassification_level(int classification_level) {
        this.classification_level = classification_level;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public int getChilds() {
        return childs;
    }

    public void setChilds(int childs) {
        this.childs = childs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getOdat_diagnosis_id() {
        return odat_diagnosis_id;
    }

    public void setOdat_diagnosis_id(Integer odat_diagnosis_id) {
        this.odat_diagnosis_id = odat_diagnosis_id;
    }
    
    
    
}
