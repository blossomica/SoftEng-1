/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gloria
 */
public class Search extends javax.swing.JFrame {

    String pid, fn, mn, ls, dob, addr, ct, pc, ph, ssn, ms, it, mid, role;
    int row;
    static Statement stmt = null;
    ResultSet rs = null;

    /**
     * Creates new form Search
     *
     * @param role
     * @param stmt
     */
    public Search(String role, Statement stmt) {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        this.role = role;
        this.stmt = stmt;
        showTable(stmt);
        btnEnabling();
    }

    public void btnEnabling() {
        jDisplayButton.setEnabled(false);
        jEditButton.setEnabled(false);
        jDeleteButton.setEnabled(false);
    }

    public String showTable(Statement stmt) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (stmt != null) {
            try {
                rs = stmt.executeQuery("select  patient.*, medical_info.mid from patient JOIN medical_info  on patient.pid = medical_info.pid;");
            } catch (SQLException es) {
                System.out.println("Unable to create statement ");
                return "ERROR";
            }
        }

        if (rs != null /*& rs1 !=null*/) {
            try {
                while (rs.next()) {
                    this.pid = rs.getString("pid");
                    this.fn = rs.getString("first_name");
                    this.ls = rs.getString("last_name");
                    this.dob = rs.getString("date_of_birth");
                    this.addr = rs.getString("address");
                    this.ct = rs.getString("city");
                    this.pc = rs.getString("post_code");
                    this.ph = rs.getString("phone");
                    this.it = rs.getString("insurance_type");
                    this.mid = rs.getString("mid");
                    model.addRow(new Object[]{fn, ls, dob, addr, ct, pc, pid, ph, it, mid});
                }
                return "Showed";
            } catch (SQLException e) {
                System.out.println("Unable to iterate over resultset");
                JOptionPane.showMessageDialog(null, "Πρόβλημα στη βάση", "Ανεπιτυχής Εμφάνιση", JOptionPane.ERROR_MESSAGE);
                return "Show table error";
            }
        }
        return "ERROR";
    }

    public String find(Statement stmt, String comboBox, String textField) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (stmt != null) {
            try {
                if (comboBox.equals("Αριθμό Διαβατηρίου")) {
                    rs = stmt.executeQuery("select  patient.*, medical_info.mid from patient JOIN medical_info  on patient.pid = medical_info.pid where patient.pid = '" + textField + "';");
                } else if (comboBox.equals("Αριθμό Φακέλου")) {
                    rs = stmt.executeQuery("select  patient.*, medical_info.mid from patient JOIN medical_info  on patient.pid = medical_info.pid where medical_info.mid = '" + textField + "';");
                } else if (comboBox.equals("Επώνυμο Ασθενή")) {
                    rs = stmt.executeQuery("select  patient.*, medical_info.mid from patient JOIN medical_info  on patient.pid = medical_info.pid where patient.last_name = '" + textField + "';");
                } else if (comboBox.equals("Όνομα Ασθενή")) {
                    rs = stmt.executeQuery("select  patient.*, medical_info.mid from patient JOIN medical_info  on patient.pid = medical_info.pid where patient.first_name = '" + textField + "';");
                }
            } catch (SQLException es) {
                System.out.println("Unable to create statement ");
                System.out.println(rs);
                return null;
            }
        }

        if (rs != null) {
            try {
                rs.previous();
                while (rs.next()) {
                    this.pid = rs.getString("pid");
                    this.fn = rs.getString("first_name");
                    this.ls = rs.getString("last_name");
                    this.dob = rs.getString("date_of_birth");
                    this.addr = rs.getString("address");
                    this.ct = rs.getString("city");
                    this.pc = rs.getString("post_code");
                    this.ph = rs.getString("phone");
                    this.it = rs.getString("insurance_type");
                    this.mid = rs.getString("mid");
                    model.addRow(new Object[]{fn, ls, dob, addr, ct, pc, pid, ph, it, mid});
                }
                System.out.printf("NAME IS " + fn + " PASSPORT NUM IS " + pid + " MID IS " + mid + "%n");
                return "Found";
            } catch (SQLException e) {
                System.out.println("Unable to iterate over resultset");
                JOptionPane.showMessageDialog(null, "Δεν βρέθηκε ασθενής", "Ανεπιτυχής Αναζήτηση", JOptionPane.ERROR_MESSAGE);
                model.setRowCount(0);
                return "This PID does not exist";
            }
        }
        return "ERROR";
    }

    public String delete(Statement stmt, String pid) {

        int option = JOptionPane.showConfirmDialog(null, "Είστε ότι θέλετε να διαγράψετε?", "Ανεπιτυχής Διαγραφή", JOptionPane.YES_NO_OPTION);
        if ((stmt != null) && (option == JOptionPane.YES_OPTION)) {
            try {
                String sql1, sql2, sql3;

                sql1 = "DELETE FROM patient WHERE pid='" + pid + "';";
                sql2 = "DELETE FROM record WHERE pid='" + pid + "';";
                sql3 = "DELETE FROM medical_info WHERE pid='" + pid + "';";
                System.out.println(sql1);
                this.row = stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);
            } catch (SQLException es) {
                System.out.println("Unable to insert into the databese");
                JOptionPane.showMessageDialog(null, "Μη ολοκληρωμένα πεδία.", "Ανεπιτυχής Διαγραφή", JOptionPane.ERROR_MESSAGE);
            }

            if (stmt == null) {
                JOptionPane.showMessageDialog(null, "Μη ολοκληρωμένα πεδία.", "Ανεπιτυχής Διαγραφή", JOptionPane.ERROR_MESSAGE);
                return "ERROR";
            } else if (row == 0) {
                JOptionPane.showMessageDialog(null, "Δεν υπάρχει ο χρήστης.", "Ανεπιτυχής Διαγραφή", JOptionPane.INFORMATION_MESSAGE);
                return "This ID does not exist";
            } else {
                JOptionPane.showMessageDialog(null, "Ο χρήστης διαγράφηκε επιτυχώς.", "Επιτυχής Διαγραφή", JOptionPane.INFORMATION_MESSAGE);
                return "Deleted";
            }
        }
        return "ERROR";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        jSearchPidTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSearchButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();
        jDisplayButton = new javax.swing.JButton();
        jEditButton = new javax.swing.JButton();
        jHomeButton = new javax.swing.JButton();
        jSearchComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Anazi");

        jSearchPidTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchPidTextFieldActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Όνομα", "Επώνυμο", "Ημερομηνία γέννησης", "Διεύθυνση", "Πόλη", "Τηλέφωνο", "Αρ. Διαβατηρίου/ Ταυτότητας", "Ασφάλιση", "Α.Μ Φακέλου"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jSearchButton.setText("Αναζήτηση");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setText("Διαγραφή");
        jDeleteButton.setMaximumSize(new java.awt.Dimension(75, 23));
        jDeleteButton.setMinimumSize(new java.awt.Dimension(75, 23));
        jDeleteButton.setPreferredSize(new java.awt.Dimension(75, 23));
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        jDisplayButton.setText("Προβολή");
        jDisplayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDisplayButtonActionPerformed(evt);
            }
        });

        jEditButton.setText("Επεξεργασία");
        jEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditButtonActionPerformed(evt);
            }
        });

        jHomeButton.setText("Αρχική Σελίδα");
        jHomeButton.setMaximumSize(new java.awt.Dimension(75, 23));
        jHomeButton.setMinimumSize(new java.awt.Dimension(75, 23));
        jHomeButton.setPreferredSize(new java.awt.Dimension(75, 23));
        jHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHomeButtonActionPerformed(evt);
            }
        });

        jSearchComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Αριθμό Διαβατηρίου", "Όνομα Ασθενή", "Επώνυμο Ασθενή", "Αριθμό Φακέλου" }));
        jSearchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSearchPidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jDisplayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSearchPidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDisplayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchPidTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchPidTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchPidTextFieldActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        String textField = jSearchPidTextField.getText();
        String comboBox = (String) jSearchComboBox.getSelectedItem();
        String Return = find(stmt, comboBox, textField);
        System.out.println(Return);
        btnEnabling();
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        System.out.println(delete(stmt, pid));
        model.setRowCount(0);
        System.out.println(showTable(stmt));
        btnEnabling();
    }//GEN-LAST:event_jDeleteButtonActionPerformed

    private void jDisplayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDisplayButtonActionPerformed
        // TODO add your handling code here:
        Create cr1 = new Create(false, false, false, true, false, role, stmt);
        System.out.println(cr1.display(pid, false, false, stmt));
        cr1.setVisible(true);
    }//GEN-LAST:event_jDisplayButtonActionPerformed

    private void jEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditButtonActionPerformed
        // TODO add your handling code here:
        Create cr2 = new Create(false, false, true, true, false, role, stmt);
        if (role == "Doctor") {
            System.out.println(cr2.display(pid, false, true, stmt));
        } else {
            System.out.println(cr2.display(pid, true, true, stmt));
        }
        cr2.setVisible(true);
    }//GEN-LAST:event_jEditButtonActionPerformed

    private void jHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHomeButtonActionPerformed
        this.setVisible(false);
        new Home(role, stmt).setVisible(true);
    }//GEN-LAST:event_jHomeButtonActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        this.pid = target.getValueAt(row, 6).toString();

        if (role.equals("Nurse")) {
            System.out.println(role);
            jEditButton.setEnabled(false);
            jDeleteButton.setEnabled(false);
            jDisplayButton.setEnabled(true);
        } else if (role.equals("Doctor")) {
            System.out.println(role);
            jEditButton.setEnabled(true);
            jDeleteButton.setEnabled(false);
            jDisplayButton.setEnabled(true);
        } else {
            jDisplayButton.setEnabled(true);
            jEditButton.setEnabled(true);
            jDeleteButton.setEnabled(true);
        }

        if (evt.getClickCount() == 2) {
            Create cr1 = new Create(false, false, false, true, false, role, stmt);
            target = (JTable) evt.getSource();
            System.out.println(cr1.display(pid, false, false, stmt));
            cr1.setVisible(true);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void jSearchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JButton jDisplayButton;
    private javax.swing.JButton jEditButton;
    private javax.swing.JButton jHomeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JComboBox jSearchComboBox;
    private javax.swing.JTextField jSearchPidTextField;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
