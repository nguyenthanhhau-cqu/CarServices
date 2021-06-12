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
public class Vehicle {
   private String registerNumber;
   private String brand;
   private String model;
   private int year;
   private int kilometers;
   
   public Vehicle() {
       
   }

    public Vehicle(String registerNumber, String brand, String model, int year, int kilometers) {
        this.registerNumber = registerNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
   
   
   
    
}
