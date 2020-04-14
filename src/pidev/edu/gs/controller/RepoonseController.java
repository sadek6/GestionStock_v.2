/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Reponse;
import pidev.edu.gs.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RepoonseController implements Initializable {

    @FXML
    private TextField repp;
    @FXML
    private DatePicker dpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajte(ActionEvent event) {
        System.out.println("ok");
        ReponseService se = new ReponseService();
            //Reponse dm =cbrep.getSelectionModel().getSelectedItem();
            //Date datecons = new Date( dpdate.getValue().getYear()-1900 , dpdate.getValue().getMonth()-1, dpdate.getValue().getDayOfMonth());
       
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());
            se.AjouterRep(new Reponse( repp.getText(),datecons ));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Reponse ajoutée !");
            alert.showAndWait();
    }
    
}
