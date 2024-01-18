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



public class InsertRow {
    
    private EstablishConnection connect = new EstablishConnection();
    private Statement statement = null;
    
    public InsertRow() {}
    
    public void createFingerprintTemplate(Fingerprint fingerprint) {
        
        connect.getConnection();
        String qry = "INSERT INTO FINGERPRINT(fingerprint_id, name, date_enrolled, status) VALUES "
                + "('"+fingerprint.getFingerprint_ID()+"',"
                + "'"+fingerprint.getName()+"',"
                + "'"+fingerprint.getDate_enrolled().toString()
                +"','"+fingerprint.getStatus()+"')"; 
        
        try {
            statement = connect.getConnection().createStatement();
            statement.executeUpdate(qry);
            statement.close();
            System.out.println("SELECT STATEMENT: " + qry);
            
        } 
        
        catch(SQLException e ) {
            // Print to console the possible cause of error/s
            String msg = "SQL statement may be incorrect or record/s are existing!";
            String possible_err_statement = qry;
            System.out.println(msg);
            System.out.println(possible_err_statement);
            e.printStackTrace();
        }
    }
    
}
