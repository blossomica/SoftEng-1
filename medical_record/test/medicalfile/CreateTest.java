/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author haozai
 */
public class CreateTest {

    Connection conn = null;
    Statement stmt = null;
    String connectionUrl;
    String connectionUser;
    String connectionPassword = "pass";
    String role = "Secretary";
    String pid, npid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex;
    String mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg;
    String doctor_id, secretary_id, nurse_id, dp;

    public CreateTest() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();;
        } catch (Exception e) {
            System.out.println("Unable to load Driver");
        }
        //Establish connection using DriverManager 
        try {

            connectionUrl = "jdbc:mysql://localhost:3306/medical_record?";
            connectionUser = "root";

            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
        }
        //if connection is successfully established, create statement
        if (conn != null) {
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.println("Unable to create statement");
            }
        }
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Done!");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate_file() {

        System.out.println("----------Testing Create File----------");
        pid = "777";
        fn = "George";
        mn = "KARAHalien";
        ls = "Karahalios";
        job = "student";
        dob = "7-7-7";
        ag = "21";
        addr = "ston kosmo tou 777";
        ct = "Athens";
        pc = "777";
        ph = "090969696";
        ht = "1.75";
        ssn = "777";
        it = "IKA";
        ms = "Single";
        sex = "Male";

        mid = "777";
        cur_ill = "Too genius";
        past_ill = "Even more genius";
        curr_med = "No medication can cure him, just RIP.";
        sur_proc = "Failed";
        imm = "Immune to logic";
        alrg = "Logic";
        fmh = "Hao knows";
        sh = "Hao knows";
        preg = "Sure";

        dp = "7-7-7";
        doctor_id = "3";
        secretary_id = null;
        nurse_id = null;

        Create instance = new Create(true, true, false, false, true, role, stmt);
        String expResult = "Created";
        String result = instance.create_file(pid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex, mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg, doctor_id, secretary_id, nurse_id, dp, role);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testEditTest() {

        System.out.println("----------Testing Edit----------");
        pid = "777";
        npid = "777";
        fn = "a";
        mn = "a";
        ls = "a";
        job = "a";
        dob = "1-1-1";
        ag = "1";
        addr = "a";
        ct = "a";
        pc = "1";
        ph = "1";
        ht = "1";
        ssn = "1";
        it = "IKA";
        ms = "Married";
        sex = "Other";

        mid = "3";
        cur_ill = "a";
        past_ill = "a";
        curr_med = "a";
        sur_proc = "a";
        imm = "a";
        alrg = "a";
        fmh = "a";
        sh = "a";
        preg = "a";

        dp = "1-1-1";
        doctor_id = "3";
        secretary_id = null;
        nurse_id = null;

        Create instance = new Create(true, true, false, false, true, role, stmt);
        String expResult = "Saved";
        String result = instance.edit(pid, npid, fn, mn, ls, job, dob, ag, addr, ct, pc, ph, ht, ssn, ms, it, sex, mid, cur_ill, past_ill, curr_med, sur_proc, imm, alrg, fmh, sh, preg, doctor_id, secretary_id, nurse_id, dp, role);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplay() {

        System.out.println("----------Testing Display----------");
        pid = "777";
        Create instance = new Create(true, true, false, false, true, role, stmt);
        String expResult = "Displayed";
        String result = instance.display(pid, true, false, stmt);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }
}
