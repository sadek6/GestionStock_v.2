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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.Addresse;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.services.AddresseService;
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
    private TableView<Commande> listeDesCommandes;

    private ObservableList<Commande> list;
    @FXML
    private TableColumn imprimerFacteur;
    @FXML
    private TextField newNumTel;
    @FXML
    private TextField newMail;
    @FXML
    private TextField newPays;
    @FXML
    private TextField newVille;
    @FXML
    private TextField newPinCode;
    @FXML
    private Button mod;
    @FXML
    private Label idAdresse;
    @FXML
    private Label testNumTel;
    @FXML
    private Label testCodePostale;

    Boolean verificationNumTel = true;
    Boolean verificationEmail = true;
    Boolean verificationPays = true;
    Boolean verificationVille = true;
    Boolean verificationCodePostale = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("hello");
        populateTableView();
        showHide(false);
        idAdresse.setVisible(false);
        testCodePostale.setVisible(false);
        testNumTel.setVisible(false);
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

    @FXML
    public void getCommande() throws SQLException {
        Commande commande = new Commande();
        commande = listeDesCommandes.getSelectionModel().getSelectedItem();
        System.out.println(commande);
        AddresseService addresseService = new AddresseService();
        Addresse addresse = addresseService.findAdresse(commande.getAdresse());
        System.out.println(addresse);
        showHide(true);
        newNumTel.setText(String.valueOf(addresse.getNumTel()));
        newMail.setText(addresse.getMail());
        newPays.setText(addresse.getPays());
        newPinCode.setText(String.valueOf(addresse.getPinCode()));
        newVille.setText(addresse.getVille());
        idAdresse.setText(String.valueOf(addresse.getId()));

    }

    @FXML
    public void modifierAdresse() {

        if (testGlobale()) {
            Addresse addresse = new Addresse();
            addresse.setId(Integer.parseInt(idAdresse.getText()));
            addresse.setNumTel(Integer.parseInt(newNumTel.getText()));
            addresse.setMail(newMail.getText());
            addresse.setPays(newPays.getText());
            addresse.setPinCode(Integer.parseInt(newPinCode.getText()));
            addresse.setVille(newVille.getText());
            System.out.println("begin mod @");
            AddresseService addresseService = new AddresseService();
            addresseService.modifierAdresse(addresse);
            showHide(false);
        }
    }

    public void showHide(boolean b) {
        newNumTel.setVisible(b);
        newMail.setVisible(b);
        newPays.setVisible(b);
        newPinCode.setVisible(b);
        newVille.setVisible(b);
        mod.setVisible(b);
    }

    @FXML
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (newNumTel.getText().trim().length() == 7) {
            boolean test = true;
            for (int i = 1; i < newNumTel.getText().trim().length() && test; i++) {
                char ch = newNumTel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                testNumTel.setVisible(false);
                verificationNumTel = true;
            }
        } else {
            System.out.println("taille num non valide");
            testNumTel.setVisible(true);
            testNumTel.setText("Il faut 8 chiffres");
            verificationNumTel = false;
        }
    }

    @FXML
    private void controlEmail(KeyEvent event) {
        verificationEmail = false;
        System.out.println("mail est unique");
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(newMail.getText());

        if (matcher.matches()) {
            verificationEmail = true;
        }
    }

    @FXML
    private void controlPays(KeyEvent event) {
        verificationPays = false;
        if (newPays.getText().trim().equals("")) {
            verificationPays = false;
        } else {
            verificationPays = true;
        }
    }

    @FXML
    private void controlVille(KeyEvent event) {
        verificationVille = false;
        if (newVille.getText().trim().equals("")) {
            verificationVille = false;
        } else {
            verificationVille = true;
        }
    }

    @FXML
    private void controlCodePostale(KeyEvent event) {
        verificationCodePostale = false;
        if (newPinCode.getText().trim().length() == 3) {
            boolean test = true;
            for (int i = 1; i < newPinCode.getText().trim().length() && test; i++) {
                char ch = newPinCode.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                testCodePostale.setVisible(false);
                verificationCodePostale = true;
            }
        } else {
            System.out.println("taille num non valide");
            testCodePostale.setVisible(true);
            testCodePostale.setText("Il faut 4 chiffres");
            verificationCodePostale = false;
        }
    }

    public boolean testGlobale() {
        if (verificationNumTel == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le Tel");
            alert.show();
        } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer le mail");
            alert.show();
        } else if (verificationPays == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le pays");
            alert.show();
        } else if (verificationVille == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir ville");
            alert.show();
        } else if (verificationCodePostale == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez verfifer le code postale");
            alert.show();
        } else {
            return true;
        }
        return false;
    }
}
