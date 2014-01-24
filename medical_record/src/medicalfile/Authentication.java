/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalfile;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author gloria
 */
public class Authentication extends javax.swing.JFrame {

    Statement stmt = null;
    ResultSet rs = null;
    static Connection conn = null;

    String name = null;
    String pass = null;
    String nRole = null;
    int id;

    /**
     * Creates new form Authentication
     *
     *
     */
    public Authentication(Statement stmt, Connection conn) {
        this.stmt = stmt;
        this.conn = conn;
        initComponents();
        setSize(480, 410);
    }

    public String login(Statement stmt, String role, String username, String password) {

        //if statement is created successfully, execute query and get results
        if (stmt != null) {
            try {
                System.out.println(username);
                rs = stmt.executeQuery("SELECT * FROM user WHERE username ='" + username + "' AND password ='" + password + "'AND role ='" + role + "';");
            } catch (SQLException e) {
                System.out.println("Unable to create statement");
            }
        }
        //if resultset is received and is not empty,
        // iterate over resultset to get values
        if (rs != null) {
            try {
                while (rs.next()) {
                    name = rs.getString("username");
                    pass = rs.getString("password");
                    nRole = rs.getString("role");
                    System.out.println("nRole is : " + nRole);
                }
            } catch (SQLException e) {
                System.out.println("Unable to iterate over resultset");
                return "ERROR";
            }
        }

        System.out.println("name: " + name + ", PASS: " + pass + ",Role IS " + nRole);
        System.out.println("role: " + role);

        //Doctor
        if (username.equals(name) && password.equals(pass) && role.equals(nRole) && nRole.equals("Doctor")) {
            new Home(role, stmt).setVisible(true);
            System.out.println("Doc");
        } //Nurse
        else if (username.equals(name) && password.equals(pass) && role.equals(nRole) && nRole.equals("Nurse")) {
            new Home(role, stmt).setVisible(true);
            System.out.println("Nurse");
        } //secretary
        else if (username.equals(name) && password.equals(pass) && role.equals(nRole) && nRole.equals("Secretary")) {
            new Home(role, stmt).setVisible(true);
            System.out.println("Secr");
        } else if (username.equals(name) && password.equals(pass) && role.equals(nRole) && nRole.equals("Administrator")) {
            new AddUser(role, stmt).setVisible(true);
            System.out.println("Admin");
        } else {
            JOptionPane.showMessageDialog(null, "Λάθος κωδικός ή/και όνομα πρόσβασης.", "Ανεπιτυχής Σύνδεση", JOptionPane.ERROR_MESSAGE);
            return "Wrong username or password";
        }
        return "Log In";
    }

    public String exit(Statement stmt, ResultSet rs, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error");
            return "ERROR";
        }
        return "Exit";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jUserNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jRoleComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLogInButton = new javax.swing.JButton();
        jExitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Αναγνωριστικό :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 120, -1));
        getContentPane().add(jUserNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 120, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Κωδικός :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 70, -1));
        getContentPane().add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 120, -1));

        jRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Secretary", "Doctor", "Nurse", "Administrator" }));
        getContentPane().add(jRoleComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ρόλος :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 50, -1));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Σύνδεση :"));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 350, 230));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ΔΙΑΧΕΙΡΙΣΗ ΙΑΤΡΙΚΟΥ ΦΑΚΕΛΟΥ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 290, -1));

        jLogInButton.setText("Σύνδεση");
        jLogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogInButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jLogInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 90, 40));

        jExitButton.setText("Έξοδος");
        jExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 90, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medicalfile/newpackage/Medical_2.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 370));
    }// </editor-fold>//GEN-END:initComponents

    private void jLogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogInButtonActionPerformed
        // TODO add your handling code here:
        String role = (String) jRoleComboBox.getSelectedItem();
        String username = jUserNameTextField.getText();
        String password = jPasswordField.getText();
        String Return = login(stmt, role, username, password);
        System.out.println(Return);
        if (Return == "Log In") {
            this.setVisible(false);
        }
    }//GEN-LAST:event_jLogInButtonActionPerformed

    private void jExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExitButtonActionPerformed
        // TODO add your handling code here:
        System.out.println(exit(stmt, rs, conn));
        System.exit(0);
    }//GEN-LAST:event_jExitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jExitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jLogInButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    public javax.swing.JComboBox jRoleComboBox;
    private javax.swing.JTextField jUserNameTextField;
    // End of variables declaration//GEN-END:variables
}