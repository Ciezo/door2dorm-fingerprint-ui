/**
    Authors             : Cloyd Van Secuya
    Filename            : Controller.java
    Package             : com.door2dorm.src.controller;
    Date of Creation    : July 4, 2023
    Description:
        This is the Controller used to access the interface to Database layer 
*/

// PACKAGE SECTION
package com.door2dorm.src.controller;



// IMPORT SECTION
import com.door2dorm.src.database.Database;
import com.door2dorm.src.model.Fingerprint;
import com.door2dorm.src.interfaces.IFingerprint;
import java.util.ArrayList;



public class Controller {
    
    IFingerprint interfaces = new Database();
    
    public Controller() {
        this.interfaces = new Database();
    }
    
    public void saveFingerprint(Fingerprint fingerprint) {
        Fingerprint new_fp = new Fingerprint();
        new_fp = new Fingerprint(
                fingerprint.getFingerprint_ID(),
                fingerprint.getName(), 
                fingerprint.getStatus());
        
        interfaces.addFingerprintTemplate(new_fp);
        
    }
    
    public Fingerprint [] fetchAllFingerprintTemplates() {
        Fingerprint [] fp;
        fp = interfaces.getAllFingerprintTemplates();
        
        return fp;
    }
    
    public Fingerprint fetchFingerprintById(int byID) {
        Fingerprint fp = new Fingerprint();
        fp = interfaces.getFingerprintByID(byID);
        
        return fp;
    }
    
    public Fingerprint fetchFingerprintByName(String byName) {
        Fingerprint fp = new Fingerprint();
        fp = interfaces.getFingerpintByName(byName);
        
        return fp;
    }

    public ArrayList<Integer> fetchAllFingerprint_IDs() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids = interfaces.getAllID();
        
        return ids;
    }
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        ArrayList<Integer> valid_IDs = new ArrayList<Integer>();
        valid_IDs = controller.fetchAllFingerprint_IDs();
        
        for(int i = 0; i < valid_IDs.size(); i++) {
            System.out.println("Controller");
            System.out.println("ID: " + valid_IDs.get(i));
        }
    }
}
