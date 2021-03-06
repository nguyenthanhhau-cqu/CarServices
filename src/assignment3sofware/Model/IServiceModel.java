/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.Model;

import java.util.List;

/**
 *
 * @author ilove
 */
public interface IServiceModel {
    List <Vehicle> searchCustomerAndVehicleByName (String first, String last); 
    List <Vehicle> searchCustomerAndVehicleByPhone (String p);
    List <Services> searchServiclesByVehicleNum (String vehicleNum);
    public int insertService(String serviceDescription,String serviceDate,double price,String VehicleNumber);
    int cancelABooking(String serviceID);
    public int updateCustomerDetail(String first, String last, String phone, String address, int customerID);
    public int addCustomer( String firstNameTextField, String lastNameTextField, String phoneTextField, String addressTextField);
   
    public int addVehicleToCustomer(String VehicleNumber,String VehicleBrand,String VehicleModel, int VehicleYear, int VehicleKilometers, int CustomerID);
    public int getLastInsertedCustomerID();
   
    List<Double> statistic();
    List<String> vehicleServeByBrand();
    List<Services> displayServices();
    public void close();
}
