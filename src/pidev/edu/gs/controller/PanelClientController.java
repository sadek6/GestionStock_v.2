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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class PanelClientController implements Initializable {

    @FXML
    private AnchorPane container_client;
    @FXML
    private Label idUtilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void afficherProduit(ActionEvent actionEvent) throws IOException {
        System.out.println("afficher liste des produits");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeDesProduits.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void afficherPanier(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/panier.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void afficherCommandes(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher commandes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeDesCommandes.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
}
