package pidev.edu.gs.controller;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.services.PanierService;
import pidev.edu.gs.services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListeProduitAdminController extends Application implements  Initializable {

    @FXML
    public TableColumn<Produit, Integer> quantiteProduit;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private TableView<Produit> listeProduits;
    @FXML
    private TableColumn<Produit, String> nomProduit;
    @FXML
    private TableColumn<Produit, Float> prixProduit;
    @FXML
    private TableColumn modifier;
    @FXML
    private TableColumn supprimer;

    private ObservableList<Produit> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher list des produits");
        nomUtilisateur.setText(("Bienvenu " + String.valueOf(SeConnecterController.idUtilisateur)));
        populateTableView();
    }

    public void populateTableView() {

        ListeProduitAdminController thisController = this;
        ProduitService produitService = new ProduitService();
        list = produitService.afficher();

        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFactory= (param) -> {

            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button modifier = new Button("Modifier");
                        modifier.setOnAction(event -> {
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/modifier_produit.fxml"));
                                //fxmlLoader.setLocation();
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();

                                stage.setTitle("Modifier Produit");
                                Produit p = getTableView().getItems().get(getIndex());

                                stage.setScene(new Scene(root1));
                                fxmlLoader.<ModifierProduitController>getController().initData(p, thisController);

                                stage.show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        });

                        setGraphic(modifier);
                        setText(null);

                    }
                }

            };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFactorySupp= (param) -> {

            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button supprimer = new Button("Supprimer");
                        supprimer.setOnAction(event -> {
                            try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer Produit");
                                alert.setHeaderText("vous etes sur ?");
                                alert.setContentText("cette action ne peut pas etre realiser");

                                // option != null.
                                Optional<ButtonType> option = alert.showAndWait();

                                if (option.get() == null) {
//                                    this.label.setText("No selection!");
                                } else if (option.get() == ButtonType.OK) {
                                   new ProduitService().supprimer(getTableView().getItems().get(getIndex()));
                                   populateTableView();
                                } else if (option.get() == ButtonType.CANCEL) {
//                                    this.label.setText("Cancelled!");
                                } else {
//                                    this.label.setText("-");
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        });

                        setGraphic(supprimer);
                        setText(null);

                    }
                }

            };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        modifier.setCellFactory(cellFactory);
        supprimer.setCellFactory(cellFactorySupp);
        listeProduits.setItems(list);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void AjouterProduit(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/ajout_produit.fxml"));
            //fxmlLoader.setLocation();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Ajouter Produit");
            stage.setScene(new Scene(root1));

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
