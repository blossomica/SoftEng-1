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
public class AuthenticationTest {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String connectionUrl;
    String connectionUser;
    String connectionPassword = "pass";

    public AuthenticationTest() {

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testLogIn() {

        System.out.println("----------Testing Log In----------");
        String username = "secrAnna";
        String password = "secAnna";
        String role = "Secretary";
        Authentication instance = new Authentication(stmt, conn);
        String expResult = "Log In";
        String result = instance.login(stmt, role, username, password);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testExit() {

        System.out.println("----------Testing Exit----------");
        Authentication instance = new Authentication(stmt, conn);
        String expResult = "Exit";
        String result = instance.exit(stmt, rs, conn);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }
}
