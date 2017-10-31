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
public class odat_diagnose {
    
    private int id;
    private int medical_record_id;
    private int origin_source_id;
    private int origin_cause_id;
    private int consultation_cause_id;
    private int main_diagnosis_item_id;
    private String description;
    private Date created_at;
    private Date updated_at;
    
    public odat_diagnose(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getMedical_record_id() {
        return medical_record_id;
    }

    public void setMedical_record_id(int medical_record_id) {
        this.medical_record_id = medical_record_id;
    }

    public int getOrigin_source_id() {
        return origin_source_id;
    }

    public void setOrigin_source_id(int origin_source_id) {
        this.origin_source_id = origin_source_id;
    }

    public int getOrigin_cause_id() {
        return origin_cause_id;
    }

    public void setOrigin_cause_id(int origin_cause_id) {
        this.origin_cause_id = origin_cause_id;
    }

    public int getConsultation_cause_id() {
        return consultation_cause_id;
    }

    public void setConsultation_cause_id(int consultation_cause_id) {
        this.consultation_cause_id = consultation_cause_id;
    }

    public int getMain_diagnosis_item_id() {
        return main_diagnosis_item_id;
    }

    public void setMain_diagnosis_item_id(int main_diagnosis_item_id) {
        this.main_diagnosis_item_id = main_diagnosis_item_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    

}
