/**
    Authors             : Cloyd Van Secuya
    Filename            : SelectAllRows.java
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



public class SelectAllRowsByString {
    private EstablishConnection connect = new EstablishConnection();
    private Statement statement = null;
    private ResultSet rs = null; 
    
    public SelectAllRowsByString() {}
    
    public Fingerprint fetchFingerprintByName(String byName) {
        
        Fingerprint fingerprint_as_ls = new Fingerprint();
        
        connect.getConnection();
        String qry = "SELECT * FROM FINGERPRINT WHERE name = " + "'" + byName + "'";
        
        try {
            statement = connect.getConnection().createStatement();
            rs = statement.executeQuery(qry);
            System.out.println("SELECT STATEMENT: " + qry);
            
            while (rs.next()) {
                int fingerprint_id = rs.getInt("fingerprint_id");
                String name = rs.getString("name");
                String date_enrolled = rs.getString("date_enrolled");
                String status = rs.getString("status");
                
                fingerprint_as_ls = new Fingerprint(fingerprint_id, name, status);
   
            }
            
        } 
        
        catch(SQLException e ) {
            // Print to console the possible cause of error/s
            String msg = "SQL statement may be incorrect or record/s are existing!";
            String possible_err_statement = qry;
            System.out.println(msg);
            System.out.println(possible_err_statement);
            e.printStackTrace();
            
            // Return an empty set if no records found            
            return fingerprint_as_ls; 
        }
        
        return fingerprint_as_ls;
    }
    
}
