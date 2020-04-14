/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import com.itextpdf.text.Image;
import entites.Chauffeur;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import pidev.edu.gs.services.ChaufferController;
import java.net.MalformedURLException;
import java.time.LocalDate;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterChauffeurController implements Initializable {

    @FXML
    private ImageView Image;
    @FXML
    private ComboBox<String> idniv;
    @FXML
    private DatePicker daten;
    @FXML
    private TextField numpass;
    @FXML
    private TextField idpre;
    @FXML
    private TextField idtel;
    @FXML
    private TextField idnom;
    //private TextField idc;
    @FXML
    private Button ajte;
        int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    ChaufferController chauff=new ChaufferController();

    
    ObservableList<String> niveau =FXCollections.observableArrayList("a domicile","par adresse","point a relais");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File( c + ".jpg");
        lien =  c + ".jpg";
        
        idniv.setItems(niveau);
        // TODO
    }    

    @FXML
    private void Load(ActionEvent event) throws MalformedURLException {
        
          
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            //Image image = new Image(pfile.toURI().toURL().toExternalForm());
            //Image.setImage(image);
    }
    }
   
      
    @FXML
    private void ajouter(ActionEvent event) {
                
       int numpas= Integer.parseInt(numpass.getText());
       copier(pfile,pDir);
       String Prenom=idpre.getText();
       String niveau=idniv.getValue();
       int numerotel=Integer.parseInt(idtel.getText());
       String nom=idnom.getText();
       int nbr=0;
       
 
       LocalDate datedenaissance=daten.getValue();
        java.util.Date date = java.sql.Date.valueOf(datedenaissance);
        
       
       
         Chauffeur ch=new Chauffeur(numpas,lien,Prenom,niveau,numerotel,nom,nbr,(java.sql.Date)date);
       
       
      chauff.ajouter(ch);
        
        
    
}

    public static boolean copier(File source, File dest){
        
         try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
       
    }

    @FXML
    private void liste(ActionEvent event) throws IOException {
        
         
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestiondelivraisons/MSAchauffeur.fxml"));
            //fxmlLoader.setLocation();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Ajouter Produit");
            stage.setScene(new Scene(root1));

            stage.show();
    }

    @FXML
    private void afficherlistedelivraison(ActionEvent event) throws IOException {
        
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestiondelivraisons/MSAlivraison.fxml"));
            //fxmlLoader.setLocation();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Afficher les livraisons");
            stage.setScene(new Scene(root1));

            stage.show();
    
    }
    
}
