/**
    Authors             : Cloyd Van Secuya
    Filename            : TestSerialConnection.java
    Package             : com.door2dorm.tests;
    Date of Creation    : July 4, 2023
    Description:
        This class is a test to create a connection to the Serial port
*/

// PACKAGE SECTION
package com.door2dorm.tests;



// IMPORT SECTION
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.util.Scanner;



public class TestSerialConnection {
    
    public static void main(String[] args) throws IOException {
        SerialPort [] serialPorts = SerialPort.getCommPorts();
        
        SerialPort sp = SerialPort.getCommPort("COM3");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        
        if (!sp.openPort()) {
            System.out.println("COM Port not found!");
        } 
        
        else {
            System.out.println("COM Port successful!");
        }

        // Try getting an input from Java 
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.print("\n Enter an input");
            Integer opt = sc.nextInt();
            /**
             * Now, parse the opt:Integer to a writing bytes value to send 
             * to the micro-controller 
             */
            sp.getOutputStream().write(opt.byteValue());
        }
    }
    
}
