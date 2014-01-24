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

/**
 *
 * @author gloria
 */
public class MedicalFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        Statement stmt = null;
        String connectionUrl;
        String connectionUser;
        String connectionPassword = "pass";

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
        Authentication a1 = new Authentication(stmt, conn);
        a1.setVisible(true);
    }
}
