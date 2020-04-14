/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.edu.gs.entities.Utilisateur;
import pidev.edu.gs.services.UserService;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.gs.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class UserModifierController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableau;

    @FXML
    private TableColumn<Utilisateur, String> Image;

    @FXML
    private TableColumn<Utilisateur, String> username;

    @FXML
    private TableColumn<Utilisateur, String> email;

    @FXML
    private TableColumn<Utilisateur, String> Role;

    @FXML
    private TableColumn<Utilisateur, Integer> Etat;

    @FXML
    private TextField imgtxt;

    @FXML
    private TextField nomtxt;

    @FXML
    private TextField emailtxt;
        @FXML
    private TextField passtxt;

        @FXML
    private AnchorPane containerAdmin;
        
        public ObservableList<Utilisateur> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewUser();
        List<PieChart.Data> l = new ArrayList<>();
        UserService ps = new UserService();
        ps.getAllUser().stream().forEach(p->{
            l.add(new PieChart.Data(p.getUsername(),p.getEnabled()));
        });

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        l
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistique youssef");

        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        final Label caption = new Label("");
        //caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-text-fill: black;");
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY()-110);
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
        containerAdmin.getChildren().add(chart);
        containerAdmin.getChildren().add(caption);

    }    
    
     @FXML
    public void redirection(AnchorPane c,Utilisateur user ){
        //centerContent=c;
       
        }
    public void viewUser(){
    UserService se = new UserService();
    tableau.setItems((ObservableList<Utilisateur>) se.getuser());
    Role.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("roles"));
    username.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("username"));
    email.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
    Etat.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("enabled"));

    }
    String selectionner()
    {
          data=tableau.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNomUser();
            System.out.println(id);
            
            //cbmed.setValue(data.get(0).getIdMed());
            nomtxt.setText(data.get(0).getUsername());
             //System.out.print("idididididid"+id);
            
           // dpdate.setValue(data.get(0).getDateCons().toLocalDate());
            return id;
           
    } 
    
       @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE user SET nom=?,email=?,password=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        //DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();

            pst.setInt(4, getid());
            pst.setString(1, nomtxt.getText());
            pst.setString(2,emailtxt.getText());
            pst.setString(3,passtxt.getText());
            pst.executeUpdate();
           System.out.println("Utilisateur modifiée !");
            viewUser();
            //clearFields();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Utilisateur Modifieé !");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
            @FXML
    void supprimer(ActionEvent event) {
    Utilisateur m = tableau.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           UserService se =new UserService();
       Utilisateur e =new Utilisateur(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cet Utilisateur ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewUser();
         

      }
    }
} 
    
 int getid()
    {
        data=tableau.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getId();
    }

         @FXML
         private void modifier(ActionEvent event){
           selectionner();
           
   
    }
         
         
         
         
         
         
         
         
 
    
}
