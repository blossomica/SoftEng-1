/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalfile;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author gloria
 */
public class Home extends javax.swing.JFrame {

    String role;
    Statement stmt = null;
    static Connection conn = null;

    /**
     * Creates new form Home
     */
    public Home(String role, Statement stmt) {

        setPreferredSize(new Dimension(580, 575));
        initComponents();

        this.role = role;
        this.stmt = stmt;

        if (role.equals("Doctor") || role.equals("Nurse")) {
            createBtn.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createBtn = new javax.swing.JButton();
        displayhBtn = new javax.swing.JButton();
        logOffBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        createBtn.setText("Δημιουργεία");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });
        getContentPane().add(createBtn);
        createBtn.setBounds(220, 140, 125, 60);

        displayhBtn.setText("Προβολή");
        displayhBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayhBtnActionPerformed(evt);
            }
        });
        getContentPane().add(displayhBtn);
        displayhBtn.setBounds(220, 260, 130, 60);

        logOffBtn.setText("Αποσύνδεση");
        logOffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffBtnActionPerformed(evt);
            }
        });
        getContentPane().add(logOffBtn);
        logOffBtn.setBounds(220, 380, 130, 55);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("ΔΙΑΧΕΙΡΙΣΗ ΙΑΤΡΙΚΟΥ ΦΑΚΕΛΟΥ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 20, 385, 32);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medicalfile/newpackage/Medical_2.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 580, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Create cr = new Create(true, true, false, false, true, role, stmt);
        cr.setVisible(true);
    }//GEN-LAST:event_createBtnActionPerformed

    private void displayhBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayhBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Search srch = new Search(role, stmt);
        srch.setVisible(true);
    }//GEN-LAST:event_displayhBtnActionPerformed

    private void logOffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOffBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Authentication(stmt, conn).setVisible(true);
    }//GEN-LAST:event_logOffBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton displayhBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logOffBtn;
    // End of variables declaration//GEN-END:variables
}