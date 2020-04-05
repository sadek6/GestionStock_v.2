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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    Boolean verificationTitre = false;
    Boolean verificationPrix = false;
    Boolean verificationNbParticipants = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void ajouterJeuxConcours() {
        if (testGlobale()) {
            JeuxConcours jeuxConcours = new JeuxConcours();
            jeuxConcours.setNomJeux(nomJeux.getText());
            jeuxConcours.setPrix(Integer.parseInt(prix.getText()));
            jeuxConcours.setNbParticipants(0);

            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
            jeuxConcoursService.addJeuxConcours(jeuxConcours);
            precedent();
        }
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

    @FXML
    private void controlTitre(KeyEvent event) {

        if (nomJeux.getText().trim().equals("")) {
            verificationTitre = false;
        } else {
            verificationTitre = true;
        }
    }

    @FXML
    private void controlPrix(KeyEvent event) {

        if (Integer.parseInt(prix.getText()) < 0) {
            verificationPrix = false;
        } else {
            verificationPrix = true;
        }
    }

    @FXML
    private void controlNb(KeyEvent event) {

        if (Integer.parseInt(nbParticipants.getText()) < 0) {
            verificationNbParticipants = false;
        } else {
            verificationNbParticipants = true;
        }
    }

    public boolean testGlobale() {
        if (verificationTitre == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le titre");
            alert.show();
        } else if (verificationPrix == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer le prix");
            alert.show();
        } else if (verificationNbParticipants == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer nb");
            alert.show();
        } else {
            return true;
        }

        return false;
    }

}
