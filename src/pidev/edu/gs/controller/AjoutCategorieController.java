package pidev.edu.gs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Categorie;
import pidev.edu.gs.services.ServiceCategorie;

public class AjoutCategorieController {


    @FXML
    public TextField nomCategorie;
    @FXML
    private TextField description;


    public void AjouterCategorie(ActionEvent actionEvent) {


        System.out.println(nomCategorie.getText().toString());

        ServiceCategorie ps = new ServiceCategorie();

        Categorie p = new Categorie(0, nomCategorie.getText().toString(), description.getText().toString());
        ps.ajouter(p);
    }

    public void initData(String aaa) {
        System.out.println(aaa);
    }






}
