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
    private Vehicle VehicleNum;
    
    public Services() {
        
    }

    public Services(int serviceNumber, String serviceDescription, String serviceDate, double price, Vehicle VehicleNum) {
        this.serviceID = serviceNumber;
        this.serviceDescription = serviceDescription;
        this.serviceDate = serviceDate;
        this.price = price;
        this.VehicleNum = VehicleNum;
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
        return VehicleNum;
    }

    public void setVehicleNum(Vehicle VehicleNum) {
        this.VehicleNum = VehicleNum;
    }
    
    
    
    
    
    
    
}
