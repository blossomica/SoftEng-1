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
 * @author gloria
 */
public class AddUserTest {

    Connection conn = null;
    Statement stmt = null;
    String connectionUrl;
    String connectionUser;
    String connectionPassword = "pass";

    public AddUserTest() {

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

    /**
     * Test of addition method, of class AddUser.
     */
    @Test
    public void testAddition() {
        System.out.println("----------Testing Addition----------");
        String user = "Tom";
        String pass = "Tom";
        String role = "Administrator";
        String roleType = "Nurse";
        AddUser instance = new AddUser(role, stmt);
        String expResult = "Added";
        String result = instance.addition(stmt, user, pass, roleType);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class AddUser.
     */
    @Test
    public void testDelete() {
        System.out.println("----------Testing Delete----------");
        String user = "Tom";
        String pass = "Tom";
        String role = "Administrator";
        String roleType = "Nurse";
        AddUser instance = new AddUser(role, stmt);;
        String expResult = "Deleted";
        String result = instance.delete(stmt, user, pass, roleType);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
