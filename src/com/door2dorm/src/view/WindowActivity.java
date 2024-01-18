/**
    Authors             : Cloyd Van Secuya
    Filename            : WindowActivity.java
    Package             : com.door2dorm.src.view;
    Date of Creation    : July 4, 2023
    Description:
        This is the main user interface with Java Swing.
*/

// PACKAGE SECTION
package com.door2dorm.src.view;



// IMPORT SECTION
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class WindowActivity extends JFrame {
    
    // Layout Manager
    CardLayout card;
    
    // Panels 
    JPanel contentP;
    JPanel dashboard;
    JPanel enrollP;
    JPanel verifyP;
    JPanel headerP;
    JPanel sidebarP;
    JPanel padding;
    
    // Labels
    JLabel modes; 
    JLabel headerTitle;
    JLabel largeTxt;
    
    /* Input fields labels */
    /**
     * These are at the Enrollment Panel
     */
    JLabel fingerprintID;
    JLabel name;
    /* Error prompts */
    JLabel error_ID;
    JLabel error_name;
    
    /**
     * These Labels are at the Verification Panel
     */
    JLabel verifyHint;
    
    // Text fields 
    JTextField getFingerprintID;
    JTextField getName;
    
    // Buttons
    JButton home_btn;
    JButton enrollment_btn; 
    JButton verification_btn;
    JButton enroll_fingerprint_btn;
    JButton verify_fingerprint_btn; 
    JButton delete_fingerprints_btn;
    
    // Fonts
    Font seoge = new Font("Segoe UI", Font.PLAIN, 12); 
    Font seoge2 = new Font("Segoe UI", Font.PLAIN, 36); 
    Font calibri = new Font("Calibri", Font.PLAIN, 18);
    
    // Icon and Image
    URL url = WindowActivity.class.getResource("assets/icon/home.png"); 
    ImageIcon home = new ImageIcon(url);
    
    // Event handling
    EventMaster eventhandler = new EventMaster(WindowActivity.this);
    
    public WindowActivity() {
        // Set properties to this frame
        this.setTitle("Door2Dorm Fingerprint Enrollment and Verification");
        this.setSize(960, 720);
        this.setResizable(false);													
        this.setPreferredSize(new Dimension(795, 720));									
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 						
        this.setLocationRelativeTo(null);
        
        initComponents(); 
        
        addComponents();
        
        setActionCommands();
        
        addActionListener();
        
        this.pack();
        this.setVisible(true);
    }
    
    private void initComponents() {
        
        // Init Panels
        contentP = new JPanel();
        dashboard = new JPanel();
        enrollP = new JPanel(new GridLayout(12, 1, 10, 10));
        verifyP = new JPanel(new GridLayout(12, 1, 10, 10));
        headerP = new JPanel(new BorderLayout());
        sidebarP = new JPanel();
        padding = new JPanel();
        
        contentP.setPreferredSize(new Dimension(960, 720));
        headerP.setPreferredSize(new Dimension(800, 50));
        sidebarP.setPreferredSize(new Dimension(144, 400));
        padding.setPreferredSize(new Dimension(600, 0));
        
        enrollP.setOpaque(false);
        verifyP.setOpaque(false);
        padding.setOpaque(false);
        
        contentP.setBackground(Color.WHITE);
        headerP.setBackground(new Color(52, 58, 64));
        sidebarP.setBackground(new Color(52, 58, 64));
        
        // Init Labels
        modes = new JLabel("Modes", JLabel.CENTER);
        modes.setForeground(Color.white);
        modes.setFont(seoge);
        error_ID = new JLabel("", JLabel.LEFT);
        error_name = new JLabel("", JLabel.LEFT);
        largeTxt = new JLabel("", JLabel.CENTER);
        
        headerTitle = new JLabel("Enroll a fingerprint", JLabel.CENTER);
        headerTitle.setForeground(Color.white);
        headerTitle.setFont(calibri);
        
        fingerprintID = new JLabel("Fingerprint ID", JLabel.LEFT);
        name = new JLabel("Name", JLabel.LEFT);
        verifyHint = new JLabel("Turn on verification mode", JLabel.LEFT);
        largeTxt.setFont(seoge2);
        
        // Init Text Fields
        getFingerprintID = new JTextField();
        getFingerprintID.setPreferredSize(new Dimension(600, 20));
        getName = new JTextField();
        getName.setPreferredSize(new Dimension(600, 20));
        
        // Init Buttons
        /* Home Button */
        home_btn = new JButton();
        home_btn.setIcon(home);
        home_btn.setOpaque(false);
        home_btn.setContentAreaFilled(false);
        home_btn.setBorderPainted(false);
        
        /* Sidebar Buttons */
        enrollment_btn = new JButton("Enroll");
        enrollment_btn.setContentAreaFilled(false);
        enrollment_btn.setHorizontalAlignment(SwingConstants.CENTER);
        enrollment_btn.setFont(seoge);
        enrollment_btn.setForeground(Color.white);
        enrollment_btn.setBackground(new Color(52, 58, 64));
        enrollment_btn.setPreferredSize(new Dimension(100, 30));
        
        verification_btn = new JButton("Verify");
        verification_btn.setHorizontalAlignment(SwingConstants.CENTER);
        verification_btn.setFont(seoge);
        verification_btn.setForeground(Color.white);
        verification_btn.setBackground(new Color(52, 58, 64));
        verification_btn.setPreferredSize(new Dimension(100, 30));
        
        delete_fingerprints_btn = new JButton("Delete");
        delete_fingerprints_btn.setHorizontalAlignment(SwingConstants.CENTER);
        delete_fingerprints_btn.setFont(seoge);
        delete_fingerprints_btn.setForeground(Color.white);
        delete_fingerprints_btn.setBackground(new Color(52, 58, 64));
        delete_fingerprints_btn.setPreferredSize(new Dimension(100, 30));
        
        /* Submit buttons */
        enroll_fingerprint_btn = new JButton("Save fingerprint");
        enroll_fingerprint_btn.setHorizontalAlignment(SwingConstants.CENTER);
        enroll_fingerprint_btn.setOpaque(true);
        enroll_fingerprint_btn.setBackground(new Color(3, 173, 18));
        
        /* Turn on verification mode */
        verify_fingerprint_btn = new JButton("Turn On");
        verify_fingerprint_btn.setOpaque(true);
        verify_fingerprint_btn.setBackground(new Color(66, 50, 158));
        verify_fingerprint_btn.setForeground(Color.white);
        
    }
    
    private void addComponents() {
        
        /***
         * @NOTE: Adding the elements to their respective places
         */
        // Header Panel
        headerP.add(home_btn, BorderLayout.EAST);
        headerP.add(headerTitle, BorderLayout.CENTER);
        
        // Sidebar Panel
        sidebarP.add(modes);
        sidebarP.add(enrollment_btn);
        sidebarP.add(verification_btn);
        sidebarP.add(delete_fingerprints_btn);
        
        // Dashboard Panel 
        dashboard.add(largeTxt);
        
        // Enrollment Panel 
        enrollP.setName("Enrollment Panel");
        enrollP.add(fingerprintID);
        enrollP.add(getFingerprintID);
        enrollP.add(error_ID);
        enrollP.add(name);
        enrollP.add(getName);
        enrollP.add(error_name);
        enrollP.add(enroll_fingerprint_btn);
        
        // Verification Panel
        verifyP.setName("Verification Panel");
        verifyP.add(verifyHint);
        verifyP.add(verify_fingerprint_btn);
        verifyP.add(padding);
        
        // Content Panel
        contentP.add(dashboard);
        contentP.add("Enrollment Panel", enrollP);
        contentP.add("Verification Panel", verifyP);
        enrollP.setVisible(false);
        verifyP.setVisible(false);                  // Hide this first
        
        /**
         * Add the configured components to this frame
         */
        this.add(headerP, BorderLayout.NORTH);
        this.add(sidebarP, BorderLayout.WEST);
        this.add(contentP, BorderLayout.CENTER);
        
    }
    
    private void setActionCommands() {
        // Side bar events
        enrollment_btn.setActionCommand("enroll");
        verification_btn.setActionCommand("verify");
        
        // Home button events
        home_btn.setActionCommand("home");
        
        // Enrollment Panel events
        enroll_fingerprint_btn.setActionCommand("save_fingerprint");
        
        // Verification Panel events
        verify_fingerprint_btn.setActionCommand("verify_fingerprint");
        
        // Delete all fingerprints
        delete_fingerprints_btn.setActionCommand("delete_fingerprint");
        
    }
    
    private void addActionListener() {
        // Side bar buttons
        enrollment_btn.addActionListener(eventhandler);
        verification_btn.addActionListener(eventhandler);
        
        // Home button
        home_btn.addActionListener(eventhandler);
        
        // Enroll finger button
        enroll_fingerprint_btn.addActionListener(eventhandler);
        
        // Verify finger button
        verify_fingerprint_btn.addActionListener(eventhandler);
        
        // Delete fingerprints button
        delete_fingerprints_btn.addActionListener(eventhandler);
        
    }
    
    public static void main(String[] args) {
        new WindowActivity();
    }
}
