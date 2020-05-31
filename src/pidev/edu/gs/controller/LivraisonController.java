/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import pidev.edu.gs.entities.Chauffeur;
import pidev.edu.gs.entities.Livraison;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
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
    private ComboBox<Chauffeur> idch;
            ObservableList<String> niveau =FXCollections.observableArrayList("a domicile","par adresse","point a relais");
        ObservableList<String> list =FXCollections.observableArrayList("le matin","amidi");
     ChaufferController chauf=new ChaufferController();
     // private ObservableList <Integer>ListMembre = FXCollections.observableArrayList();

  //  List<Integer> chif =chauf.afficher().stream().map(e->e.getNumpass()).collect(Collectors.toList());

      
        LivraisonContoller lv=new LivraisonContoller();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             //Creating custom cell forr combobox
     Callback<ListView<Chauffeur>, ListCell<Chauffeur>> cellFactory = new Callback<ListView<Chauffeur>, ListCell<Chauffeur>>() {

    @Override
    public ListCell<Chauffeur> call(ListView<Chauffeur> l) {
        return new ListCell<Chauffeur>() {

            @Override
            protected void updateItem(Chauffeur item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(  item.getNom());
                }
            }
        } ;
    }
};

// Just set the button cell here:
idch.setButtonCell(cellFactory.call(null));
idch.setCellFactory(cellFactory);
 //remplissage du combobox
         pidev.edu.gs.services.ChaufferController chauf=new  pidev.edu.gs.services.ChaufferController();
         List<Chauffeur> arr=new ArrayList<>();
         arr=chauf.afficher();
        for(Chauffeur u: arr)
        {
            idch.getItems().add(u);
            
        }
        idper.setItems(list);
       idniv.setItems(niveau);
      // ListMembre.addAll(chif);
             // idch.setItems(ListMembre);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        Livraison l=new Livraison();
    
       
          if  ((idadr.getText().equals(""))   ||  ( idper.getValue().equals(""))
                  ||  (idcmd.getText().equals("") ) ||  (idniv.getValue().equals("") ))
          {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
              
        alert.setTitle(" Champs invalide " );
        alert.setHeaderText(null);
        alert.setContentText("Merci de bien remplir les champs !");
 
        alert.showAndWait();
                }else{
      
         String adresse =idadr.getText();
       String niveau=idniv.getValue();
       int commandeass=Integer.parseInt(idcmd.getText());
       String periode=idper.getValue();
       
     // tel=lv.findChauffeurBynumpass(nomch).getNumerotel();
    
     int nomch=idch.getValue().getNumpass();
       LocalDate datedelivraison=idate.getValue();
       
      
       l.setAdresse(adresse);
       l.setNiveau(niveau);
       l.setCommandeass(commandeass);
       l.setPeriode(periode);
       //Chauffeur cher= idch.getSelectionModel().getSelectedItem();
       l.setNomch(nomch);
         //   tel=lv.findChauffeurBynumpass(nomch).getNumerotel();

         java.util.Date date = java.sql.Date.valueOf(datedelivraison);
       l.setDatedelivraison((Date) date);
       
      lv.ajouter(l);
   //   sendSMS();
       chauf.count(l.getNomch());
     
    }}

    @FXML
    private void consulter(ActionEvent event) {
    }
      @FXML
    private void sms(ActionEvent event) {
         Livraison l=new Livraison();
        
       String adresse =idadr.getText();
       String niveau=idniv.getValue();
       int commandeass=Integer.parseInt(idcmd.getText());
       String periode=idper.getValue();
       
       //  tel=lv.findChauffeurBynumpass(nomch).getNumerotel();
    
    // String nomch=idch.getValue();
       LocalDate datedelivraison=idate.getValue();
        
              NexmoClient client = NexmoClient.builder().apiKey("82ba02be").apiSecret("O7FHVmMCxsEaxIot").build();
  TextMessage message = new TextMessage("Une livraison Ajoutée",
                   "+21626399969",
                    "l'adresse de la livraison est "+adresse+"la date de la livraison :"+datedelivraison+"le niveau de la livraison est "+niveau            );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
        
    }
    
}
