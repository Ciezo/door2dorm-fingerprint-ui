/**
    Authors             : Cloyd Van Secuya
    Filename            : EventMaster.java
    Package             : com.door2dorm.src.view;
    Date of Creation    : July 4, 2023
    Description:
        To handle and process events and user actions.
*/

// PACKAGE SECTION
package com.door2dorm.src.view;



// IMPORT SECTION
import com.door2dorm.inputs.validations.Validations;
import com.door2dorm.src.controller.Controller;
import com.door2dorm.src.model.Fingerprint;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JOptionPane;



public class EventMaster implements ActionListener {

    Fingerprint fp; 
    int FINGERPRINT_ID;
    String FINGERPRINT_NAME;
    String sensor_status = "";
    
    Controller controller = new Controller();
    
    Validations validations = new Validations();
    
    WindowActivity windowActivity;
    
    SerialPort sp = SerialPort.getCommPort("COM3");
    SerialPort [] serialPorts; 
    
    public EventMaster(WindowActivity windowActivity) {
        this.windowActivity = windowActivity;
        this.controller = new Controller();
        
        this.serialPorts = SerialPort.getCommPorts();
        
        this.sp.setComPortParameters(9600, 8, 1, 0);
        this.sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        
        if (!this.sp.openPort()) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            System.out.println("COM Port not found!");
            sensor_status = "Fingerprint sensor offline!";
            JOptionPane.showMessageDialog(null, 
                    "Fingerprint sensor is not connected!", 
                    "Errors found", 
                    JOptionPane.ERROR_MESSAGE);
        } 
        
        else {
            System.out.println("COM Port successful!");
            sensor_status = "Fingerprint sensor online!";
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command; 
        e.getSource();
        command = e.getActionCommand();
        
        switch (command) {
            case "enroll":
                windowActivity.enrollP.setVisible(true);
                windowActivity.verifyP.setVisible(false);
                windowActivity.headerTitle.setText("Enroll a fingerprint");
                
                /**
                 * @SerialConnection  
                 */
                Integer mode = 1;
                try { sp.getOutputStream().write(mode.byteValue()); System.out.println("Sent to Serial");}
                catch (IOException ex) {System.out.println("An error occurred in writing to Serial Connection");}
            
                
                break;

                
            case "verify":
                windowActivity.verifyP.setVisible(true);
                windowActivity.enrollP.setVisible(false);
                windowActivity.headerTitle.setText("Configure fingerprint verification");
                
                break;
                
            case "home":
                windowActivity.enrollP.setVisible(true);
                windowActivity.verifyP.setVisible(false);
                windowActivity.headerTitle.setText("Enroll a fingerprint");
                
                break;
                
            case "save_fingerprint":  
                /**
                 * Fetch the ID
                 */
                String temp_id = windowActivity.getFingerprintID.getText();
                int id = Integer.valueOf(temp_id);
                System.out.println("Entered ID: " + id);
                
                /**
                 * Fetch the name
                 */
                String name = windowActivity.getName.getText();
                System.out.println("Entered Name: " + name);
                
                boolean check1 = validations.is_FingerprintID_available(id);
                boolean check2 = validations.is_FingerprintID_empty(id);
                boolean check3 = validations.is_FingerprintName_empty(name);
                
                System.out.println("Check 1: " + check1);
                System.out.println("Check 2: " + check2);
                System.out.println("Check 3: " + check3);
                
                if (check1 == true && check2 == false && check3 == false) {
                    // If ID is not taken and is not empty and we have a given name
                    
                    /* Remove the error prompts */
                    windowActivity.error_ID.setText("");
                    windowActivity.error_ID.setVisible(false);
                    windowActivity.error_name.setText("");
                    windowActivity.error_ID.setVisible(false);
                    
                    
                    /**
                     * @If No validations are passed
                     */
                    FINGERPRINT_ID = id;
                    FINGERPRINT_NAME = name;
                    
                    Integer id_byte = FINGERPRINT_ID;
                    System.out.println("Integer ID: " + FINGERPRINT_ID);
                    // Write the id and send to Arduino
                    try { sp.getOutputStream().write(id_byte.byteValue()); System.out.println("ID Sent to Serial");} 
                    catch (IOException ex) { System.out.println("An error occurred in writing to Serial Connection");}
                    
                    
                    fp = new Fingerprint(FINGERPRINT_ID, FINGERPRINT_NAME, "ok");
                    // @Controller here
                    controller.saveFingerprint(fp);

                    JOptionPane.showMessageDialog(null,
                            "Fingerprint enrolled!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                }
                
                else if (check1 == false || check2 == true) {
                    windowActivity.error_ID.setText("Fingerprint ID is taken or empty");
                    windowActivity.error_ID.setForeground(Color.red);
                    windowActivity.error_ID.setVisible(true);
                }
                
                if (check3 == true) {
                    // If name is empty 
                    
                    windowActivity.error_name.setText("Name is empty");
                    windowActivity.error_name.setForeground(Color.red);
                    windowActivity.error_name.setVisible(true);
                }
                
                
                
                break;
            
            case "verify_fingerprint":
                String temp = windowActivity.verify_fingerprint_btn.getText();
                
                if (temp.equals("Turn Off")) {
                    windowActivity.verify_fingerprint_btn.setText("Turn On");
                    windowActivity.verify_fingerprint_btn.setBackground(new Color(66, 50, 158));
                    System.out.println("\t Turning off verification");                   
                }
                
                else if (temp.equals("Turn On")){
                    windowActivity.verify_fingerprint_btn.setText("Turn Off");
                    windowActivity.verify_fingerprint_btn.setBackground(Color.red);
                    System.out.println("\t Turning on verification");
                    
                    /**
                    * @SerialConnection  
                    */
                    Integer mode2 = 2;
                    try { sp.getOutputStream().write(mode2.byteValue()); System.out.println("Sent to Serial");
                    } catch (IOException ex) {System.out.println("An error occurred in writing to Serial Connection");}
                }
                
                
                break;
                
            case "delete_fingerprint":
                int opt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete all fingerprints?!",
                        "Delete all fingerprints?",
                        JOptionPane.OK_CANCEL_OPTION);
                
                if (opt == JOptionPane.OK_OPTION) {
                    /**
                     * @SerialConnection
                     */
                    Integer mode3 = 3;
                    try { sp.getOutputStream().write(mode3.byteValue()); System.out.println("Sent to Serial");
                    } catch (IOException ex) { System.out.println("An error occurred in writing to Serial Connection"); }
                } 
                
                break;
        }
    }
        
    
}
