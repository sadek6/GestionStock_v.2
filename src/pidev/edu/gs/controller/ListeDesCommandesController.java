/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.services.CommandeService;
import pidev.edu.gs.utils.Facteur;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ListeDesCommandesController implements Initializable {

    @FXML
    private AnchorPane containerCommandes;
    @FXML
    private TableColumn<Commande, String> etatCommande;
    @FXML
    private TableColumn<Commande, Integer> prixTotaleCommande;
    @FXML
    private TableColumn annulerCommande;
    @FXML
    private TableView listeDesCommandes;

    private ObservableList<Commande> list;
    @FXML
    private TableColumn imprimerFacteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("hello");
        populateTableView();
    }

    private void populateTableView() {

        CommandeService commandeService = new CommandeService();
        list = commandeService.afficher(SeConnecterController.idUtilisateur);

        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixTotaleCommande.setCellValueFactory(new PropertyValueFactory<>("prixTotale"));

        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryRemove = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Annuler");
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
        
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryImprimer = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Imprimer");
                        test.setOnAction(event -> {
                            Commande p = getTableView().getItems().get(getIndex());
                            try {
                                Facteur.facteur(p);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ListeDesCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DocumentException ex) {
                                Logger.getLogger(ListeDesCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeDesCommandesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            //System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };

        imprimerFacteur.setCellFactory(cellFactoryImprimer);
        annulerCommande.setCellFactory(cellFactoryRemove);

        listeDesCommandes.setItems(list);

    }

    public void refresh() throws IOException {
        System.out.println("afficher mes commandes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeDesCommandes.fxml"));
        Parent root = loader.load();
        containerCommandes.getChildren().setAll(root);
    }
}
