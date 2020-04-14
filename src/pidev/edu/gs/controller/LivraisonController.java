/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import entites.Livraison;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pidev.edu.gs.services.ChaufferController;
import pidev.edu.gs.services.LivraisonContoller;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class LivraisonController implements Initializable {

    @FXML
    private TextField idadr;
    @FXML
    private TextField idcmd;
    @FXML
    private ComboBox<String> idper;
    @FXML
    private ComboBox<String>idniv;
    @FXML
    private DatePicker idate;
    @FXML
    private ComboBox<Integer> idch;
            ObservableList<String> niveau =FXCollections.observableArrayList("a domicile","par adresse","point a relais");
        ObservableList<String> list =FXCollections.observableArrayList("le matin","amidi");
     ChaufferController chauf=new ChaufferController();
      private ObservableList <Integer>ListMembre = FXCollections.observableArrayList();

    List<Integer> chif =chauf.afficher().stream().map(e->e.getNumpass()).collect(Collectors.toList());

      
        LivraisonContoller lv=new LivraisonContoller();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idper.setItems(list);
       idniv.setItems(niveau);
       ListMembre.addAll(chif);
              idch.setItems(ListMembre);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        String adresse =idadr.getText();
       String niveau=idniv.getValue();
       int commandeass=Integer.parseInt(idcmd.getText());
       String periode=idper.getValue();
       int nomch=idch.getValue();
       LocalDate datedelivraison=idate.getValue();
       
       Livraison l=new Livraison();
       l.setAdresse(adresse);
       l.setNiveau(niveau);
       l.setCommandeass(commandeass);
       l.setPeriode(periode);
       l.setNomch(nomch);
         java.util.Date date = java.sql.Date.valueOf(datedelivraison);
       l.setDatedelivraison((Date) date);
       
      lv.ajouter(l);
        chauf.count(l.getNomch());
    }

    @FXML
    private void consulter(ActionEvent event) {
    }
    
}
