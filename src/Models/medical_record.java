/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Lucia Tortosa
 */
public class medical_record {
    private int id;
    private int center_id;
    private String name;
    private String surname;
    private Date birth_date;
    private Date archive_date;
    private String city;
    private String address;
    private int birth_position;
    private String gender;

    //Father Information
    private String father_name;
    private String father_surname;
    private Date father_birth_date;
    private Integer father_job_id;
    private Integer father_civil_state_id;
    private Integer father_job_status_id;
    private Integer father_formation_level_id;
    private String father_email;
    private String father_extra_information;
    
    //Mother Information
    private String mother_name;
    private String mother_surname;
    private Date mother_birth_date;
    private Integer mother_job_id;
    private Integer mother_civil_state_id;
    private Integer mother_job_status_id;
    private Integer mother_formation_level_id;
    private String mother_email;
    private String mother_extra_information;
    
    //Phones
    private String home_phone;
    private String portable_phone;
    private String work_phone;
    
    private int total_siblings_amount;
    private String postal;
    private boolean sanitary_services;
    private boolean social_services;
    private boolean educative_services;
    
    private java.util.Date created_at;
    private java.util.Date updated_at;
    
    private boolean multiple_birth;
    
    private Integer province_id;
    
    private int position_in_siblings;
    private boolean handicap;
    private int dependency_degree;
    private Integer school_type_id;
    
    public medical_record(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCenter_id() {
        return center_id;
    }

    public void setCenter_id(int center_id) {
        this.center_id = center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getArchive_date() {
        return archive_date;
    }

    public void setArchive_date(Date archive_date) {
        this.archive_date = archive_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirth_position() {
        return birth_position;
    }

    public void setBirth_position(int birth_position) {
        this.birth_position = birth_position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_surname() {
        return father_surname;
    }

    public void setFather_surname(String father_surname) {
        this.father_surname = father_surname;
    }

    public Date getFather_birth_date() {
        return father_birth_date;
    }

    public void setFather_birth_date(Date father_birth_date) {
        this.father_birth_date = father_birth_date;
    }

    public Integer getFather_job_id() {
        return father_job_id;
    }

    public void setFather_job_id(Integer father_job_id) {
        this.father_job_id = father_job_id;
    }

    public Integer getFather_civil_state_id() {
        return father_civil_state_id;
    }

    public void setFather_civil_state_id(Integer father_civil_state_id) {
        this.father_civil_state_id = father_civil_state_id;
    }

    public Integer getFather_job_status_id() {
        return father_job_status_id;
    }

    public void setFather_job_status_id(Integer father_job_status_id) {
        this.father_job_status_id = father_job_status_id;
    }

    public Integer getFather_formation_level_id() {
        return father_formation_level_id;
    }

    public void setFather_formation_level_id(Integer father_formation_level_id) {
        this.father_formation_level_id = father_formation_level_id;
    }

    public String getFather_email() {
        return father_email;
    }

    public void setFather_email(String father_email) {
        this.father_email = father_email;
    }

    public String getFather_extra_information() {
        return father_extra_information;
    }

    public void setFather_extra_information(String father_extra_information) {
        this.father_extra_information = father_extra_information;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMother_surname() {
        return mother_surname;
    }

    public void setMother_surname(String mother_surname) {
        this.mother_surname = mother_surname;
    }

    public Date getMother_birth_date() {
        return mother_birth_date;
    }

    public void setMother_birth_date(Date mother_birth_date) {
        this.mother_birth_date = mother_birth_date;
    }

    public Integer getMother_job_id() {
        return mother_job_id;
    }

    public void setMother_job_id(Integer mother_job_id) {
        this.mother_job_id = mother_job_id;
    }

    public Integer getMother_civil_state_id() {
        return mother_civil_state_id;
    }

    public void setMother_civil_state_id(Integer mother_civil_state_id) {
        this.mother_civil_state_id = mother_civil_state_id;
    }

    public Integer getMother_job_status_id() {
        return mother_job_status_id;
    }

    public void setMother_job_status_id(Integer mother_job_status_id) {
        this.mother_job_status_id = mother_job_status_id;
    }

    public Integer getMother_formation_level_id() {
        return mother_formation_level_id;
    }

    public void setMother_formation_level_id(Integer mother_formation_level_id) {
        this.mother_formation_level_id = mother_formation_level_id;
    }

    public String getMother_email() {
        return mother_email;
    }

    public void setMother_email(String mother_email) {
        this.mother_email = mother_email;
    }

    public String getMother_extra_information() {
        return mother_extra_information;
    }

    public void setMother_extra_information(String mother_extra_information) {
        this.mother_extra_information = mother_extra_information;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getPortable_phone() {
        return portable_phone;
    }

    public void setPortable_phone(String portable_phone) {
        this.portable_phone = portable_phone;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }

    public int getTotal_siblings_amount() {
        return total_siblings_amount;
    }

    public void setTotal_siblings_amount(int total_siblings_amount) {
        this.total_siblings_amount = total_siblings_amount;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public boolean isSanitary_services() {
        return sanitary_services;
    }

    public void setSanitary_services(boolean sanitary_services) {
        this.sanitary_services = sanitary_services;
    }

    public boolean isSocial_services() {
        return social_services;
    }

    public void setSocial_services(boolean social_services) {
        this.social_services = social_services;
    }

    public boolean isEducative_services() {
        return educative_services;
    }

    public void setEducative_services(boolean educative_services) {
        this.educative_services = educative_services;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.util.Date created_at) {
        this.created_at = created_at;
    }

    public java.util.Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(java.util.Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isMultiple_birth() {
        return multiple_birth;
    }

    public void setMultiple_birth(boolean multiple_birth) {
        this.multiple_birth = multiple_birth;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public int getPosition_in_siblings() {
        return position_in_siblings;
    }

    public void setPosition_in_siblings(int position_in_siblings) {
        this.position_in_siblings = position_in_siblings;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public int getDependency_degree() {
        return dependency_degree;
    }

    public void setDependency_degree(int dependency_degree) {
        this.dependency_degree = dependency_degree;
    }

    public Integer getSchool_type_id() {
        return school_type_id;
    }

    public void setSchool_type_id(Integer school_type_id) {
        this.school_type_id = school_type_id;
    }

    
    
}
