/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Lucia Tortosa
 */
public class center_resource_odat_diagnosis {
    
    private int id;
    private String name;
    private String description;
    private boolean odat_diagnosis;
    private boolean exists;
    private int odat_id;

    public center_resource_odat_diagnosis() {
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

    public boolean isOdat_diagnosis() {
        return odat_diagnosis;
    }

    public void setOdat_diagnosis(boolean odat_diagnosis) {
        this.odat_diagnosis = odat_diagnosis;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public int getOdat_id() {
        return odat_id;
    }

    public void setOdat_id(int odat_id) {
        this.odat_id = odat_id;
    }
    
    
    
}
