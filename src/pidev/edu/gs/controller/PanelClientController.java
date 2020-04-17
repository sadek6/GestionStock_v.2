/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class PanelClientController implements Initializable {

    @FXML
    private AnchorPane container_client;
    @FXML
    private MenuItem mesParticipations;
    @FXML
    private VBox co;

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
    
    @FXML
    public void afficherJeux(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher jeux");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeJeuxConcoursClient.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void afficherMesParticipation(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher jeux *************");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/mesParticipations.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void ajouterReclamation(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher jeux *************");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/Reclamation.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void afficherReponse(ActionEvent actionEvent) throws IOException{
        System.out.println("afficher jeux *************");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/AfficheReponse.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
    @FXML
    public void logout() throws IOException{
        System.out.println("afficher jeux *************");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/seConnecter.fxml"));
        Parent root = loader.load();
        co.getChildren().setAll(root);
    }
    
    
   
}
