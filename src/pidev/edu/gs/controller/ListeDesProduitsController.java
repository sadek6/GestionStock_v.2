/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.services.PanierService;
import pidev.edu.gs.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ListeDesProduitsController implements Initializable {

    @FXML
    private Label nomUtilisateur;
    @FXML
    private TableView<Produit> listeProduits;
    @FXML
    private TableColumn<Produit, String> nomProduit;
    @FXML
    private TableColumn<Produit, Integer> prixProduit;
    @FXML
    private TableColumn ajouterPanier;
    
    private ObservableList<Produit> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher list des produits");
        nomUtilisateur.setText("Liste des Produits" );
        populateTableView();
    }  
    
    private void populateTableView() {
        
        ProduitService produitService = new ProduitService();
        list = produitService.afficher();
        
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFactory= (param) -> {

            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("Ajouter le produit");
                        test.setOnAction(event -> {
                            Produit p = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();
                            try {
                                panierService.addProduct(SeConnecterController.idUtilisateur, p.getId());
                            } catch (SQLException ex) {
                                
                            }
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        
        ajouterPanier.setCellFactory(cellFactory);
        listeProduits.setItems(list);
        
    }
    
}
