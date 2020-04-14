/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.gs.entities.Reclamation;
import pidev.edu.gs.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yanisinfo
 */
public class AfficherReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Reclamation> table;

    @FXML
    private TableColumn<Reclamation, String> coldesc;

    @FXML
    private TableColumn<Reclamation, String> coldate;

    @FXML
    private TableColumn<Reclamation, String> coletat;

    @FXML
    private TableColumn<Reclamation, String> coltype;

    @FXML
    private TableColumn<Reclamation, String> colreponse;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewReclamation();
    } 
        public void viewReclamation(){
    ReclamationService se = new ReclamationService();
    table.setItems((ObservableList<Reclamation>) se.afficher());
    coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
    colreponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));

    }
           void supprimer(ActionEvent event) {
    Reclamation m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ReclamationService se =new ReclamationService();
       Reclamation e =new Reclamation(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette reclamation");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewReclamation();

      }
    }
}
    
}
