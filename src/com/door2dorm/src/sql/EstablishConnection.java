/**
    Authors             : Cloyd Van Secuya
    Filename            : EstablishConnection.java
    Package             : com.door2dorm.src.sql;
    Date of Creation    : July 4, 2023
    Description:
        This Java class file is responsible for establish a connection to the 
        database via remote 
*/

// PACKAGE SECTION
package com.door2dorm.src.sql;



// IMPORT SECTION
import java.sql.*;


public class EstablishConnection {
    
    Connection conn = null;
    private final String HOST; 
    private final String USER;
    private final String PASSWORD;
    private final String SCHEMA;
    
    public EstablishConnection() {
        String URI = "mysql://b6242f07ae4393:c5ba3043@us-cluster-east-01.k8s.cleardb.net/heroku_f47e36bc84bb2cb?reconnect=true";
        this.HOST = "us-cluster-east-01.k8s.cleardb.net";
        this.USER = "b6242f07ae4393";
        this.PASSWORD = "c5ba3043";
        this.SCHEMA = "heroku_f47e36bc84bb2cb";
        
        try {
            // Connection to remote
            conn = DriverManager.getConnection("jdbc:" + URI, this.USER, this.PASSWORD); 
            System.out.println("Connected to remote via: " + URI);
        } 
        
        catch (SQLException e) {
            System.out.println("An error occured!");
            e.printStackTrace(); 
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static void main(String[] args) {
        new EstablishConnection();
    }
}
