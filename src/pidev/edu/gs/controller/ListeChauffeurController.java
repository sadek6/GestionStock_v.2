/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import pidev.edu.gs.entities.Chauffeur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pidev.edu.gs.services.ChaufferController;
import pidev.edu.gs.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ListeChauffeurController implements Initializable {

    @FXML
    private ComboBox<String> niv;
    @FXML
    private TextField tel;
    @FXML
    private TextField nom;
    @FXML
    private TextField com;
    @FXML
    private DatePicker nais;
    ObservableList<String> niveau =FXCollections.observableArrayList("a domicile","par adresse","point a relais");
   // private TextField num;
   // private TableColumn id;
    @FXML
    private TableColumn  img;
    @FXML
    private TableColumn pre;
    @FXML
    private TableColumn nv;
    @FXML
    private TableColumn ntel;
    @FXML
    private TableColumn nomm;
    @FXML
    private TableColumn cmd;
    private int IDf;

    
    
    @FXML
    TableView<Chauffeur> tab;
    ChaufferController chauff=new ChaufferController();
    @FXML
    private TextField prenom;
    ObservableList<Chauffeur> data;
    @FXML
    private TableColumn dateln;
    @FXML
    private TextField chercher;
    @FXML
    private Button mdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        niv.setItems(niveau);
        
        
        
       
         img.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,String>("nom_image")
        );
            
          pre.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,String>("Prenom")
        );
           nv.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,String>("niveau")
        );
           ntel.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,Integer>("numerotel")
        );
         nomm.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,String>("Nom")
        );
          dateln.setCellValueFactory(
          new PropertyValueFactory<Chauffeur,Date>("datenain")
          );
    
          cmd.setCellValueFactory(
            new PropertyValueFactory<Chauffeur,Integer>("nbr")
        );
         
        
      data = FXCollections.observableArrayList();
         data.addAll(chauff.afficher());
       data.forEach(System.out::println);
        tab.setItems(data);
       data.forEach(System.out::println);
       //        TableFilter filtertable = new TableFilter(tableUsers);
//        List<Users> userslist = u11.getAll();
//        userslist1.addAll(userslist);
//        
//        IDUser.setCellValueFactory(new PropertyValueFactory<>("ID_USERS"));
//        firstname.setCellValueFactory(new PropertyValueFactory<>("LAST_NAME"));
//        lastname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
//        Mail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
//        STATUS.setCellValueFactory(new PropertyValueFactory<>("STATUS"));
//        
//        FilteredList<Users> filteredData = new FilteredList<>(userslist1, p -> true);
//        
//        inputSearch.textProperty().addListener((obsVal, oldValue, newValue) -> {
//            filteredData.setPredicate(Users -> Users.getEMAIL().toLowerCase().contains(inputSearch.getText().toLowerCase())                
//            
//            );
//        });
//        
//        SortedList<Users> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableUsers.comparatorProperty());
//        
//        tableUsers.setItems(sortedData);
       
        FilteredList<Chauffeur> filteredData = new FilteredList<>(data, p -> true);
        
        chercher.textProperty().addListener((obsVal, oldValue, newValue) -> {
            filteredData.setPredicate(Chauffeur -> {
                String lowercasefilter=newValue.toLowerCase();
                
                
              if (Chauffeur.getPrenom().toLowerCase().toLowerCase().indexOf(lowercasefilter) != -1  )     {
                  return true ;
                  
                  
                  
              } else if (Chauffeur.getNom().toLowerCase().toLowerCase().indexOf(lowercasefilter) != -1  ){
                  return true ;
              
              } 
              
            else return false ;
              
}        
            
             );
        });
        
        SortedList<Chauffeur> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        
       tab.setItems(sortedData);
    }    

    @FXML
    private void supprimer(ActionEvent event) {

        
        
                Chauffeur EventSelec = (Chauffeur) tab.getSelectionModel().getSelectedItem();
        if(EventSelec == null){
            
        }else{
            if(chauff.supp(EventSelec.getNumpass())){
                
               
                resetTableData();
            }else{
                
            }
        }
        
    }
 

    



    private void resetTableData() {
    
        List<Chauffeur> listEvenements = new ArrayList<>();
        listEvenements = chauff.afficher();
        ObservableList<Chauffeur> data = FXCollections.observableArrayList(listEvenements);
        tab.setItems(data);
    
    }
     public void refresh (){
            
       ChaufferController rs = new ChaufferController();
               

                 ArrayList listRec = (ArrayList) rs.afficher();
             
                ObservableList obs = FXCollections.observableArrayList(listRec);
               tab.setItems(obs);
          
       
      
 
         img.setCellValueFactory(
            new PropertyValueFactory<>("nom_image")
        );
            
          pre.setCellValueFactory(
            new PropertyValueFactory<>("Prenom")
        );
           nv.setCellValueFactory(
            new PropertyValueFactory<>("niveau")
        );
           ntel.setCellValueFactory(
            new PropertyValueFactory<>("numerotel")
        );
         nomm.setCellValueFactory(
            new PropertyValueFactory<>("Nom")
        );
          dateln.setCellValueFactory(
          new PropertyValueFactory<>("datenain")
          );
    
          cmd.setCellValueFactory(
            new PropertyValueFactory<>("nbr")
        );
                
              tab.refresh();
                
                
                
   }
    
    
     

    @FXML
    private void save(ActionEvent event)throws IOException, SQLException {
        
         Connection cnx=ConnectionBD.getInstance().getCnx();
    
          String pren=prenom.getText();
          String nome=nom.getText();
                 
       String niveau=(String) niv.getValue();
       int nbre=Integer.parseInt(com.getText());
       int numt=Integer.parseInt(tel.getText());
   
       LocalDate datedenain=nais.getValue();
       
   String requete = "UPDATE chauffeur SET Prenom='"+pren+"'"
           + ",niveau='"+niveau+"',numerotel='"+numt+"',Nom='"+nome+"',nbr='"+nbre+"',datenain='"+datedenain+"' WHERE numpass='"+IDf+"'";
       PreparedStatement pst = cnx.prepareStatement(requete);
         
          
       pst.executeUpdate();
       refresh();
    }

    @FXML
    private void modifier(ActionEvent event) {
         Chauffeur EventSelec =  tab.getSelectionModel().getSelectedItem();
                           IDf=EventSelec.getNumpass();

            //           num.setText(Integer.toString(EventSelec.getNumpass()));
                       tel.setText(Integer.toString(EventSelec.getNumerotel()));
                       prenom.setText(EventSelec.getPrenom());
                       nom.setText(EventSelec.getNom());
                       
                       
                        niv.getSelectionModel().select(EventSelec.getNiveau());
                            com.setText(Integer.toString(EventSelec.getNbr()));
                        
                    
          
                   
            nais.setValue(EventSelec.getDatenain().toLocalDate());
                        
    }

    @FXML
    private void cher(KeyEvent event) {
        
    }
    
}
