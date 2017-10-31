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
public class individual_report {
   
    private int id;
    private int medical_record_id;
    private int odat_diagnoses_id;
    private String topic;
    private String tests;
    private String results;
    private String orientation;
    
    private Date created_at;
    private Date updated_at;
    
    private String signature;
    private Date signed_on;
    private Boolean show_signature;
    private Boolean show_signed_on;
    
    private int configurable_view_id;
    private Boolean show_medical_record_archive_date;
    private Boolean show_birth_date;
    private Boolean show_age;
    private Boolean show_full_name;
    private Boolean show_gender;
    private Boolean show_birth_position;
    private Boolean show_address;
    private Boolean show_siblings_data;
    private Boolean show_father_data;
    private Boolean show_mother_data;
    private Boolean show_phone_numbers;
    private Boolean show_diagnosis_created_at;
    private Boolean show_consultation_details;
    private Boolean show_center_resources;
    private Boolean show_detailed_diagnosis;
    private Boolean show_coordination_services;
    private Boolean show_main_diagnosis;
    private String extra_information;
    private Boolean show_handicap_data;
    private Boolean show_level3;
    
    public individual_report(){
        
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

    public int getOdat_diagnoses_id() {
        return odat_diagnoses_id;
    }

    public void setOdat_diagnoses_id(int odat_diagnoses_id) {
        this.odat_diagnoses_id = odat_diagnoses_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getSigned_on() {
        return signed_on;
    }

    public void setSigned_on(Date signed_on) {
        this.signed_on = signed_on;
    }

    public Boolean getShow_signature() {
        return show_signature;
    }

    public void setShow_signature(Boolean show_signature) {
        this.show_signature = show_signature;
    }

    public Boolean getShow_signed_on() {
        return show_signed_on;
    }

    public void setShow_signed_on(Boolean show_signed_on) {
        this.show_signed_on = show_signed_on;
    }

    public int getConfigurable_view_id() {
        return configurable_view_id;
    }

    public void setConfigurable_view_id(int configurable_view_id) {
        this.configurable_view_id = configurable_view_id;
    }

    public Boolean getShow_medical_record_archive_date() {
        return show_medical_record_archive_date;
    }

    public void setShow_medical_record_archive_date(Boolean show_medical_record_archive_date) {
        this.show_medical_record_archive_date = show_medical_record_archive_date;
    }

    public Boolean getShow_birth_date() {
        return show_birth_date;
    }

    public void setShow_birth_date(Boolean show_birth_date) {
        this.show_birth_date = show_birth_date;
    }

    public Boolean getShow_age() {
        return show_age;
    }

    public void setShow_age(Boolean show_age) {
        this.show_age = show_age;
    }

    public Boolean getShow_full_name() {
        return show_full_name;
    }

    public void setShow_full_name(Boolean show_full_name) {
        this.show_full_name = show_full_name;
    }

    public Boolean getShow_gender() {
        return show_gender;
    }

    public void setShow_gender(Boolean show_gender) {
        this.show_gender = show_gender;
    }

    public Boolean getShow_birth_position() {
        return show_birth_position;
    }

    public void setShow_birth_position(Boolean show_birth_position) {
        this.show_birth_position = show_birth_position;
    }

    public Boolean getShow_address() {
        return show_address;
    }

    public void setShow_address(Boolean show_address) {
        this.show_address = show_address;
    }

    public Boolean getShow_siblings_data() {
        return show_siblings_data;
    }

    public void setShow_siblings_data(Boolean show_siblings_data) {
        this.show_siblings_data = show_siblings_data;
    }

    public Boolean getShow_father_data() {
        return show_father_data;
    }

    public void setShow_father_data(Boolean show_father_data) {
        this.show_father_data = show_father_data;
    }

    public Boolean getShow_mother_data() {
        return show_mother_data;
    }

    public void setShow_mother_data(Boolean show_mother_data) {
        this.show_mother_data = show_mother_data;
    }

    public Boolean getShow_phone_numbers() {
        return show_phone_numbers;
    }

    public void setShow_phone_numbers(Boolean show_phone_numbers) {
        this.show_phone_numbers = show_phone_numbers;
    }

    public Boolean getShow_diagnosis_created_at() {
        return show_diagnosis_created_at;
    }

    public void setShow_diagnosis_created_at(Boolean show_diagnosis_created_at) {
        this.show_diagnosis_created_at = show_diagnosis_created_at;
    }

    public Boolean getShow_consultation_details() {
        return show_consultation_details;
    }

    public void setShow_consultation_details(Boolean show_consultation_details) {
        this.show_consultation_details = show_consultation_details;
    }

    public Boolean getShow_center_resources() {
        return show_center_resources;
    }

    public void setShow_center_resources(Boolean show_center_resources) {
        this.show_center_resources = show_center_resources;
    }

    public Boolean getShow_detailed_diagnosis() {
        return show_detailed_diagnosis;
    }

    public void setShow_detailed_diagnosis(Boolean show_detailed_diagnosis) {
        this.show_detailed_diagnosis = show_detailed_diagnosis;
    }

    public Boolean getShow_coordination_services() {
        return show_coordination_services;
    }

    public void setShow_coordination_services(Boolean show_coordination_services) {
        this.show_coordination_services = show_coordination_services;
    }

    public Boolean getShow_main_diagnosis() {
        return show_main_diagnosis;
    }

    public void setShow_main_diagnosis(Boolean show_main_diagnosis) {
        this.show_main_diagnosis = show_main_diagnosis;
    }

    public String getExtra_information() {
        return extra_information;
    }

    public void setExtra_information(String extra_information) {
        this.extra_information = extra_information;
    }

    public Boolean getShow_handicap_data() {
        return show_handicap_data;
    }

    public void setShow_handicap_data(Boolean show_handicap_data) {
        this.show_handicap_data = show_handicap_data;
    }

    public Boolean getShow_level3() {
        return show_level3;
    }

    public void setShow_level3(Boolean show_level3) {
        this.show_level3 = show_level3;
    }
    
    
    
    
    
}
