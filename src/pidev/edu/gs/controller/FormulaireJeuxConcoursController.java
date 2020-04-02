/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidev.edu.gs.controller.PanelAdminController;
import pidev.edu.gs.entities.JeuxConcours;
import pidev.edu.gs.services.JeuxConcoursService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class FormulaireJeuxConcoursController implements Initializable {

    @FXML
    private AnchorPane containerJC;
    @FXML
    private TextField nomJeux;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbParticipants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void ajouterJeuxConcours(){
        JeuxConcours jeuxConcours = new JeuxConcours();
        jeuxConcours.setNomJeux(nomJeux.getText());
        jeuxConcours.setPrix(Integer.parseInt(prix.getText()));
        jeuxConcours.setNbParticipants(Integer.parseInt(nbParticipants.getText()));
        
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        jeuxConcoursService.addJeuxConcours(jeuxConcours);
        precedent();
    }
    
    private void precedent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/panelAdmin.fxml"));
            Parent root = loader.load();

            PanelAdminController panelAdminController = loader.getController();
            prix.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }
    
}
