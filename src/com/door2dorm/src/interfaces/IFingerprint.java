/**
    Authors             : Cloyd Van Secuya
    Filename            : IFingerprint.java
    Package             : com.door2dorm.src.interfaces;
    Date of Creation    : July 4, 2023
    Description:
        Defines methods to fetch fingerprint related data 
*/

// PACKAGE SECTION
package com.door2dorm.src.interfaces;



// IMPORT SECTION
import com.door2dorm.src.model.Fingerprint;
import java.util.ArrayList;



public interface IFingerprint {
    
    public Fingerprint [] getAllFingerprintTemplates();
    public Fingerprint getFingerprintByID(int id);
    public Fingerprint getFingerpintByName(String name);
    public ArrayList<Integer> getAllID();
    public void addFingerprintTemplate(Fingerprint fingeprint); 
    public boolean checkIDavailability(int id);
    
}
