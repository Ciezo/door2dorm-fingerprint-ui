/**
    Authors             : Cloyd Van Secuya
    Filename            : SelectAllRowsByID.java
    Package             : com.door2dorm.src.sql;
    Date of Creation    : July 4, 2023
    Description:
        This class defines how data processing and fetching are both done
        with SQL logic
*/

// PACKAGE SECTION
package com.door2dorm.src.sql;



// IMPORT SECTION
import com.door2dorm.src.model.Fingerprint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class SelectIDColumns {
    private EstablishConnection connect = new EstablishConnection();
    private Statement statement = null;
    private ResultSet rs = null;
    
    public ArrayList<Integer> fetchAllFingerprintIDs() {
        
        ArrayList<Integer> fingerpint_record = new ArrayList<Integer>();
        
        connect.getConnection();
        String qry = "SELECT fingerprint_id FROM FINGERPRINT";
        
        try {
            statement = connect.getConnection().createStatement();
            rs = statement.executeQuery(qry);
            System.out.println("SELECT STATEMENT: " + qry);
            
            while (rs.next()) {
                int fingerprint_id = rs.getInt("fingerprint_id");                
                fingerpint_record.add(fingerprint_id);
                
            }
            
        } 
        
        catch(SQLException e ) {
            // Print to console the possible cause of error/s
            String msg = "SQL statement may be incorrect or record/s are existing!";
            String possible_err_statement = qry;
            System.out.println(msg);
            System.out.println(possible_err_statement);
            e.printStackTrace();
            
        }
        
        return fingerpint_record;
        
    }
    
    public static void main(String[] args) {
        SelectIDColumns s = new SelectIDColumns();
        
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids = s.fetchAllFingerprintIDs();
        
        for(int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println("ID: " + id);
        }
    }
    
}
