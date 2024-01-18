/**
    Authors             : Cloyd Van Secuya
    Filename            : Valdations.java
    Package             : com.door2dorm.src.view;
    Date of Creation    : July 4, 2023
    Description:
        To handle and process events and user actions.
*/

// PACKAGE SECTION
package com.door2dorm.inputs.validations;

import com.door2dorm.src.controller.Controller;
import java.util.ArrayList;


public class Validations {
    
    private Controller controller;
    ArrayList<Integer> valid_IDs = new ArrayList<Integer>();
    
    public Validations() {
        this.controller = new Controller();
    }
    
    public boolean is_FingerprintID_available(int id) {
        /**
         * @note This method will check the availability of Fingerprint IDs.
         *       It is important that ID should be unique
         * 
         */
        valid_IDs = controller.fetchAllFingerprint_IDs();
        
        for (int i = 0; i < valid_IDs.size(); i++) {
            int temp_ID = valid_IDs.get(i);
            
            if (temp_ID == id) {
                return false;  // ID is already taken, return false
            }
        }
        
        return true;
    }
    
    public boolean is_FingerprintID_empty(int id) {
        /**
         * @note This can check if an ID is 0. 
         *       Remember that an ID should have a value to assign
         */
        if (id == 0) return true;
        else return false;
    } 
   
    public boolean is_FingerprintName_empty(String name) {
        /**
         * @note This method checks if there is a named assigned to a fingerprint.
         *       A fingerprint data template should have ID and Name assigned.
         */
        if (name.isBlank() || name.isEmpty()) return true;
        else return false;
    }
    
    public static void main(String[] args) {
        Validations validations = new Validations();
        
        boolean check = validations.is_FingerprintID_available(5);
        System.out.println("Check: " + check);
    }
}
