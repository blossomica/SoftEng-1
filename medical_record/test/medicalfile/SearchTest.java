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
public class SearchTest {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String connectionUrl;
    String connectionUser;
    String connectionPassword = "pass";
    String role = "Doctor";

    public SearchTest() {

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
    public void testFind() {
        System.out.println("----------Testing Find----------");
        String pid = "777";
        Search instance = new Search(role, stmt);
        String expResult = "Found";
        String result = instance.find(stmt, "Αριθμό Διαβατηρίου", "1");
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() {
        System.out.println("----------Testing Delete----------");
        String pid = "777";
        Search instance = new Search(role, stmt);
        String expResult = "Deleted";
        String result = instance.delete(stmt, pid);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testShowTable() {
        System.out.println("----------Testing Show Table----------");
        Search instance = new Search(role, stmt);
        String expResult = "Showed";
        String result = instance.showTable(stmt);
        System.out.println("Expected Result: " + expResult + " --- Actual Result: " + result);
        assertEquals(expResult, result);
    }
}
