/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import pidev.edu.gs.controller.ListeJeuxConcoursClientController;
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
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.JeuxConcours;
import pidev.edu.gs.services.JeuxConcoursService;
import pidev.edu.gs.services.ParticiperService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class MesParticipationsController implements Initializable {

    @FXML
    private AnchorPane containerMesParticipation;
    @FXML
    private TableView listeDesBA;
    @FXML
    private TableColumn<JeuxConcours, String> nomJeux;
    @FXML
    private TableColumn<JeuxConcours, Integer> prix;
    @FXML
    private TableColumn quitter;
    
    private ObservableList<JeuxConcours> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateTableView();
    }

    private void populateTableView() {
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        list = jeuxConcoursService.mesParticipations(SeConnecterController.idUtilisateur);
        
        nomJeux.setCellValueFactory(new PropertyValueFactory<>("nomJeux"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        Callback<TableColumn<JeuxConcours, String>, TableCell<JeuxConcours, String>> cellFactoryQuitter = (param) -> {

            final TableCell<JeuxConcours, String> cell = new TableCell<JeuxConcours, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Quitter");
                        test.setOnAction(event -> {
                            JeuxConcours p = getTableView().getItems().get(getIndex());
                            ParticiperService participerService = new ParticiperService();
                            try {
                                participerService.quitter(SeConnecterController.idUtilisateur, p.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(ListeJeuxConcoursClientController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(MesParticipationsController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        quitter.setCellFactory(cellFactoryQuitter);
        listeDesBA.setItems(list);

    }
    
    public void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/mesParticipations.fxml"));
        Parent root = loader.load();
        containerMesParticipation.getChildren().setAll(root);
    }
    
}
