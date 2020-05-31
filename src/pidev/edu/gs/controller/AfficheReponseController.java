/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.gs.entities.Reclamation;
import pidev.edu.gs.entities.Reponse;
import pidev.edu.gs.services.ReclamationService;
import pidev.edu.gs.services.ReponseService;

/**
 *
 * @author Acer
 */
public class AfficheReponseController implements Initializable {

   
     @FXML
    private TableView<Reponse> table;
        
    @FXML
    private TableColumn<Reponse, Date> coldate;

  
    @FXML
    private TableColumn<Reponse, String> colrep;

         ObservableList<Reponse> data;
      
         
         ReclamationService req=new ReclamationService();


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewReponse();
        }
    
        void viewReponse(){
         ReponseService se = new ReponseService();
    table.setItems((ObservableList<Reponse>) se.afficher());
    coldate.setCellValueFactory(new PropertyValueFactory<>("dateReponse"));
    colrep.setCellValueFactory(new PropertyValueFactory<>("reponse"));

    
        
         
    }    
    @FXML
    void supprimer(ActionEvent event) {
    Reponse m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(Alert.AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ReponseService se =new ReponseService();
       Reponse e =new Reponse(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette reponse");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewReponse();

      }
    }
    }
    }
    

  

