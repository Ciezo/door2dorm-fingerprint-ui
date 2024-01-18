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



public class CheckIdAvailability {
    
    private EstablishConnection connect = new EstablishConnection();
    private Statement statement = null;
    private ResultSet rs = null; 
    
    public CheckIdAvailability() {}
    
    public boolean check_fingerprintID_availability(int byID) {
        
        connect.getConnection();
        String qry = "SELECT fingerprint_id FROM FINGERPRINT WHERE fingerprint_id = " + byID;
        
        try {
            statement = connect.getConnection().createStatement();
            rs = statement.executeQuery(qry);
            System.out.println("SELECT STATEMENT: " + qry);
            
            if(rs.next()) {
                int temp_id = rs.getInt("fingerprint_id");
                // Compare the fetched id to the passed id 
                if (temp_id == byID) {
                    System.out.println("Similar IDs found!");
                    return false; 
                }
                
                else {
                    return true;
                }
            }
            
        } 
        
        catch(SQLException e) {
            // Print to console the possible cause of error/s
            String msg = "SQL statement may be incorrect or record/s are existing!";
            String possible_err_statement = qry;
            System.out.println(msg);
            System.out.println(possible_err_statement);
            e.printStackTrace();
            
        }
        
        return true;
    }
    
}
