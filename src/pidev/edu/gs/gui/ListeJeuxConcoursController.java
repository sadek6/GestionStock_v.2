/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.gs.entities.JeuxConcours;
import pidev.edu.gs.entities.Utilisateur;
import pidev.edu.gs.services.JeuxConcoursService;
import java.util.concurrent.ThreadLocalRandom;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ListeJeuxConcoursController implements Initializable {

    @FXML
    private TableView<JeuxConcours> listeJeuxConcours;
    @FXML
    private TableColumn<JeuxConcours, String> nomJeux;
    @FXML
    private TableColumn<JeuxConcours, Integer> prix;
    @FXML
    private TableColumn<JeuxConcours, Integer> nbParticipants;
    @FXML
    private TableColumn<JeuxConcours, Integer> etat;
    @FXML
    private TableColumn supprimer;
    @FXML
    private TableColumn mod;
    @FXML
    private TableColumn valider;

    private ObservableList<JeuxConcours> list;
    @FXML
    private AnchorPane containerListeJC;
    @FXML
    private TextField newTitreJeux;
    @FXML
    private TextField newPrix;
    @FXML
    private TextField newNbParticipants;
    @FXML
    private Button newV;
    @FXML
    private Label idd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idd.setVisible(false);
        showHide(false);
        populateTableView();
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void populateTableView() {
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        list = jeuxConcoursService.afficher();

        nomJeux.setCellValueFactory(new PropertyValueFactory<>("nomJeux"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbParticipants.setCellValueFactory(new PropertyValueFactory<>("nbParticipants"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        Callback<TableColumn<JeuxConcours, String>, TableCell<JeuxConcours, String>> cellFactoryRemove = (param) -> {

            final TableCell<JeuxConcours, String> cell = new TableCell<JeuxConcours, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            JeuxConcours p = getTableView().getItems().get(getIndex());
                            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();

                            jeuxConcoursService.supprimerJeuxConcours(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
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

        Callback<TableColumn<JeuxConcours, String>, TableCell<JeuxConcours, String>> cellFactoryT = (param) -> {

            final TableCell<JeuxConcours, String> cell = new TableCell<JeuxConcours, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Fermer le jeux");
                        test.setOnAction(event -> {
                            JeuxConcours p = getTableView().getItems().get(getIndex());
                            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
                            if ((p.getEtat() == 0) && (p.getNbParticipants() > 2)){
                                ObservableList<Utilisateur> l = jeuxConcoursService.getUtilisateursJeux(p.getId());
                                System.out.println(l.size());
                                //int r = getRandomNumberInRange(1, l.size()-1);
                                int r = ThreadLocalRandom.current().nextInt(1, l.size()-1);
                                System.out.println(r);
                                System.out.println(l.get(r).toString());
                                p.setEtat(1);
                                System.out.println("***************");
                                jeuxConcoursService.modifierJeuxConcoursT(p);
                                try {
                                    refresh();
                                } catch (IOException ex) {
                                    Logger.getLogger(ListeJeuxConcoursController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };

        supprimer.setCellFactory(cellFactoryRemove);
        valider.setCellFactory(cellFactoryT);
        listeJeuxConcours.setItems(list);
    }

    public void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeJeuxConcours.fxml"));
        Parent root = loader.load();
        containerListeJC.getChildren().setAll(root);
    }

    public void showHide(boolean b) {
        newTitreJeux.setVisible(b);
        newNbParticipants.setVisible(b);
        newPrix.setVisible(b);
        newV.setVisible(b);
    }

    @FXML
    public void modifierJeuxConcours() {
        System.out.println("mod jc");

        //JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        JeuxConcours jeuxConcours = new JeuxConcours();
        jeuxConcours = listeJeuxConcours.getSelectionModel().getSelectedItem();
        System.out.println(jeuxConcours);

        if (jeuxConcours == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un jeux-concours");
            alert.show();

        } else {
            showHide(true);
            idd.setText(String.valueOf(jeuxConcours.getId()));
            newTitreJeux.setText(jeuxConcours.getNomJeux());
            newNbParticipants.setText(String.valueOf(jeuxConcours.getNbParticipants()));
            newPrix.setText(String.valueOf(jeuxConcours.getPrix()));
            //jeuxConcoursService.modifierJeuxConcours(jeuxConcours);
            //showHide(false);
        }

    }

    @FXML
    public void modifierJeuxConcours2() throws IOException {
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        JeuxConcours jeuxConcours = new JeuxConcours();

        jeuxConcours.setId(Integer.valueOf(idd.getText()));
        jeuxConcours.setNomJeux(newTitreJeux.getText());
        jeuxConcours.setNbParticipants(Integer.parseInt((newNbParticipants.getText())));
        jeuxConcours.setPrix(Integer.parseInt(newPrix.getText()));
        System.out.println(jeuxConcours);
        jeuxConcoursService.modifierJeuxConcours(jeuxConcours);
        showHide(false);
        refresh();
    }

}
