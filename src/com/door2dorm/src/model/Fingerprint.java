/**
    Authors             : Cloyd Van Secuya
    Filename            : Fingerprint.java
    Package             : com.door2dorm.src.model;
    Date of Creation    : July 4, 2023
    Description:
        Defines a data template for the fingerprint
*/

// PACKAGE SECTION
package com.door2dorm.src.model;



// IMPORT SECTION
import java.util.Date;



public class Fingerprint {
    
    private int fingerprint_ID;
    private String name; 
    private Date date_enrolled;
    private String status;

    public Fingerprint() {}
    
    public Fingerprint(int fingerprint_ID, String name, String status) {
        this.fingerprint_ID = fingerprint_ID;
        this.name = name;
        this.status = status;
        
        this.date_enrolled = new Date();
    }

    public int getFingerprint_ID() {
        return fingerprint_ID;
    }

    public void setFingerprint_ID(int fingerprint_ID) {
        this.fingerprint_ID = fingerprint_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_enrolled() {
        return date_enrolled;
    }

    public void setDate_enrolled(Date date_enrolled) {
        this.date_enrolled = date_enrolled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
