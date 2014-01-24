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

/**
 *
 * @author gloria
 */
public class Create extends javax.swing.JFrame {

    public String pid, npid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex;
    String mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg;
    String doctor_id, secretary_id, nurse_id, dp, role;
    boolean edit;

    static Statement stmt = null;
    ResultSet rs = null;

    /**
     * Creates new form Create
     *
     * @param role
     * @param stmt
     * @param ClearVisible
     * @param CreateVisible
     * @param ReturnVisible
     * @param HomeVisible
     * @param EditVisible
     */
    public Create(boolean ClearVisible, boolean CreateVisible, boolean EditVisible, boolean ReturnVisible, boolean HomeVisible, String role, Statement stmt) {
        initComponents();
        setSize(930, 550);

        this.role = role;
        this.stmt = stmt;
        btnEnabling(ClearVisible, CreateVisible, EditVisible, ReturnVisible, HomeVisible, role);
    }

    public void btnEnabling(boolean ClearVisible, boolean CreateVisible, boolean EditVisible, boolean ReturnVisible, boolean HomeVisible, String role) {
        this.role = role;

        jCreateButton.setVisible(ClearVisible);
        jButtonClean.setVisible(CreateVisible);
        jButtonSave.setVisible(EditVisible);
        jButtonClose.setVisible(ReturnVisible);
        jButtonHome.setVisible(HomeVisible);

        if (role.equals("Nurse")) {
            jTextArea7.setVisible(false);
            jTextArea8.setVisible(false);
        }

    }

