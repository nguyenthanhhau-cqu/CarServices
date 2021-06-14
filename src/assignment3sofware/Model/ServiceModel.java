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

    private static final String URL = "jdbc:mysql://localhost:3306/carservicedb"; //NEW ADJUSTED
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hoanduong05";//YOUR PASSWORD
    private Connection connection = null; // manages connection
    private PreparedStatement searchCustomerAndVehicleByName = null; // select query
    private PreparedStatement searchCustomerAndVehicleByPhone = null; // select query
    private PreparedStatement insertService = null; // Insert a new service
    private PreparedStatement getAllVehicle = null;
    private String lastInsertedVehicleID = "";
    private PreparedStatement cancelABooking = null;
    private PreparedStatement searchBookingByVehicleNumber = null;
    private PreparedStatement updateCustomerDetail = null;

    // constructor
    public ServiceModel() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            getAllVehicle = connection.prepareStatement("SELECT * FROM Vehicles");
            //search customer and vehicle by name sql statement
            searchCustomerAndVehicleByName = connection.prepareStatement("SELECT * FROM Customers As C left Join Vehicles As V on C.CustomerID=V.CustomerID where FIRSTNAME=? and LASTNAME=?");
            //search customer and vehicle by phone sql statement
            searchCustomerAndVehicleByPhone = connection.prepareStatement("SELECT * FROM Customers As C left Join Vehicles As V on C.CustomerID=V.CustomerID where Phone =?");
            //insert a service to a vehicle
            insertService = connection.prepareStatement("INSERT INTO services "
                    + "(serviceDescription,servicedate,price,vehicleNumber)" + "VALUES (?,?,?,?)");
            //canle a booking
            cancelABooking = connection.prepareStatement("DELETE FROM SERVICES WHERE SERVICEID =? ");
            //search a booking 
            searchBookingByVehicleNumber = connection.prepareStatement("SELECT * FROM Services As S left join Vehicles As V on S.VehicleNumber = V.VehicleNumber left join Customers as C on V.CustomerID = C.CustomerID Where S.VehicleNumber = ?");
            //Update customer detail
            updateCustomerDetail = connection.prepareStatement("UPDATE Customers SET FIRSTNAME =?, LASTNAME =?, PHONE = ?, ADDRESS = ? WHERE CUSTOMERID = ?");
        } // end try  
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end PersonQueries constructor

    public List<Vehicle> searchCustomerAndVehicleByName(String first, String last) {
        ResultSet resultSet = null;
        List<Vehicle> result = new ArrayList<>();

        try {
            searchCustomerAndVehicleByName.setString(1, first);
            searchCustomerAndVehicleByName.setString(2, last);

            resultSet = searchCustomerAndVehicleByName.executeQuery();//execute query

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CUSTOMERID");//get customer id
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");

                Customer c = new Customer(customerID, firstName, lastName, phone, address);

                String vehicleNumber = resultSet.getString("VehicleNumber");
                String vehicleModel = resultSet.getString("VehicleModel");
                String vehicleBrand = resultSet.getString("VehicleBrand");
                int vehicleYear = resultSet.getInt("VehicleYear");
                int vehicleKm = resultSet.getInt("VehicleKilometers");

                Vehicle v = new Vehicle(vehicleNumber, vehicleModel, vehicleBrand, vehicleYear, vehicleKm, c);
                result.add(v);
            } // end while

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch
        return result;
    }

    public List<Vehicle> searchCustomerAndVehicleByPhone(String p) {
        ResultSet resultSet = null;
        List<Vehicle> result = new ArrayList<>();

        try {
            searchCustomerAndVehicleByPhone.setString(1, p);
            resultSet = searchCustomerAndVehicleByPhone.executeQuery();//execute query

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CUSTOMERID");//get customer id
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");

                Customer c = new Customer(customerID, firstName, lastName, phone, address);

                String vehicleNumber = resultSet.getString("VehicleNumber");
                String vehicleModel = resultSet.getString("VehicleModel");
                String vehicleBrand = resultSet.getString("VehicleBrand");
                int vehicleYear = resultSet.getInt("VehicleYear");
                int vehicleKm = resultSet.getInt("VehicleKilometers");

                Vehicle v = new Vehicle(vehicleNumber, vehicleModel, vehicleBrand, vehicleYear, vehicleKm, c);
                result.add(v);

            } // end while

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch

        return result;
    }

    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    @Override
    public int insertService(String serviceDescription, String serviceDate, double price, String VehicleNumber) {
        int result = -1;
        try {
            insertService.setString(1, serviceDescription);
            insertService.setString(2, serviceDate);
            insertService.setDouble(3, price);
            insertService.setString(4, VehicleNumber);

            result = insertService.executeUpdate();
        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch

        return result;
    }

    ;

     public int cancelABooking(String serviceID) {
        ResultSet resultSet = null;
        int result = 0;
        try {
            cancelABooking.setString(1, serviceID);
            result = cancelABooking.executeUpdate();
        } // end while
        catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch
        return result;
    }

    @Override
    public List<Services> searchServiclesByVehicleNum(String vehicleNum) {
        ResultSet resultSet = null;
        List<Services> result = new ArrayList<>();

        try {
            searchBookingByVehicleNumber.setString(1, vehicleNum);
            resultSet = searchBookingByVehicleNumber.executeQuery();//execute query

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CUSTOMERID");//get customer id
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");

                Customer c = new Customer(customerID, firstName, lastName, phone, address);

                String vehicleNumber = resultSet.getString("VehicleNumber");
                String vehicleModel = resultSet.getString("VehicleModel");
                String vehicleBrand = resultSet.getString("VehicleBrand");
                int vehicleYear = resultSet.getInt("VehicleYear");
                int vehicleKm = resultSet.getInt("VehicleKilometers");

                Vehicle v = new Vehicle(vehicleNumber, vehicleModel, vehicleBrand, vehicleYear, vehicleKm, c);

                int serviceNumber = resultSet.getInt("serviceID");
                String serviceDes = resultSet.getString("serviceDescription");
                String serviceDate = resultSet.getString("serviceDate");
                double price = resultSet.getDouble("price");
                String serviceVehicleNum = resultSet.getString("VehicleNumber");

                Services s = new Services(serviceNumber, serviceDes, serviceDate, price, serviceVehicleNum, v);

                result.add(s);
            } // end while

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch
        return result;
    }

    @Override
    public int updateCustomerDetail(String first, String last, String phone, String address, int customerID ) {
        int result = 0;
        try {

            updateCustomerDetail.setString(1, first);
            updateCustomerDetail.setString(2, last);
            updateCustomerDetail.setString(3, phone);
            updateCustomerDetail.setString(4, address);
            updateCustomerDetail.setInt(5, customerID);
            result = updateCustomerDetail.executeUpdate();

        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch
        return result;
    }

} // end class PersonQueries
