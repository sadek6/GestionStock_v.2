/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;
/**
 *
 * @author Acer
 */

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import pidev.edu.gs.entities.Reclamation;
import pidev.edu.gs.entities.Reponse;
import pidev.edu.gs.services.ReclamationService;

public class ReclamationController implements Initializable {

    @FXML
    private TextArea desc;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<Reponse> cbrep;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajte(ActionEvent event) {
                if(!desc.getText().equals("") ){
            ReclamationService se = new ReclamationService();
            Reponse dm =cbrep.getSelectionModel().getSelectedItem();
            //Date datecons = new Date( dpdate.getValue().getYear()-1900 , dpdate.getValue().getMonth()-1, dpdate.getValue().getDayOfMonth());
       
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());
            se.ajouter2(new Reclamation(desc.getText(),datecons,etat.getSelectionModel().getSelectedItem(),"e","e"));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Reclamation ajouté !");
            alert.showAndWait();
              /* nomtxt.setText("");
               agetxt.setText("");*/
               //viewEnfant();
             //  clearFields();

        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }
    }

   
}
