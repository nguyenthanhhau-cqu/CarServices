package assignment3sofware.Presenter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import assignment3sofware.Model.Customer;
import assignment3sofware.Model.IServiceModel;
import assignment3sofware.Model.Vehicle;
import assignment3sofware.View.IServicesView;
import java.util.List;

/**
 *
 * @author ilove
 */
public class ServicePresenter {
    IServicesView view;
    IServiceModel model;

    List<Vehicle> results;// list
    int currentEntryIndex;
    int numberOfEntries;
    Vehicle currentEntry;

    //constructor
    public ServicePresenter(IServicesView isv, IServiceModel ism) {
	    view = isv;
	    model = ism;
	    currentEntryIndex = 0;
	    numberOfEntries = 0;
	    results = null;//new ArrayList<>();
	    currentEntry = null;
	}
     // handles call when previousButton is clicked
    public void showPrevious() {
        currentEntryIndex--;

        if (currentEntryIndex < 0) {
            currentEntryIndex = numberOfEntries - 1;
        }

        currentEntry = results.get(currentEntryIndex);
        view.displayVehicleEntries(currentEntry);
        view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
    }

    // handles call when nextButton is clicked
    public void showNext() {
        currentEntryIndex++;

        if (currentEntryIndex >= numberOfEntries) {
            currentEntryIndex = 0;
        }

        currentEntry = results.get(currentEntryIndex);
        view.displayVehicleEntries(currentEntry);
        view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
    }

    // handles call search by phone
	   public void performQueryByPhone(String phone) {

	      results = model.searchCustomerAndVehicleByPhone(phone);

	      numberOfEntries = results.size();
	      if ( numberOfEntries != 0 ) {
	         currentEntryIndex = 0;
	         currentEntry = results.get( currentEntryIndex );

	         Customer c=currentEntry.getC();

             if(currentEntry.getRegisterNumber()==null && numberOfEntries!=0)
             {
                 view.displayDataTextArea("The customer has not placed an order yet");
                 view.setLatestCustomerID(c.getCustomerID());
                 view.setCustomerPhone(c.getPhone());
                 return;
             }
	         view.displayVehicleEntries(currentEntry);
             view.displayCustomerEntry(c);
             view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
	         view.setBrowsing(true);
	      }
	      else
	        view.displayDataTextArea("customer Not found");
	   }
           public void insertService (String serviceDescription,String serviceDate,double price,String VehicleNumber) {

	      int result = model.insertService(serviceDescription,serviceDate,price,VehicleNumber);

	      if ( result == 1 )
          {
	          view.displayDataTextArea("Service added");
          }
	      else
	          view.displayDataTextArea("Service not added");
	   }//end

	   //add order to a customer

}
