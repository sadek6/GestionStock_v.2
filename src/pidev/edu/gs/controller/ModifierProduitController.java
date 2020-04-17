package pidev.edu.gs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.services.ProduitService;

import java.net.URL;
import java.util.ResourceBundle;


public class ModifierProduitController implements Initializable {


    public TextField nom;
    public TextField quantite;
    public TextField prix;
    ListeProduitAdminController c;

    int id;
    Produit p ;
    public void initData(Produit p, ListeProduitAdminController controller) {
        c = controller;
        this.p = p;
        this.nom.setText(p.getNom());
        this.quantite.setText(String.valueOf(p.getQuantite()));
        this.prix.setText(String.valueOf(p.getPrix()));
    }

    public void modifierProduit(ActionEvent actionEvent) {
        ProduitService ps = new ProduitService();
        Produit newProduit = new Produit(p.getId(), this.nom.getText(), Float.valueOf(prix.getText()), Integer.valueOf(quantite.getText()));
        ps.modifier(newProduit);
        c.populateTableView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
