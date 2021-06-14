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
public class Services {
    private int serviceID;
    private String serviceDescription;
    private String serviceDate;
    private double price;
    private Vehicle vehicleNum;
    Vehicle v;
    
    public Services() {
        
    }

    public Services(int serviceNumber, String serviceDescription, String serviceDate, double price, String VehicleNum, Vehicle v1) {
        this.serviceID = serviceNumber;
        this.serviceDescription = serviceDescription;
        this.serviceDate = serviceDate;
        this.price = price;
        this.vehicleNum = vehicleNum;
        v = v1;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Vehicle getV() {
        return v;
    }

    public void setV(Vehicle v1) {
        v =v1;
    }

    public int getServiceNumber() {
        return serviceID;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceID = serviceNumber;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Vehicle vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
    
    
    
    
    
    
    
}
