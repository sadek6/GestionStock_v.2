/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.gs.entities.Reclamation;
import pidev.edu.gs.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yanisinfo
 */
public class AfficherReclamationController implements Initializable {
 public ObservableList<Reclamation> data=FXCollections.observableArrayList();
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TextField filterField;
  
  

    @FXML
    private TableColumn<Reclamation, String> coldesc;

    @FXML
    private TableColumn<Reclamation, String> coldate;

    @FXML
    private TableColumn<Reclamation, String> coletat;

    @FXML
    private TableColumn<Reclamation, String> coltype;
    @FXML
    private TableColumn<Reclamation, Integer> coliduser;

    @FXML
    private TableColumn<Reclamation, String> colreponse;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewReclamation();
    } 
        public void viewReclamation(){
    ReclamationService se = new ReclamationService();
     ObservableList<Reclamation> observableArrayList = 
           FXCollections.observableArrayList(se.afficher());
    table.setItems((ObservableList<Reclamation>) se.afficher());
    coldesc.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
    coletat.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("etat"));
    coltype.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("type"));
    coldate.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("date"));
    coliduser.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("iduser"));
    colreponse.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("reponse"));
  
    }
    
        @FXML
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
           @FXML
        public void filter(){
     ReclamationService se = new ReclamationService();
         data = FXCollections.observableArrayList(se.afficher());
            FilteredList<Reclamation> filterdata = new FilteredList<>(data, t -> true);
            filterField.setOnKeyReleased(t -> {
                filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filterdata.setPredicate((Predicate<? super Reclamation>) type -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if((type.getType().contains(newValue)) || (type.getType().toLowerCase().contains(newValue) )||
                                ((type.getEtat()+"").contains(newValue)))
                                 {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Reclamation> sorteddata = new SortedList<>(filterdata);
                data = sorteddata;  
                table.setItems(data);
            });
    }
    
}
