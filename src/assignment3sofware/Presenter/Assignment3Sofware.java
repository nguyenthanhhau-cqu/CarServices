/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3sofware.Presenter;

import assignment3sofware.Model.ServiceModel;
import assignment3sofware.View.vsmsView;

/**
 *
 * @author andre
 */
public class Assignment3Sofware {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        ServiceModel T = new ServiceModel();
        vsmsView pv = new vsmsView();
        ServicePresenter pp = new ServicePresenter(pv, T);
        pv.bind(pp);
        pv.setVisible(true);
        pv.setResizable(false);
        
    }
    
}
