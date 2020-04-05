/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class PanelAdminController implements Initializable {

    @FXML
    private AnchorPane containerAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("espace admin");
    }

    @FXML
    public void afficherCommandes(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher commandes admin");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/commandeAdmin.fxml"));
        Parent root = loader.load();
        containerAdmin.getChildren().setAll(root);
    }
    
    @FXML
    public void ajouterJeuxConcours(ActionEvent actionEvent) throws IOException{
        System.out.println("ajouter jeux concours");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/formulaireJeuxConcours.fxml"));
        Parent root = loader.load();
        containerAdmin.getChildren().setAll(root);
    }
    
    @FXML
    public void ListerJeuxConcours(ActionEvent actionEvent) throws IOException{
        System.out.println("lister jeux concours");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeJeuxConcours.fxml"));
        Parent root = loader.load();
        containerAdmin.getChildren().setAll(root);
    }
    
    @FXML
    public void ListerParticipants(ActionEvent actionEvent) throws IOException{
        System.out.println("lister participants concours");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeDesParticipants.fxml"));
        Parent root = loader.load();
        containerAdmin.getChildren().setAll(root);
    }
    
}
