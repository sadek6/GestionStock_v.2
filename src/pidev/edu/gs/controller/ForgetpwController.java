/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import pidev.edu.gs.utils.Mail3;
import pidev.edu.gs.utils.ConnectionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetpwController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private AnchorPane forgetAP;
    @FXML
    private TextField nomUtilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBackLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/seConecter.fxml"));
             //   Parent root = loader.load();
                SeConnecterController seConnecterController = loader.getController();
       // nomUtilisateur.getScene().setRoot(root);
    }

    @FXML
    private void sendPW(ActionEvent event) throws SQLException, Exception {
        Statement stm ;
        Connection con = ConnectionBD.getInstance().getCnx();
        stm=con.createStatement();
             String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(100, 10000));
        String req ="UPDATE user SET password="+DigestUtils.sha512Hex(randomNum)+" WHERE email="+email.getText();
        stm.executeUpdate(req);
       //  PreparedStatement ps = con.prepareStatement(req);
     // ps.executeUpdate();
        
  //   Mail3.SendMail(email.getText(), randomNum);
        
    }
    
}
