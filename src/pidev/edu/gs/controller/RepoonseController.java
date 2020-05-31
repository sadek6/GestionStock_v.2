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
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pidev.edu.gs.entities.Reponse;
import pidev.edu.gs.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RepoonseController implements Initializable {

    @FXML
    private TextField repp;
    @FXML
    private DatePicker dpdate;
     @FXML
    private TableView<Reponse> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajte(ActionEvent event) {
        System.out.println("ok");
        ReponseService se = new ReponseService();
            //Reponse dm =cbrep.getSelectionModel().getSelectedItem();
            //Date datecons = new Date( dpdate.getValue().getYear()-1900 , dpdate.getValue().getMonth()-1, dpdate.getValue().getDayOfMonth());
       
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());
            se.AjouterRep(new Reponse( repp.getText(),datecons ));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Reponse ajoutée !");
            alert.showAndWait();
    }       
            
               @FXML
    void sms(ActionEvent event) {
            NexmoClient client = NexmoClient.builder().apiKey("7e75d0ad").apiSecret("Nqb1izzdjQmGM8PP").build();
  TextMessage message = new TextMessage("Une réponse est ajoutée suite à votre réclamation",
                   "+21654569046",
                    "Votre réclamation est traitée " );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
   
    }
    
    @FXML
    void supprimer(ActionEvent event) {
    Reponse m = table.getSelectionModel().getSelectedItem();
    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setAlertType(Alert.AlertType.WARNING);
     // set content text
     alert.setContentText(" Choix invalide ");
     // show the dialog
     alert.show();
  }
     else{
           ReponseService se =new ReponseService();
      Reponse e =new Reponse(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Medecin ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
        
      }
    }
}
    
    
}
