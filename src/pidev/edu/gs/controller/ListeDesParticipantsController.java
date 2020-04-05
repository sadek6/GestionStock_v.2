/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.gs.entities.Participant;
import pidev.edu.gs.services.JeuxConcoursService;
import pidev.edu.gs.services.ParticiperService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ListeDesParticipantsController implements Initializable {

    @FXML
    private TableView listeDesParticipants;
    @FXML
    private TableColumn<Participant, Integer> idParticipant;
    @FXML
    private TableColumn<Participant, String> nomParticipant;
    @FXML
    private TableColumn<Participant, Integer> idJeuxConcours;
    @FXML
    private TableColumn<Participant, String> nomJeuxConcours;
    @FXML
    private TableColumn<Participant, Integer> prix;
    @FXML
    private TableColumn exclure;
    
    private ObservableList<Participant> list;
    @FXML
    private AnchorPane containerListeParticipants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateTableView();
    }
    
    public void populateTableView() {
        
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        list = jeuxConcoursService.afficherListeParticipants();
        
        idParticipant.setCellValueFactory(new PropertyValueFactory<>("idParticipant"));
        nomParticipant.setCellValueFactory(new PropertyValueFactory<>("nomParticipant"));
        idJeuxConcours.setCellValueFactory(new PropertyValueFactory<>("idJeuxConcours"));
        nomJeuxConcours.setCellValueFactory(new PropertyValueFactory<>("nomJeuxConcours"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        Callback<TableColumn<Participant, String>, TableCell<Participant, String>> cellFactoryExclure = (param) -> {

            final TableCell<Participant, String> cell = new TableCell<Participant, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Exclure");
                        test.setOnAction(event -> {
                            Participant p = getTableView().getItems().get(getIndex());
                            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
                            ParticiperService participerService = new ParticiperService();
                            
                            try {
                                participerService.quitter(p.getIdParticipant(), p.getIdJeuxConcours());
                            } catch (SQLException ex) {
                                Logger.getLogger(ListeDesParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            jeuxConcoursService.decNbParticipants(p.getIdJeuxConcours());
                            
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeDesParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
        
        exclure.setCellFactory(cellFactoryExclure);
        listeDesParticipants.setItems(list);
    }
    
    public void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeDesParticipants.fxml"));
        Parent root = loader.load();
        containerListeParticipants.getChildren().setAll(root);
    }
    
}
