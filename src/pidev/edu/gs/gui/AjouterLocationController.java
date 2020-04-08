/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev.edu.gs.entities.Location;
import pidev.edu.gs.entities.Materiel;
import pidev.edu.gs.services.LocationService;
import pidev.edu.gs.services.MaterielService;
import pidev.edu.gs.utils.ConnectionBD;


/**
 * FXML Controller class
 *
 * @author pc 2017
 */
public class AjouterLocationController implements Initializable {
   @FXML
    private AnchorPane locationContainer;
   
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker dateFin;
   @FXML
    private ComboBox<Materiel> id;
    @FXML
    private TableView<Location> table;
    @FXML
    private TableColumn<Location, Integer> nom;
    @FXML
    private TableColumn supprimer;
      @FXML
    private TableColumn<Location, Date> dateDebb;
    @FXML
    private TableColumn<Location, Date> dateFinn;
     public ObservableList<Location> data=FXCollections.observableArrayList();
        public ObservableList<Materiel> data2=FXCollections.observableArrayList();
      private ObservableList<Location> list;
    @FXML
    private MenuItem supprimer1;
     @FXML
    private Button save;
    @FXML
    private TableColumn<Location, Integer> idL;
    int eventid=0;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTableView();
        viewLoc();
    }    

   /* public void viewLocation(){
    LocationService se = new LocationService();
    table.setItems((ObservableList<Location>) se.afficher());
    colid.setCellValueFactory(new PropertyValueFactory<Location,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<Location,Date>("dateDebut"));
    colage.setCellValueFactory(new PropertyValueFactory<Location,Date>("dateFin"));
    coldm.setCellValueFactory(new PropertyValueFactory<Location,Integer>("idMateriel"));

    }*/
    public void populateTableView(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from materiel";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data2.add(new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

      }
      

    }
    catch(Exception e){
        e.printStackTrace();
    }
    id.setItems(data2);

    }
    
     public void viewLoc(){
    LocationService se = new LocationService();
    table.setItems((ObservableList<Location>) se.afficher());
        idL.setCellValueFactory(new PropertyValueFactory<Location,Integer>("id"));

    dateDebb.setCellValueFactory(new PropertyValueFactory<Location,Date>("DateDebut"));
    dateFinn.setCellValueFactory(new PropertyValueFactory<Location,Date>("DateFin"));
    nom.setCellValueFactory(new PropertyValueFactory<Location,Integer>("idMateriel"));
     Callback<TableColumn<Location, String>, TableCell<Location, String>> cellFactoryRemove = (param) -> {

            final TableCell<Location, String> cell = new TableCell<Location, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Location p = getTableView().getItems().get(getIndex());
                           LocationService materielService1 = new LocationService();

                            se.supprimerLocation(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
        supprimer.setCellFactory(cellFactoryRemove);
       // table.setItems(list);
    }
    
    
    
    
    
    
    
    @FXML  
    private void ajouterLocation(ActionEvent event) throws IOException {
      LocalDate d = dateDeb.getValue();
        Date datedeb = new Date(dateDeb.getValue().getYear()-1900, dateDeb.getValue().getMonthValue()-1, dateDeb.getValue().getDayOfMonth());

        // Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         LocalDate dd = dateFin.getValue();
        Date datefin = new Date(dateFin.getValue().getYear()-1900, dateFin.getValue().getMonthValue()-1, dateFin.getValue().getDayOfMonth());

        // Date datedd = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
/*         
 LocalDate ddd = dateDeb.getValue();
         Date dated = (Date) Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
 int i=0;
         
 int i=0;*/
         
         LocationService se=new LocationService();
         
         
Materiel dm =id.getSelectionModel().getSelectedItem();
               se.ajouter( new Location (datedeb,datefin,dm.getId())); 
               
               
Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informtion");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Location ajouté !");
            alert.showAndWait();
            viewLoc();
         
       }        
      public void refresh() throws IOException {
        System.out.println("afficher materiel");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/AjouterLocation.fxml"));
        Parent root = loader.load();
        locationContainer.getChildren().setAll(root);
    }

    @FXML
    void supprimer(ActionEvent event) {
    Location m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           LocationService se =new LocationService();
       Location e =new Location(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette location");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(String.valueOf(m.getId()));
         viewLoc();

      }
    }
}
    
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    
    
    
    
    
    
   int press()
    {
                    LocalDate d = dateDeb.getValue();
                    Date da = Date.valueOf(dateDeb.getValue().toString());

          data=table.getSelectionModel().getSelectedItems();
           int id;
              id=data.get(0).getId();    
                 System.out.println(id);
            dateDeb.setValue(LOCAL_DATE(data.get(0).getDateDebut().toString()));
           dateFin.setValue(LOCAL_DATE(data.get(0).getDateFin().toString()));

            return id;
    }
    
  
    
    
    
    
int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getId();
    }
    @FXML
    private void modifier(ActionEvent event) {
    press();
    }
  
    
     @FXML
    void save(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE location SET DateDebut=?,DateFin=?,Materiel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        Materiel dm =id.getSelectionModel().getSelectedItem();
  LocalDate d = dateDeb.getValue();
            java.util.Date dated = java.util.Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date daa = Date.valueOf(dateDeb.getValue().toString());
           Date daab = Date.valueOf(dateFin.getValue().toString());

            pst.setInt(4, getid());
            pst.setDate(1, daa);
                        pst.setDate(2, daab);

            pst.setInt(3,dm.getId());
            pst.executeUpdate();
            System.out.println("Location modifiée !");
            viewLoc();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
 
    
    
    
    
}
    

