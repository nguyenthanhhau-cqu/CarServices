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
public class ServiceModel implements IServiceModel {

    private static final String URL = "jdbc:mysql://localhost:3306/serviceDB"; //NEW ADJUSTED
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dang123";//YOUR PASSWORD
    private Connection connection = null; // manages connection
    private PreparedStatement selectAllCustomers = null;
    private PreparedStatement SelectCustomerFistName = null; // select query
    private PreparedStatement SelectCustomerPhone = null; // select query
    private PreparedStatement insertNewCustomer = null;
    private PreparedStatement updateCustomer = null;

    // constructor
    public ServiceModel() 
    {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries in the table
            selectAllCustomers =
                    connection.prepareStatement( "SELECT * FROM Customers" );

            // create query that selects entries with a last name
            SelectCustomerFistName = connection.prepareStatement(
                    "SELECT * FROM Customers WHERE FIRSTNAME = ?" );
            // create query that selects entirs with a specific tfn
            SelectCustomerPhone = connection.prepareStatement(
                    "SELECT * FROM Taxpayers WHERE PHONE = ?" );
            // create insert that adds a new entry into the database
            insertNewCustomer = connection.prepareStatement(
                    "INSERT INTO Taxpayers " +
                            "( CustomerID, FIRSTNAME, LASTNAME, PHONE, ADDRESS) " +
                            "VALUES ( ?, ?, ?, ?, ? )" );
            updateCustomer = connection.prepareStatement("UPDATE Customers SET CustomerID = ?, FIRSTNAME = ?,LASTNAME = ?,PHONE= ?, ADDRESS=?" + " WHERE ADDRESS= ?");

                    } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end PersonQueries constructor

public List< Customer > getAllCustomers()
    {
        List< Customer> results = null;
        ResultSet resultSet = null;

        try
        {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllCustomers.executeQuery();
            results = new ArrayList< Customer>();

            while ( resultSet.next() )
            {
                results.add( new Customer(
                        resultSet.getInt( "CustomerID" ),
                        resultSet.getString( "FIRSTNAME" ),
                        resultSet.getString( "LASTNAME" ),
                        resultSet.getString( "PHONE" ),
                        resultSet.getString( "ADDRESS" )
                ) );
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
            {
                resultSet.close();
            } // end try
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method getAllCustomers

    // select taxpayer by name
    public List< Customer > getCustomersByFirtstName( String firstName )
    {
        List< Customer > results = null;
        ResultSet resultSet = null;

        try
        {
            SelectCustomerFistName.setString( 1, firstName ); // specify first name

            // executeQuery returns ResultSet containing matching entries
            resultSet = SelectCustomerFistName.executeQuery();

            results = new ArrayList< Customer >();

            while ( resultSet.next() )
            {
                results.add( new Customer(
                       resultSet.getInt( "CustomerID" ),
                        resultSet.getString( "FIRSTNAME" ),
                        resultSet.getString( "LASTNAME" ),
                        resultSet.getString( "PHONE" ),
                        resultSet.getString( "ADDRESS" )
                ) );
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
            {
                resultSet.close();
            } // end try
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method getByFirstName

  

    public int addCustomer(int customerID, String firstName, String lastName, String phone, String address) {
        return 0;
    }

    // select customer by name
    public List< Customer > getCustomerByPhone( String Phone )
    {
        List< Customer > results = null;
        ResultSet resultSet = null;

        try
        {
            SelectCustomerPhone.setString( 1, Phone ); // specify phone

            // executeQuery returns ResultSet containing matching entries
            resultSet = SelectCustomerPhone.executeQuery();

            results = new ArrayList< Customer >();

            while ( resultSet.next() )
            {
                results.add( new Customer(
                         resultSet.getInt( "CustomerID" ),
                        resultSet.getString( "FIRSTNAME" ),
                        resultSet.getString( "LASTNAME" ),
                        resultSet.getString( "PHONE" ),
                        resultSet.getString( "ADDRESS" )
                ) );
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
            {
                resultSet.close();
            } // end try
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method getByTFN
    // add an entry
    public int AddCustomer(int customerID, String firstName, String lastName, String phone, String address)
    {
        int result = 0;

        // set parameters, then execute insertNewTaxpayer
        try
        {
            insertNewCustomer.setInt( 1, customerID );
            insertNewCustomer.setString( 2, firstName );
            insertNewCustomer.setString( 3, lastName );
            insertNewCustomer.setString( 4, phone );
            insertNewCustomer.setString( 5, address );  

            // insert the new entry; returns # of rows updated
            result = insertNewCustomer.executeUpdate();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method addCustomer
public int UpdateCustomer(int customerID, String firstName, String lastName, String phone, String address)
    {
        int result = 0;

        // set parameters, then execute insertNewCustomer
        try
        {
            updateCustomer.setInt(1 , customerID );
            updateCustomer.setString( 2, firstName );
            updateCustomer.setString( 3, lastName );
            updateCustomer.setString( 4, phone );
            updateCustomer.setString( 5, address );

            // insert the new entry; returns # of rows updated
            result = updateCustomer.executeUpdate();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method UpdateTaxpayer



    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    @Override
    public List<Customer> getCustomerByFirstName(int queryByFirstNameTextField) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int UpdateTaxpayer(int customerIDTextField, String firstNameTextField, String lastNameTextField, String phoneTextField, String addressTextField) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
} // end class PersonQueries
