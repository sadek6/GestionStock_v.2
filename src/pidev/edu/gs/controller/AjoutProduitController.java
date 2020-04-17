package pidev.edu.gs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.services.ProduitService;

public class AjoutProduitController {


    @FXML
    public TextField nom;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;

    public void AjouterProduit(ActionEvent actionEvent) {

        System.out.println(nom.getText().toString());

        ProduitService ps = new ProduitService();
        float price = Float.parseFloat(prix.getText());
        Produit p = new Produit(0, nom.getText().toString(), price, Integer.valueOf(quantite.getText()) );
        ps.ajouter(p);
    }

    public void initData(String aaa) {
        System.out.println(aaa);
    }
}
