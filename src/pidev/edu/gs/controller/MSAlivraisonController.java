/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import entites.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pidev.edu.gs.services.ChaufferController;
import pidev.edu.gs.services.LivraisonContoller;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.edu.gs.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class MSAlivraisonController implements Initializable {

    @FXML
    private TableView<Livraison> table;
    @FXML
    private TableColumn Adresse;
    @FXML
    private TableColumn Niveau;
    @FXML
    private TableColumn nbrc;
    @FXML
    private TableColumn periode;
    @FXML
    private TableColumn nomche;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn id;
    private int ID ;
    Livraison t =new Livraison();
 
     ObservableList<Livraison> data;
     
     
    
     LivraisonContoller lv=new LivraisonContoller();
     ChaufferController chauff=new ChaufferController();
   
   

    @FXML
    private TextField adr;
    @FXML
    private ComboBox coniv;
                ObservableList<String> con =FXCollections.observableArrayList("a domicile","par adresse","point a relais");
    @FXML
    private DatePicker datel;
    @FXML
    private ComboBox per;
    ObservableList<String> periodecp =FXCollections.observableArrayList("le matin","à midi");
    @FXML
    private ComboBox ch;
    private ObservableList <Integer>ListMembre = FXCollections.observableArrayList();

    List<Integer> chif =chauff.afficher().stream().map(e->e.getNumpass()).collect(Collectors.toList());
     
    
    @FXML
    private TextField comm;
    @FXML
    private AnchorPane panel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        coniv.setItems(con);
        per.setItems(periodecp);
        ListMembre.addAll(chif);
        ch.setItems(ListMembre);
      
       
        
        
       
            

        
        
       
       
         Adresse.setCellValueFactory(
            new PropertyValueFactory<Livraison,String>("adresse")
        );
         Niveau.setCellValueFactory(
            new PropertyValueFactory<Livraison,String>("niveau")
        );
          nbrc.setCellValueFactory(
            new PropertyValueFactory<Livraison,Integer>("commandeass")
        );
           periode.setCellValueFactory(
            new PropertyValueFactory<Livraison,String>("periode")
        );
           nomche.setCellValueFactory(
            new PropertyValueFactory<Livraison,Integer>("nomch")
        );
         date.setCellValueFactory(
            new PropertyValueFactory<Livraison,Date>("datedelivraison")
        );
        
            id.setCellValueFactory(
            new PropertyValueFactory<Livraison,Integer>("id")
        );
      
        
           data = FXCollections.observableArrayList();
         data.addAll(lv.afficher());
       data.forEach(System.out::println);
        table.setItems(data);
       data.forEach(System.out::println);
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLDataException {
        
       
                Livraison EventSelec = (Livraison) table.getSelectionModel().getSelectedItem();
        if(EventSelec == null){
            
        }else{
            if(lv.delete(EventSelec.getId())){
                 chauff.counte(7);
              
                resetTableData();
                
            }else{
                
            }
        }
        
    }
      public void resetTableData() throws SQLDataException
    {
        List<Livraison> listEvenements = new ArrayList<>();
        listEvenements = lv.afficher();
        ObservableList<Livraison> data = FXCollections.observableArrayList(listEvenements);
        table.setItems(data);
         
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        
                        Livraison EventSelec =  table.getSelectionModel().getSelectedItem();
                        
                         ID=EventSelec.getId();
                        adr.setText(EventSelec.getAdresse());
                        coniv.getSelectionModel().select(EventSelec.getNiveau());
                      
               comm.setText(Integer.toString(EventSelec.getCommandeass()));
               per.getSelectionModel().select(EventSelec.getPeriode());
               ch.getSelectionModel().select(EventSelec.getNomch());
               
                        

            datel.setValue(EventSelec.getDatedelivraison().toLocalDate());
                        
        
      
    
    }
    
         public void refresh (){
            
        LivraisonContoller rs = new LivraisonContoller();
               

                 ArrayList listRec = (ArrayList) rs.afficher();
             
                ObservableList obs = FXCollections.observableArrayList(listRec);
               table.setItems(obs);
          
         Adresse.setCellValueFactory(
            new PropertyValueFactory<>("adresse")
        );
         Niveau.setCellValueFactory(
            new PropertyValueFactory<>("niveau")
        );
          nbrc.setCellValueFactory(
            new PropertyValueFactory<>("commandeass")
        );
           periode.setCellValueFactory(
            new PropertyValueFactory<>("periode")
        );
           nomche.setCellValueFactory(
            new PropertyValueFactory<>("nomch")
        );
         date.setCellValueFactory(
            new PropertyValueFactory<>("datedelivraison")
        );
        
            id.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
      

                
              table.refresh();
                
                
                
   }

    @FXML
    private void save(ActionEvent event)throws IOException, SQLException {
        System.out.println("ssssssssssssssssssssssssssss");
         Connection cnx=ConnectionBD.getInstance().getCnx();
         

           String adresse =adr.getText();
       String niveau=(String) coniv.getValue();
       int commandeass=Integer.parseInt(comm.getText());
       String periode=(String) per.getValue();
       int nomch=(int) ch.getValue();
       LocalDate datedelivraison=datel.getValue();
       
   String requete = "UPDATE livraison SET adresse='"+adresse+"',niveau='"+niveau+"'"
           + ",commandeass='"+commandeass+"',periode='"+periode+"',nomch='"+nomch+"',datedelivraison='"+datedelivraison+"' WHERE id='"+ID+"'";
       PreparedStatement pst = cnx.prepareStatement(requete);
         
        System.out.println(pst);  
       pst.executeUpdate();
       System.out.println("ssssssssssssssssssssssssssss");
       refresh();
       
    
       
      
   
    }

    @FXML
    private void Chercher(ActionEvent event) {
        
         List<Livraison> listEvenements = new ArrayList<>();
            listEvenements = lv.RechercheLivraisonParDate();
            ObservableList<Livraison> data = FXCollections.observableArrayList(listEvenements);
            table.setItems(data);
    }
    
    @FXML
    private void ajoute(ActionEvent event) throws IOException {
        
    }
    

    @FXML
    private void chau(ActionEvent event) throws IOException {
            /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestiondelivraisons/AjoutCh.fxml"));
            //fxmlLoader.setLocation();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Ajouter Chauffeur");
            stage.setScene(new Scene(root1));

            stage.show();*/
        
    }
    
}
