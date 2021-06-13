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
}
