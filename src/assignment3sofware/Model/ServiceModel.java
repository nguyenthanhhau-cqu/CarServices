/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.Model;

/**
 *
 * @author andre
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andre
 */
public class ServiceModel implements IServericeModel {

    private static final String URL = "jdbc:mysql://localhost:3306/serviceDB"; //NEW ADJUSTED
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Anhdasai123";//YOUR PASSWORD
    private Connection connection = null; // manages connection
    private PreparedStatement SelectCustomerFistName = null; // select query
    private PreparedStatement SelectCustomerPhone = null; // select query


    // constructor
    public ServiceModel() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries in the AddressBook
            //SelectCustomerFistName =
                   // connection.prepareStatement("SELECT * FROM taxpayers");

            // create query that selects entries with a specific last name
            //SelectCustomerPhone = connection.prepareStatement(
                    //SELECT * FROM taxpayers WHERE LastName = ?");

            //Create update current entry into database


            // create insert that adds a new entry into the database
            
        } // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end PersonQueries constructor




    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close
    
} // end class PersonQueries
