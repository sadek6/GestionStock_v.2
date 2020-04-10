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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.gs.controller.ListeDesCommandesController;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.services.CommandeService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class CommandeAdminController implements Initializable {

    @FXML
    private TableView listeCommandeAdmin;
    @FXML
    private TableColumn<Commande, String> nomClient;
    @FXML
    private TableColumn<Commande, Integer> idCommande;
    @FXML
    private TableColumn<Commande, Integer> prixTotale;
    @FXML
    private TableColumn<Commande, String> etatCommande;
    @FXML
    private TableColumn traiterCommande;
    @FXML
    private TableColumn supprimerCommande;
    @FXML
    private AnchorPane containerCommande;
    
    private ObservableList<Commande> list;
    @FXML
    private TextField mot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateTableView();
        
    }

    private void populateTableView() {
        
        CommandeService commandeService = new CommandeService();
        list = commandeService.afficherAdmin();
        
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixTotale.setCellValueFactory(new PropertyValueFactory<>("prixTotale"));
        
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryRemove = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Commande p = getTableView().getItems().get(getIndex());
                            CommandeService commandeService1 = new CommandeService();

                            commandeService.remove(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeDesCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
        
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryTraiter = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Traiter");
                        test.setOnAction(event -> {
                            Commande p = getTableView().getItems().get(getIndex());
                            CommandeService commandeService1 = new CommandeService();

                            commandeService.traiter(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeDesCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
        
        supprimerCommande.setCellFactory(cellFactoryRemove);
        traiterCommande.setCellFactory(cellFactoryTraiter);
        listeCommandeAdmin.setItems(list);
        
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher mes commandes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/commandeAdmin.fxml"));
        Parent root = loader.load();
        containerCommande.getChildren().setAll(root);
    }
    
    public void chercher(String mot){
        System.out.println("recherche");
    }

    @FXML
    private void chercher(KeyEvent event) {
        System.out.println(mot.getText());
        CommandeService commandeService = new CommandeService();
        list = commandeService.chercher(mot.getText());
        for (Commande commande : list) {
            System.out.println(commande);
        }
        
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixTotale.setCellValueFactory(new PropertyValueFactory<>("prixTotale"));
        
        listeCommandeAdmin.setItems(list);
    }
    
}
