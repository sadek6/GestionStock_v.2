package pidev.edu.gs.controller;

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
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev.edu.gs.entities.Categorie;
import pidev.edu.gs.services.ServiceCategorie;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListCategorieController implements Initializable {


    public TableColumn<Categorie, String> nomCategorie;
    public TableColumn<Categorie, String> description;
    public TableColumn modifier;
    public TableColumn supprimer;
    public Label nomUtilisateur;
    @FXML
    private TableView<Categorie> listeCategories;
    private ObservableList<Categorie> list ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("afficher list des categories");
        nomUtilisateur.setText(("Liste des categories  "));
        populateTableView();

    }
    public void populateTableView() {

        ListCategorieController thisController = this;
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        list =  FXCollections.observableArrayList();
       // serviceCategorie.afficher().forEach(c -> list.add(c));
        list.addAll(serviceCategorie.afficher());


        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFactory= (param) -> {

            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {

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
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/modifier_categorie.fxml"));
                                //fxmlLoader.setLocation();
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();

                                stage.setTitle("Modifier Produit");
                                Categorie p = getTableView().getItems().get(getIndex());

                                stage.setScene(new Scene(root1));
                                fxmlLoader.<ModifierCategorieController>getController().initData(p, thisController);

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
        Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFactorySupp= (param) -> {

            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {

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
                                alert.setTitle("Supprimer Categorie");
                                alert.setHeaderText("vous etes sur ?");
                                alert.setContentText("cette action ne peut pas etre realiser");

                                // option != null.
                                Optional<ButtonType> option = alert.showAndWait();

                                if (option.get() == null) {
//                                    this.label.setText("No selection!");
                                } else if (option.get() == ButtonType.OK) {
                                    new ServiceCategorie().supprimer(getTableView().getItems().get(getIndex()));
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
        listeCategories.setItems(list);

    }

    public void AjouterCategorie(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/ajout_categorie.fxml"));
            //fxmlLoader.setLocation();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Ajouter Categorie");
            stage.setScene(new Scene(root1));

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
