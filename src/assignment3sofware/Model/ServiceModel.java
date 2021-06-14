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
    private static final String PASSWORD = "Dang123";//YOUR PASSWORD
    private Connection connection = null; // manages connection
    private PreparedStatement searchCustomerAndVehicleByName = null; // select query
    private PreparedStatement searchCustomerAndVehicleByPhone = null; // select query
    private PreparedStatement insertService = null; // Insert a new service
    private PreparedStatement getAllVehicle = null;
    private String lastInsertedVehicleID = "";
    private PreparedStatement cancelABooking = null;
    private PreparedStatement searchBookingByVehicleNumber = null;
    private PreparedStatement updateCustomerDetail = null;
    private PreparedStatement addVehicletoCustomer = null; // 
    private PreparedStatement getAllCustomer =null;
    private int lastInsertedCustomerID=0; //latest customer ID after inserted a new cusoter
    private PreparedStatement selectAllCustomers = null;
    private PreparedStatement insertNewCustomer = null;// create a new customer 
    private PreparedStatement statistic = null;  // find minimum and maximum and avg of prices
    private PreparedStatement vehicleServeByBrand = null; //List vehicle by brand
    private PreparedStatement displayService = null; //display all services


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
             statistic = connection.prepareStatement("Select Min(price) as Min, Max(price) as Max, AVG(price) as AVG   from services");
            displayService = connection.prepareStatement("SELECT * FROM Services As S left join Vehicles As V on S.VehicleNumber = V.VehicleNumber left join Customers as C on V.CustomerID = C.CustomerID ORDER BY price");
            vehicleServeByBrand = connection.prepareStatement("Select b.VehicleBrand, count(*) as Counts From vehicles as b, customers as c where c.customerID = b.CustomerID Group BY B.VehicleBrand");
            
            cancelABooking = connection.prepareStatement("DELETE FROM SERVICES WHERE SERVICEID =? ");
            //search a booking 
            searchBookingByVehicleNumber = connection.prepareStatement("SELECT * FROM Services As S left join Vehicles As V on S.VehicleNumber = V.VehicleNumber left join Customers as C on V.CustomerID = C.CustomerID Where S.VehicleNumber = ?");
            //Update customer detail
            updateCustomerDetail = connection.prepareStatement("UPDATE Customers SET FIRSTNAME =?, LASTNAME =?, PHONE = ?, ADDRESS = ? WHERE CUSTOMERID = ?");
            insertNewCustomer = connection.prepareStatement(
                    "INSERT INTO Customers " +
                            "(FIRSTNAME, LASTNAME, PHONE, ADDRESS) " +
                            "VALUES ( ?, ?, ?, ? )" );
            getAllCustomer = connection.prepareStatement("SELECT * FROM Customers");
         
            addVehicletoCustomer = connection.prepareStatement(
                            "INSERT INTO Vehicles "
                    + "(VehicleNumber,VehicleBrand,VehicleModel,VehicleYear,VehicleKilometers, CustomerID)" + "VALUES (?,?, ?, ?, ?,?)");   
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
     public int getLastInsertedCustomerID()
    {
		getAllCustomer();
		return lastInsertedCustomerID;
	}

    //select all of the customes in the database-
    public void getAllCustomer()
    {
        ResultSet resultSet = null;

        try {
            resultSet = getAllCustomer.executeQuery();//execute query

            int customerID=0;

            while (resultSet.next()) {
              customerID=resultSet.getInt(1);
            }

            lastInsertedCustomerID=customerID;// latest customerID

            resultSet.close();
        } // end try
        catch (SQLException e)
        {
           e.printStackTrace();
        }

    } // end method
    @Override
    public int addCustomer(  String firstName, String lastName, String phone, String address)
    {
      int result=-1;

        // set parameters, then execute insertNewTaxpayer
        try
        {
            
            insertNewCustomer.setString( 1, firstName );
            insertNewCustomer.setString( 2, lastName );
            insertNewCustomer.setString( 3, phone );
            insertNewCustomer.setString( 4, address );  

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

 @Override
    public List<Double> statistic() {
        double Min=0;
        double Max=0;
        double Avg=0;
        ResultSet resultSet = null;
        List<Double> result = new ArrayList<>();
        try {
           resultSet = statistic.executeQuery();//execute query
           while (resultSet.next()) {
               Min = resultSet.getDouble(1);
               Max = resultSet.getDouble(2);
               Avg = resultSet.getDouble(3);
               
               result.add(Min);
               result.add(Avg);
               result.add(Max);
           }
        } catch (SQLException e) {//handle error
            e.printStackTrace();
            
        }
        return result;
    }

    
   

    @Override
    public int addVehicleToCustomer(String VehicleNumber, String VehicleBrand, String VehicleModel, int VehicleYear, int VehicleKilometers, int CustomerID) {
        	 {
			int result=-1;
			try{
			    addVehicletoCustomer.setString(1, VehicleNumber);
			    addVehicletoCustomer.setString(2,VehicleBrand);
			    addVehicletoCustomer.setString(3,VehicleModel);
                            addVehicletoCustomer.setInt(4, VehicleYear);//fk
                            addVehicletoCustomer.setInt(5,VehicleKilometers);
                            addVehicletoCustomer.setInt(6,CustomerID);

                            result = addVehicletoCustomer.executeUpdate();
	        } catch (SQLException e) {//handle error
	            e.printStackTrace();
	        } // end catch

			return result;
	 }// end of the method
    }
    
    @Override
    public List<String> vehicleServeByBrand() {
		    List<String> result=new ArrayList<>();
	 		ResultSet resultSet = null;

	 	    try{
	              resultSet = vehicleServeByBrand.executeQuery();//execute query

	              while (resultSet.next())
	              {
	 				  String VehicleBrand = resultSet.getString("VehicleBrand");
	 				  int Counts=  resultSet.getInt("Counts");

	                  String s=VehicleBrand+"\t"+Counts;
                          
	                  result.add(s);
                          
	 		     } // end while
	 	      } catch (SQLException e) {
	 	            e.printStackTrace();
	 	      } // end catch
	 	      return result;             
    }

    @Override
    public List<Services> displayServices() {
        ResultSet resultSet = null;
        List<Services> result = new ArrayList<>();

        try {
            resultSet = displayService.executeQuery();//execute query
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
                
                int serviceID = resultSet.getInt("serviceID");//get customer id
                String serviceDescription = resultSet.getString("serviceDescription");
                String serviceDate = resultSet.getString("serviceDate");
                double price = resultSet.getDouble("price");
                String VehicleNumber = resultSet.getString("VehicleNumber");   
                
                
      
                Services s = new Services(serviceID,serviceDescription,serviceDate,price,VehicleNumber,v);
                result.add(s);
             }
    }catch (SQLException e) {//handle error
            e.printStackTrace(); 
          
    }   
       return result;
}

    
} // end class PersonQueries