    public String create_file(String pid, String fn, String mn, String ls, String job, String dob, String ag, String addr,
            String ct, String pc, String ph, String ht, String ssn, String ms, String it, String sex, String mid, String cur_ill, String past_ill,
            String curr_med, String sur_proc, String imm, String alrg, String fmh, String sh, String preg, String doctor_id,
            String secretary_id, String nurse_id, String dp, String role) {

        if (stmt != null) {
            try {
                String sql, sql2, sql3;
                sql = "INSERT INTO medical_record.patient (pid, first_name, middle_name, last_name,job, date_of_birth, age, address, city, post_code, phone, height, social_sec_num, marital_stat, insurance_type,sex) "
                        + "VALUES ( '" + pid + "','" + fn + "','" + mn + "','" + ls + "','" + job + "','" + dob + "','" + ag + "','" + addr + "','" + ct + "','" + pc + "','" + ph + "','" + ht + "','" + ssn + "','" + ms + "','" + it + "','" + sex + "');";

                sql2 = "INSERT INTO medical_info (mid, pid, curr_illness, past_illness, curr_medication, surgical_procedure, immunazation, allergy, family_med_history, social_history, pregnancy) "
                        + "VALUES ('" + mid + "', '" + pid + "','" + cur_ill + "','" + past_ill + "','" + curr_med + "','" + sur_proc + "','" + imm + "','" + alrg + "','" + fmh + "','" + sh + "','" + preg + "');";

                sql3 = "INSERT INTO record (pid, doctor_id, date_prepared) VALUES ('" + pid + "','" + doctor_id + "','" + dp + "');";

                System.out.println(sql);
                System.out.println(sql2);
                System.out.println(sql3);

                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);
            } catch (SQLException es) {
                System.out.println("Unable to insert int the databese");
                JOptionPane.showMessageDialog(null, "Μη ολοκληρωμένα πεδία.", "Ανεπιτυχής Δημιουργία", JOptionPane.ERROR_MESSAGE);
                return "ERROR";
            }

            if (stmt == null) {
                JOptionPane.showMessageDialog(null, "Μη ολοκληρωμένα πεδία.", "Ανεπιτυχής Δημιουργία", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Ο φάκελος δημιουρήθηκε επιτυχώς.", "Επιτυχής Δημιουργεία", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return "Created";
    }

    public String edit(String pid, String npid, String fn, String mn, String ls, String job, String dob, String ag, String addr,
            String ct, String pc, String ph, String ht, String ssn, String ms, String it, String sex, String mid, String cur_ill, String past_ill,
            String curr_med, String sur_proc, String imm, String alrg, String fmh, String sh, String preg, String doctor_id,
            String secretary_id, String nurse_id, String dp, String role) {
        if (stmt != null) {
            try {
                String sql1, sql2, sql3;

                System.out.println("pid= " + pid);

                System.out.println("pid= " + npid);

                sql1 = "UPDATE patient SET pid='" + npid + "',first_name='" + fn + "', middle_name='" + mn + "',last_name='" + ls + "', job='" + job + "', date_of_birth='" + dob + "', age='" + ag + "', address='" + addr + "', city='" + ct + "',post_code='" + pc + "',phone='" + ph + "', height='" + ht + "', social_sec_num='" + ssn + "' ,marital_stat='" + ms + "',insurance_type='" + it + "',sex='" + sex + "' WHERE pid ='" + pid + "';";
                sql2 = "UPDATE medical_info set mid='" + mid + "', pid='" + npid + "', curr_illness='" + cur_ill + "', past_illness='" + past_ill + "', curr_medication='" + curr_med + "', surgical_procedure='" + sur_proc + "', immunazation='" + imm + "', allergy='" + alrg + "', family_med_history='" + fmh + "', social_history='" + sh + "', pregnancy='" + preg + "' WHERE pid ='" + pid + "';";
                sql3 = "UPDATE record SET pid='" + npid + "',doctor_id='" + doctor_id + "', date_prepared='" + dp + "' WHERE pid= '" + pid + "';";

                System.out.println(sql1);
                System.out.println(sql2);
                System.out.println(sql3);

                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);

                System.out.println("name is " + fn + " pass is " + ls);
            } catch (SQLException es) {
                JOptionPane.showMessageDialog(null, "Μη ολοκληρωμένα πεδία.", "Ανεπιτυχής αλλαγή", JOptionPane.ERROR_MESSAGE);
                return "ERROR";
            }

            if (stmt == null) {
                JOptionPane.showMessageDialog(null, "NULL", "NULL", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Η αλλαγές σας αποθηκευτηκαν", "Επιτυχής αλλαγή", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return "Saved";
    }

    public String display(String pid, boolean edit, boolean editDoctor, Statement stmt) {

        this.pid = pid;
        this.edit = edit;

        if (stmt != null) {
            try {
                rs = stmt.executeQuery("select  patient.*, medical_info.*,record.* from patient JOIN medical_info  on patient.pid = medical_info.pid JOIN record on patient.pid = record.pid where patient.pid = '" + pid + "';");
            } catch (SQLException es) {
                System.out.println("Unable to create statement ");
                return "ERROR";
            }
        }
        if (rs != null /*& rs1 !=null*/) {
            try {
                while (rs.next()) {
                    pid = rs.getString("pid");
                    jTextField16.setText(pid);
                    jTextField16.setEditable(edit);

                    fn = rs.getString("first_name");
                    jTextField1.setText(fn);
                    jTextField1.setEditable(edit);

                    ls = rs.getString("last_name");
                    jTextField2.setText(ls);
                    jTextField2.setEditable(edit);

                    job = rs.getString("job");
                    jTextField10.setText(job);
                    jTextField10.setEditable(edit);

                    dob = rs.getString("date_of_birth");
                    jTextField4.setText(dob);
                    jTextField4.setEditable(edit);

                    addr = rs.getString("address");
                    jTextField12.setText(addr);
                    jTextField12.setEditable(edit);

                    ct = rs.getString("city");
                    jTextField13.setText(ct);
                    jTextField13.setEditable(edit);

                    pc = rs.getString("post_code");
                    jTextField14.setText(pc);
                    jTextField14.setEditable(edit);

                    ph = rs.getString("phone");
                    jTextField11.setText(ph);
                    jTextField11.setEditable(edit);

                    ssn = rs.getString("social_sec_num");
                    jTextField8.setText(ssn);
                    jTextField8.setEditable(edit);

                    it = rs.getString("insurance_type");
                    jComboBox1.setSelectedItem(it);
                    jComboBox1.setEditable(edit);

                    ms = rs.getString("marital_stat");
                    jComboBox2.setSelectedItem(ms);
                    jComboBox2.setEditable(edit);

                    sex = rs.getString("sex");
                    jComboBox3.setSelectedItem(sex);
                    jComboBox3.setEditable(edit);

                    mid = rs.getString("mid");
                    jTextField7.setText(mid);
                    jTextField7.setEditable(edit);

                    mn = rs.getString("middle_name");
                    jTextField3.setText(mn);
                    jTextField3.setEditable(edit);

                    ag = rs.getString("age");
                    jTextField6.setText(ag);
                    jTextField6.setEditable(edit);

                    ht = rs.getString("height");
                    jTextField5.setText(ht);
                    jTextField5.setEditable(edit);

                    cur_ill = rs.getString("curr_illness");
                    jTextArea2.setText(cur_ill);
                    jTextArea2.setEditable(editDoctor);

                    past_ill = rs.getString("past_illness");
                    jTextArea3.setText(past_ill);
                    jTextArea3.setEditable(editDoctor);

                    curr_med = rs.getString("curr_medication");
                    jTextArea1.setText(curr_med);
                    jTextArea1.setEditable(editDoctor);

                    sur_proc = rs.getString("surgical_procedure");
                    jTextArea4.setText(sur_proc);
                    jTextArea4.setEditable(editDoctor);

                    imm = rs.getString("immunazation");
                    jTextArea5.setText(imm);
                    jTextArea5.setEditable(editDoctor);

                    alrg = rs.getString("allergy");
                    jTextArea6.setText(alrg);
                    jTextArea6.setEditable(editDoctor);

                    fmh = rs.getString("family_med_history");
                    jTextArea7.setText(fmh);
                    jTextArea7.setEditable(editDoctor);

                    sh = rs.getString("social_history");
                    jTextArea8.setText(sh);
                    jTextArea8.setEditable(editDoctor);

                    preg = rs.getString("pregnancy");
                    jTextArea9.setText(preg);
                    jTextArea9.setEditable(editDoctor);

                    dp = rs.getString("date_prepared");
                    jTextField9.setText(dp);
                    jTextField9.setEditable(edit);

                    doctor_id = rs.getString("doctor_id");
                    jTextField15.setText(doctor_id);
                    jTextField15.setEditable(edit);

                    nurse_id = rs.getString("nurse_id");
                    secretary_id = rs.getString("secretary_id");
                }
                System.out.println("NAME IS " + fn + " PASSPORT NUM IS " + pid + " MID IS " + mid);
            } catch (SQLException e) {
                System.out.println("Unable to iterate over resultset");
                return "ERROR";
            }
        }
        return "Displayed";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButtonClean = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButtonClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        jCreateButton = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1200, 900));

        jLabel1.setText("Όνομα : ");

        jLabel2.setText("Μεσαίο όνομα :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Επώνυμο : ");

        jLabel4.setText("Ημ. Γέννησης :");

        jLabel5.setText("Ύψος : ");

        jLabel6.setText("Ηλικία: ");

        jLabel7.setText("Αρ. Φακέλου :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IKA", "ΟΓΑ", "ΤΕΒΕ", "-", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Είδος Ασφάλισης  :");

        jLabel9.setText("Α.Μ.Κ.Α: ");

        jLabel10.setText("Ημ. Εγγραφής");

        jLabel11.setText("Τρέχον Εργασία :");

        jLabel12.setText("Phone No. :");

        jLabel13.setText("Οικ. Κατάσταση :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Παντρεμένος/νη", "Ανύπαντρος/ρη", "Σε σχέση", "-" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Φύλο :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Άντρας", "Γυναίκα", "Άλλο", "-" }));

        jLabel15.setText("Διεύθυνση :");

        jLabel16.setText("Πόλη :");

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jLabel17.setText("Τ.Κ : ");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel18.setText("Πληροφορίες ασθενή");

        jButtonClean.setText("Διαγραφή");
        jButtonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCleanActionPerformed(evt);
            }
        });

        jLabel31.setText("ID Προσωπικού Ιατρού: ");

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jLabel32.setText("Αρ. Διαβατηρίου");

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jButtonClose.setText("Έξοδος");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField7)
                                    .addComponent(jTextField10)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(41, 41, 41)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31)
                            .addComponent(jLabel3)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField14)
                                .addGap(2, 2, 2)))
                        .addGap(363, 363, 363))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(357, 357, 357))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel32)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Πληροφορίες ασθενή", jPanel1);

        jLabel19.setText("Τρέχον φάρμακα/ Συνταγογράφιση : ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel20.setText("Τρέχον ασθένεια : ");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel21.setText("Περασμένες ασθένειες : ");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Ασθένειες και Συνταγογράφιση - Πληροφορείες");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane3))
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(628, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ασθένειες και Συνταγογράφιση", jPanel2);

        jLabel22.setText("Χειρουργικές Επεμβάσεις : ");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel23.setText("Εμβολιασμοί /Εργαστηριακές Εξετάσεις : ");

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        jLabel24.setText("Αλλεργίες : ");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane6.setViewportView(jTextArea6);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Γενικές Πληροφορίες Υγείας");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(579, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Γενικές Πληροφορίες Υγείας", jPanel3);

        jLabel25.setText("Ιστορικό Οικογενείας : ");

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jScrollPane7.setViewportView(jTextArea7);

        jLabel26.setText("Κοινωνικό Ιστορικό : ");

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jScrollPane8.setViewportView(jTextArea8);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Ιστορικό");

        jLabel30.setText("( Για γυναίκες) Εγκυμοσύνες :");

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jScrollPane9.setViewportView(jTextArea9);

        jCreateButton.setText("Δημιουργία");
        jCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCreateButtonActionPerformed(evt);
            }
        });

        jButtonSave.setText("Αποθήκευση");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonHome.setText("Αρχική Σελ.");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel30))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(jCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                    .addComponent(jScrollPane8))
                                .addGap(18, 18, Short.MAX_VALUE)))
                        .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(440, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Ιστορικό Υγείας", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1219, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("INFO");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCreateButtonActionPerformed
        // TODO add your handling code here:
        pid = jTextField16.getText();
        fn = jTextField1.getText();
        mn = jTextField3.getText();
        ls = jTextField2.getText();
        job = jTextField10.getText();
        dob = jTextField4.getText();
        ag = jTextField6.getText();
        addr = jTextField12.getText();
        ct = jTextField13.getText();
        pc = jTextField14.getText();
        ph = jTextField11.getText();
        ht = jTextField5.getText();
        ssn = jTextField8.getText();
        it = (String) jComboBox1.getSelectedItem();
        ms = (String) jComboBox2.getSelectedItem();
        sex = (String) jComboBox3.getSelectedItem();

        mid = jTextField7.getText();
        cur_ill = jTextArea2.getText();
        past_ill = jTextArea3.getText();
        curr_med = jTextArea1.getText();
        sur_proc = jTextArea4.getText();
        imm = jTextArea5.getText();
        alrg = jTextArea6.getText();
        fmh = jTextArea7.getText();
        sh = jTextArea8.getText();
        preg = jTextArea9.getText();

        dp = jTextField9.getText();
        doctor_id = jTextField15.getText();

        Create cr2 = new Create(true, true, false, false, true, role, stmt);
        String Return = cr2.create_file(pid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex, mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg, doctor_id, secretary_id, nurse_id, dp, role);
        System.out.println(Return);

        if (Return == "Created") {
            this.setVisible(false);
            new Home(role, stmt).setVisible(true);
        }
    }//GEN-LAST:event_jCreateButtonActionPerformed

    private void jButtonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCleanActionPerformed
        this.setVisible(false);
        new Create(true, true, false, false, true, role, stmt).setVisible(true);
    }//GEN-LAST:event_jButtonCleanActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:        
        fn = jTextField1.getText();
        mn = jTextField3.getText();
        ls = jTextField2.getText();
        job = jTextField10.getText();
        dob = jTextField4.getText();
        ag = jTextField6.getText();
        addr = jTextField12.getText();
        ct = jTextField13.getText();
        pc = jTextField14.getText();
        ph = jTextField11.getText();
        ht = jTextField5.getText();
        ssn = jTextField8.getText();
        it = (String) jComboBox1.getSelectedItem();
        ms = (String) jComboBox2.getSelectedItem();
        sex = (String) jComboBox3.getSelectedItem();

        mid = jTextField7.getText();
        cur_ill = jTextArea2.getText();
        past_ill = jTextArea3.getText();
        curr_med = jTextArea1.getText();
        sur_proc = jTextArea4.getText();
        imm = jTextArea5.getText();
        alrg = jTextArea6.getText();
        fmh = jTextArea7.getText();
        sh = jTextArea8.getText();
        preg = jTextArea9.getText();

        dp = jTextField9.getText();
        doctor_id = jTextField15.getText();
        npid = jTextField16.getText();

        Create cr1 = new Create(true, true, false, false, true, role, stmt);
        String Return = cr1.edit(pid, npid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex, mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg, doctor_id, secretary_id, nurse_id, dp, role);
        System.out.println(Return);

        if (Return == "Saved") {
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Home(role, stmt).setVisible(true);
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClean;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JButton jCreateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
