/**
    Authors             : Cloyd Van Secuya
    Filename            : Database.java
    Package             : com.door2dorm.src.database;
    Date of Creation    : July 4, 2023
    Description:
        The Database.java interacts with the SQL processing and handling on the its layer to
        fetch the Fingerprint attributes and its assigned values either as objects and such.
*/

// PACKAGE SECTION
package com.door2dorm.src.database;


// IMPORT SECTION
import com.door2dorm.src.interfaces.IFingerprint;
import com.door2dorm.src.model.Fingerprint;
import com.door2dorm.src.sql.CheckIdAvailability;
import com.door2dorm.src.sql.InsertRow;
import com.door2dorm.src.sql.SelectAllRows;
import com.door2dorm.src.sql.SelectAllRowsByID;
import com.door2dorm.src.sql.SelectAllRowsByString;
import com.door2dorm.src.sql.SelectIDColumns;
import java.util.ArrayList;



public class Database implements IFingerprint {

    Fingerprint [] fp_arr;
    Fingerprint fp; 
    ArrayList<Integer> ids; 
    
    @Override
    public Fingerprint[] getAllFingerprintTemplates() {
        SelectAllRows s = new SelectAllRows();
        fp_arr = s.fetchAllFingerprints();
        
        return fp_arr;
    }

    @Override
    public Fingerprint getFingerprintByID(int id) {
        SelectAllRowsByID s = new SelectAllRowsByID();
        fp = s.fetchFingerprintByID(id);
        
        return fp;
    }

    @Override
    public Fingerprint getFingerpintByName(String name) {
        SelectAllRowsByString s = new SelectAllRowsByString();
        fp = s.fetchFingerprintByName(name);
        
        return fp;
    }

    @Override
    public void addFingerprintTemplate(Fingerprint fingeprint) {
       InsertRow insert = new InsertRow();
       insert.createFingerprintTemplate(fingeprint);
    }

    @Override
    public boolean checkIDavailability(int id) {
       CheckIdAvailability check_id = new CheckIdAvailability();
       
       return check_id.check_fingerprintID_availability(id);
    }

    @Override
    public ArrayList<Integer> getAllID() {
        SelectIDColumns s = new SelectIDColumns(); 
        ids = s.fetchAllFingerprintIDs();
        
        return ids;
    }
    
}
