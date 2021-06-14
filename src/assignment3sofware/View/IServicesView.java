/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.View;

import assignment3sofware.Presenter.ServicePresenter;
import assignment3sofware.Model.Customer;
import assignment3sofware.Model.Services;
import assignment3sofware.Model.Vehicle;

/**
 *
 * @author ilove
 */
public interface IServicesView {
    public void bind(ServicePresenter p);

    public void displayCustomerEntry(Customer c);
    public void displayVehicleEntries(Vehicle v);
    public void displayServiceEntries (Services s);
    public void displayDataTextArea(String s);

    public void setLatestCustomerID(int id);
    public void setCustomerPhone(String p);

    public void setBrowsing(boolean flag);
    public void displayMaxAndCurrentIndex(int m, int c);
    
    public void setLatestVehicleID(String id);
    
    public void displayMaxAndCurrentServiceIndex(int sm, int sc);
    void setCustomerIDTextField(int a);
    int getCustomerIDTextField();
    void setFirstNameTextField(String s);
    String getFirstNameTextField();
    void setLastNameTextField(String s);
    String getLastNameTextField(); 
    void setPhoneTextField(String s);
    String getPhoneTextField();
    void setAddressTextField(String s);
    String getAddressTextField();
    void setVehicleNumTextField(String a);
    String getVehicleNumTextField();
     void setVehicleBrandTextField(String a);
    String getVehicleBrandTextField();
     void setVehicleModelTextField(String a);
    String getVehicleModelTextField();
     void setVehicleYearTextField(int a);
    int getVehicleYearTextField();
      void setKilometersTextField(int a);
    int getKilometersTextField();
}
