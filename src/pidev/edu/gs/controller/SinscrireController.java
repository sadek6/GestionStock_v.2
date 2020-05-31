/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.mail.MessagingException;
import static pidev.edu.gs.controller.AjouterChauffeurController.copier;
import pidev.edu.gs.controller.SeConnecterController;
import pidev.edu.gs.entities.Utilisateur;
import pidev.edu.gs.services.GestionUtilisateur;
import pidev.edu.gs.utils.Mail2;
import pidev.edu.gs.utils.Password;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SinscrireController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField mdp;
   
    Boolean verificationNom = false;
    Boolean verificationPrenom = false;
    Boolean verificationEmail = false;
 
    Boolean verificationMdp = false;
    
    
    @FXML
    private ImageView Image;
     int c;
    int file;
    File pDir;
    File pfile;
    String lien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("S'inscrire");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File( c + ".jpg");
        lien =  c + ".jpg";
       
        
    }

    @FXML
    public void sinscrire(ActionEvent actionEvent) throws IOException, MessagingException {
        if (verificationNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le nom");
            alert.show();

        } else if (verificationPrenom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le prenom");
            alert.show();

            } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();
        

        } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();
        }else if ((verificationMdp == false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez resaissir votre Mot de Passe Correctement");
            alert.show();

        }else{
            Password md = new Password();
            String mdpCrypte1 = md.hashPassword(mdp.getText());

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setUsername(nom.getText());
            utilisateur.setEmail(email.getText());
            utilisateur.setEmailCanonical(email.getText());
            utilisateur.setPassword(mdpCrypte1);
            utilisateur.setNomUser(nom.getText());
            utilisateur.setPrenomUser(prenom.getText());
            copier(pfile,pDir);
            utilisateur.setImage(lien);

            GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
            gestionUtilisateur.ajouterClient(utilisateur);
            
            TrayNotification tray = new TrayNotification("Successfully",
                    "Inscription Effectuée avec Succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/seConnecter.fxml"));
            Parent root = loader.load();
            SeConnecterController seConnecterController = loader.getController();
            prenom.getScene().setRoot(root);
            Mail2.sendMail(utilisateur.getEmail());
        }
    }

    public void action(KeyEvent keyEvent) {
        System.out.println("action");
    }

    @FXML
    private void controlNom(KeyEvent event) {

        if (nom.getText().trim().equals("")) {
            verificationNom = false;
        } else {
            verificationNom = true;
        }

    }

    @FXML
    private void controlPrenom(KeyEvent event) {
        if (prenom.getText().trim().equals("")) {
            verificationPrenom = false;
        } else {
            verificationPrenom = true;
        }
    }

   
    
    @FXML
    private void controlEmail(KeyEvent event) throws SQLException {
        verificationEmail = false;
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
        if(gestionUtilisateur.mailExiste(email.getText())){
            System.out.println("mail est unique");
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches())
                verificationEmail = true;
        }
        else
            System.out.println("mail non valide");
    }
    
    @FXML
    private void controlMDP(KeyEvent event) {

        if (mdp.getText().trim().equals("")) {

            verificationMdp = false;

        } else {

            verificationMdp = true;

        }
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
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            Image.setImage(image);
    }  
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

}
