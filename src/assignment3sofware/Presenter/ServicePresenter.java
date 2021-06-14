package assignment3sofware.Presenter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import assignment3sofware.Model.Customer;
import assignment3sofware.Model.IServiceModel;
import assignment3sofware.Model.Services;
import assignment3sofware.Model.Vehicle;
import assignment3sofware.View.IServicesView;
import java.security.Provider.Service;
import java.util.List;

/**
 *
 * @author ilove
 */
public class ServicePresenter {

    IServicesView view;
    IServiceModel model;

    List<Vehicle> results;// list
    List<Services> serviceResult;
    int currentEntryIndex;
    int numberOfEntries;
    int currentServiceEntryIndex;
    int numberOfServiceEntries;
    Services currentServiceEntry;
    Vehicle currentEntry;

    //constructor
    public ServicePresenter(IServicesView isv, IServiceModel ism) {
        view = isv;
        model = ism;
        currentEntryIndex = 0;
        numberOfEntries = 0;
        results = null;//new ArrayList<>();
        currentEntry = new Vehicle();
    }
    // handles call when previousButton in Vehicle panel is clicked

    public void showPrevious() {
        currentEntryIndex--;

        if (currentEntryIndex < 0) {
            currentEntryIndex = numberOfEntries - 1;
        }

        currentEntry = results.get(currentEntryIndex);
        view.displayVehicleEntries(currentEntry);
        view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
    }

    // handles call when nextButton in Vehicl panel is clicked
    public void showNext() {
        currentEntryIndex++;

        if (currentEntryIndex >= numberOfEntries) {
            currentEntryIndex = 0;
        }

        currentEntry = results.get(currentEntryIndex);
        view.displayVehicleEntries(currentEntry);
        view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
    }

    //handles when next button in service panel is clicked
    public void showNextService() {
        currentServiceEntryIndex++;
        if (currentServiceEntryIndex >= numberOfServiceEntries) {
            currentServiceEntryIndex = 0;
        }

        currentServiceEntry = serviceResult.get(currentServiceEntryIndex);
        view.displayServiceEntries(currentServiceEntry);
        view.displayMaxAndCurrentServiceIndex(numberOfServiceEntries, currentServiceEntryIndex);
    }

    //handles when previouse button in service panel is clicked
    public void showPreviousService() {
        currentServiceEntryIndex--;
        if (currentServiceEntryIndex < 0) {
            currentServiceEntryIndex = numberOfServiceEntries - 1;
        }
        currentServiceEntry = serviceResult.get(currentServiceEntryIndex);
        view.displayServiceEntries(currentServiceEntry);
        view.displayMaxAndCurrentServiceIndex(numberOfServiceEntries, currentServiceEntryIndex);
    }

    // handles call search by phone
    public void performQueryByPhone(String phone) {

        results = model.searchCustomerAndVehicleByPhone(phone);

        numberOfEntries = results.size();
        if (numberOfEntries != 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);

            Customer c = currentEntry.getC();

            if (currentEntry.getRegisterNumber() == null && numberOfEntries != 0) {
                view.displayDataTextArea("Customer does not have any vehicle in the system");
                view.setLatestCustomerID(c.getCustomerID());
                view.setCustomerPhone(c.getPhone());
                return;
            }
            view.displayVehicleEntries(currentEntry);
            view.displayCustomerEntry(c);
            view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
            view.setBrowsing(true);
        } else {
            view.displayDataTextArea("Customer Not found!");
        }
    }

    //handle method call- search by name
    public void performQueryByName(String first, String last) {

        results = model.searchCustomerAndVehicleByName(first, last);

        numberOfEntries = results.size();
        if (numberOfEntries != 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);

            Customer c = currentEntry.getC();

            if (currentEntry.getRegisterNumber() == null && numberOfEntries != 0) {
                view.displayDataTextArea("Customer does not have any vehicle in the system");
                view.setLatestCustomerID(c.getCustomerID());
                view.setCustomerPhone(c.getPhone());
                return;
            }
            view.displayVehicleEntries(currentEntry);
            view.displayCustomerEntry(c);
            view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
            view.setBrowsing(true);
        } else {
            view.displayDataTextArea("Customer Not found!");
        }
    }

    public void cancelBooking(String serviceID) {
        int result = model.cancelABooking(serviceID);

        if (result == 1) {
            view.displayDataTextArea("Booking canceled");
        } else {
            view.displayDataTextArea("Cancel booking failed!");
        }
    }
    //add a new service 
    public void insertService(String serviceDescription, String serviceDate, double price, String VehicleNumber) {

        int result = model.insertService(serviceDescription, serviceDate, price, VehicleNumber);

        if (result == 1) {
            view.displayDataTextArea("Service added !");
        } else {
            view.displayDataTextArea("Service not added");
        }
    }//end
    
    //searchBooking by Vehicle Number
    public void searchBookingByVehicleNumber(String serviceID) {
        serviceResult = model.searchServiclesByVehicleNum(serviceID);
        numberOfServiceEntries = serviceResult.size();
        if (numberOfServiceEntries == 0) {
                view.displayDataTextArea("This vehicle has not got any booking or vehicle not found!");
                return;
            }
        if(numberOfServiceEntries != 0){
            currentServiceEntryIndex = 0;
            currentServiceEntry = serviceResult.get(currentServiceEntryIndex);
            view.displayServiceEntries(currentServiceEntry);
            view.displayMaxAndCurrentServiceIndex(numberOfServiceEntries, currentServiceEntryIndex);
            view.setBrowsing(true);          
        }
        }
    
    public void updateCustomerDetail (String first, String last, String phone, String address, int customerID){
        int results = model.updateCustomerDetail(first, last, phone, address, customerID);
        
        if (results ==1){
            view.displayDataTextArea("Customer's detail updated");
        }
        else{
            view.displayDataTextArea("Customer's detail not updated");
        }
    }
    }
