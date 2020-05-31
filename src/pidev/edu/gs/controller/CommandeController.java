/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javax.mail.MessagingException;
import pidev.edu.gs.controller.PanelClientController;
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.Addresse;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.entities.Panier;
import pidev.edu.gs.entities.PanierVendu;
import pidev.edu.gs.entities.Utilisateur;
import pidev.edu.gs.services.AddresseService;
import pidev.edu.gs.services.CommandeService;
import pidev.edu.gs.services.GestionUtilisateur;
import pidev.edu.gs.services.PanierService;
import pidev.edu.gs.services.PanierVenduService;
import pidev.edu.gs.services.ProduitService;
import pidev.edu.gs.utils.Mail;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField numTel;
    @FXML
    private TextField email;
    @FXML
    private TextField pays;
    @FXML
    private TextField ville;
    @FXML
    private TextField codePostale;

    public static int idAdresse;

    Boolean verificationNumTel = false;
    Boolean verificationEmail = false;
    Boolean verificationPays = false;
    Boolean verificationVille = false;
    Boolean verificationCodePostale = false;
    @FXML
    private Label numTelTest;
    @FXML
    private Label codePostaleTest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numTelTest.setVisible(false);
        codePostaleTest.setVisible(false);
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
        Utilisateur utilisateur = gestionUtilisateur.findUser(SeConnecterController.idUtilisateur);
        email.setText(utilisateur.getEmail());
    }

    @FXML
    public void passerCommande() throws MessagingException {
        if (testGlobale()) {
            System.out.println("procedure de creation de commande");
            System.out.println("@ ok");
            idAdresse = 0;

            Addresse addresse = new Addresse();
            addresse.setNumTel(Integer.parseInt(numTel.getText()));
            addresse.setMail(email.getText());
            addresse.setPays(pays.getText());
            addresse.setVille(ville.getText());
            addresse.setPinCode(Integer.parseInt(codePostale.getText()));
            addresse.setId(addresse.hashCode());
            idAdresse = addresse.getId();
            System.out.println(addresse);

            AddresseService addresseService = new AddresseService();
            addresseService.add(addresse);
            System.out.println("###creation commande###");
            System.out.println("###calculer prix totale###");
            ProduitService produitService = new ProduitService();
            PanierService panierService = new PanierService();
            List<Panier> list = new ArrayList<>();
            list = panierService.getUserBasket2(SeConnecterController.idUtilisateur);
            int prixTotale = 0;
            for (Panier panier : list) {
                prixTotale += produitService.getProductPrice(panier.getIdProduit());
            }

            Commande commande = new Commande();

            commande.setIdClient(SeConnecterController.idUtilisateur);
            commande.setPrixTotale(prixTotale);
            commande.setEtat("en cours");
            commande.setAdresse(idAdresse);
            commande.setId(commande.hashCode());
            System.out.println(commande);

            CommandeService commandeService = new CommandeService();
            commandeService.createCommande(commande);

            System.out.println("###creation panier vendu###");
            PanierVenduService panierVenduService = new PanierVenduService();

            for (Panier panier : list) {
                PanierVendu panierVendu = new PanierVendu();
                panierVendu.setIdProduit(panier.getIdProduit());
                panierVendu.setIdClient(panier.getIdClient());
                panierVendu.setQuantite(panier.getQuantite());
                panierVendu.setIdCommande(commande.getId());
                panierVenduService.add(panierVendu);
                panierService.removeProductFromBasket(panier.getId());
            }

            System.out.println("opération ok ");
            Mail.sendMail("muhamedsadek.snoussi@gmail.com");

            TrayNotification tray = new TrayNotification("Successfully",
                    "Commande effectuée avec succès", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(3));

            precedent();
        }
    }

    private void precedent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/panelClient.fxml"));
            Parent root = loader.load();

            PanelClientController panelClientController = loader.getController();
            email.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (numTel.getText().trim().length() == 7) {
            boolean test = true;
            for (int i = 1; i < numTel.getText().trim().length() && test; i++) {
                char ch = numTel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                numTelTest.setVisible(false);
                verificationNumTel = true;
            }
        } else {
            System.out.println("taille num non valide");
            numTelTest.setVisible(true);
            numTelTest.setText("Il faut 8 chiffres");
            verificationNumTel = false;
        }
    }

    @FXML
    private void controlEmail(KeyEvent event) {
        verificationEmail = false;
        System.out.println("mail est unique");
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(email.getText());

        if (matcher.matches()) {
            verificationEmail = true;
        }
    }

    @FXML
    private void controlPays(KeyEvent event) {

        if (pays.getText().trim().equals("")) {
            verificationPays = false;
        } else {
            verificationPays = true;
        }
    }

    @FXML
    private void controlVille(KeyEvent event) {

        if (ville.getText().trim().equals("")) {
            verificationVille = false;
        } else {
            verificationVille = true;
        }
    }

    @FXML
    private void controlCodePostale(KeyEvent event) {
        verificationCodePostale = false;
        if (codePostale.getText().trim().length() == 3) {
            boolean test = true;
            for (int i = 1; i < codePostale.getText().trim().length() && test; i++) {
                char ch = codePostale.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                codePostaleTest.setVisible(false);
                verificationCodePostale = true;
            }
        } else {
            System.out.println("taille num non valide");
            codePostaleTest.setVisible(true);
            codePostaleTest.setText("Il faut 4 chiffres");
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
