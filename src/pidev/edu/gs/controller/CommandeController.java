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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import pidev.edu.gs.controller.PanelClientController;
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.Addresse;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.entities.Panier;
import pidev.edu.gs.entities.PanierVendu;
import pidev.edu.gs.services.AddresseService;
import pidev.edu.gs.services.CommandeService;
import pidev.edu.gs.services.PanierService;
import pidev.edu.gs.services.PanierVenduService;
import pidev.edu.gs.services.ProduitService;
import pidev.edu.gs.utils.Mail;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void passerCommande() throws MessagingException{
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
        for (Panier panier : list) 
            prixTotale += produitService.getProductPrice(panier.getIdProduit());
        
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
        precedent();
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
    
}
