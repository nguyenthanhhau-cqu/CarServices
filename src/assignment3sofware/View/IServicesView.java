/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.View;

import assignment3sofware.Presenter.ServicePresenter;
import assignment3sofware.Model.Customer;
import assignment3sofware.Model.Vehicle;

/**
 *
 * @author ilove
 */
public interface IServicesView {
    public void bind(ServicePresenter p);

    public void displayCustomerEntry(Customer c);
    public void displayOrderEntries(Vehicle v);
    public void displayDataTextArea(String s);

    public void setLatestCustomerID(int id);
    public void setCustomerPhone(String p);

    public void setBrowsing(boolean flag);
    public void displayMaxAndCurrentIndex(int m, int c);
    
    public void setLatestVehicleID(String id);

}
