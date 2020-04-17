/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javax.mail.MessagingException;
import pidev.edu.gs.services.GestionUtilisateur;
import pidev.edu.gs.utils.Mail;

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

    Boolean verificationTitre = true;
    Boolean verificationPrix = true;
    Boolean verificationNbParticipants = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idd.setVisible(false);
        showHide(false);
        listeJeuxConcours.setEditable(true);
        nomJeux.setCellFactory(TextFieldTableCell.forTableColumn());
        //prix.setCellFactory(TextFieldTableCell.forTableColumn());
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
                            if ((p.getEtat() == 0) && (p.getNbParticipants() > 2)) {
                                ObservableList<Utilisateur> l = jeuxConcoursService.getUtilisateursJeux(p.getId());
                                System.out.println(l.size());
                                //int r = getRandomNumberInRange(1, l.size()-1);
                                int r = ThreadLocalRandom.current().nextInt(1, l.size() - 1);
                                System.out.println(r);
                                System.out.println(l.get(r).toString());
                                /*Utilisateur utilisateur = l.get(r);
                                GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
                                try {
                                    Mail.sendMail(gestionUtilisateur.findUser(utilisateur.getId()).getEmail());
                                } catch (MessagingException ex) {
                                    Logger.getLogger(ListeJeuxConcoursController.class.getName()).log(Level.SEVERE, null, ex);
                                }*/
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
        if (testGlobale()) {
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

    @FXML
    private void controlTitre(KeyEvent event) {
        verificationTitre = false;
        if (nomJeux.getText().trim().equals("")) {
            verificationTitre = false;
        } else {
            verificationTitre = true;
        }
    }

    @FXML
    private void controlPrix(KeyEvent event) {
        verificationPrix = false;
        if (Integer.parseInt(prix.getText()) == 0) {
            verificationPrix = false;
        } else {
            verificationPrix = true;
        }
    }

    @FXML
    private void controlNb(KeyEvent event) {
        verificationNbParticipants = false;
        if (Integer.parseInt(nbParticipants.getText()) < 0) {
            verificationNbParticipants = false;
        } else {
            verificationNbParticipants = true;
        }
    }

    public boolean testGlobale() {
        if (verificationTitre == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le titre");
            alert.show();
        } else if (verificationPrix == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer le prix");
            alert.show();
        } else if (verificationNbParticipants == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer nb");
            alert.show();
        } else {
            return true;
        }

        return false;
    }
    
    public void changeTitreCellEvent(CellEditEvent cellEditEvent){
        System.out.println("****************************");
        JeuxConcours jeuxConcours = listeJeuxConcours.getSelectionModel().getSelectedItem();
        jeuxConcours.setNomJeux(cellEditEvent.getNewValue().toString());
        
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        jeuxConcoursService.modifierJeuxConcours(jeuxConcours);
    }
    
    public void changePrixCellEvent(CellEditEvent cellEditEvent){
        JeuxConcours jeuxConcours = listeJeuxConcours.getSelectionModel().getSelectedItem();
        jeuxConcours.setPrix(Integer.parseInt(cellEditEvent.getNewValue().toString()));
        
        JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
        jeuxConcoursService.modifierJeuxConcours(jeuxConcours);
    }

}
