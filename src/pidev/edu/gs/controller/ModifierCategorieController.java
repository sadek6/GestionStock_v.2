package pidev.edu.gs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Categorie;
import pidev.edu.gs.services.ServiceCategorie;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierCategorieController {


    @FXML
    public TextField nomCategorie;
    @FXML
    private TextField description;
    ListCategorieController c;

    int id;
    Categorie p ;
    public void initData(Categorie p, ListCategorieController controller) {
        c = controller;
        this.p = p;
        this.nomCategorie.setText(p.getNomCategorie());
        this.description.setText(p.getDescription());

    }

    public void modifierCategorie(ActionEvent actionEvent) {
        ServiceCategorie ps = new ServiceCategorie();
        Categorie newCategorie = new Categorie(p.getId(), this.nomCategorie.getText(), this.description.getText());
        ps.modifier(newCategorie);
        c.populateTableView();
    }

    //@Override
    public void initialize(URL location, ResourceBundle resources) {

    }





}
