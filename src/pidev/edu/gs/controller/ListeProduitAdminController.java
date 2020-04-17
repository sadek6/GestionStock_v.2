package pidev.edu.gs.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.services.ProduitService;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ListeProduitAdminController extends Application implements  Initializable {

    @FXML
    public TableColumn<Produit, Integer> quantiteProduit;
    public TextField rech;
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
    private List<Produit> oldList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher list des produits");
        nomUtilisateur.setText(("Liste des produits"));
        populateTableView();
    }

    public void populateTableView() {

        ListeProduitAdminController thisController = this;
        if(rech.getText().isEmpty()){
            ProduitService produitService = new ProduitService();
            list = FXCollections.observableArrayList();
            oldList= produitService.afficher();
            list.addAll(oldList);
        }




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

    public void recherche(KeyEvent inputMethodEvent) {
        System.out.println("changed");
        list = FXCollections.observableArrayList();
        list.addAll(oldList.stream().filter(c -> c.getNom().contains(rech.getText())).collect(Collectors.toList()));
//        list.removeIf(c -> !c.getNom().contains(rech.getText()));
        this.listeProduits.setItems(list);
    }
}
