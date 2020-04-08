/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.gui;

import com.itextpdf.text.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import pidev.edu.gs.controller.PanelAdminController;
import pidev.edu.gs.entities.Materiel;
import pidev.edu.gs.services.MaterielService;

/**
 * FXML Controller class
 *
 * @author pc 2017
 */
public class AjouterMaterielController implements Initializable {

    @FXML
    private TextField nomMateriel;
    @FXML
    private TextField descriptionMateriel;
    @FXML
    private ImageView iv;
      @FXML
    private Button imageEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public String handle(){
        FileChooser fileChooser = new FileChooser();//Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getName();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }  
       @FXML
    private void chooseAction(MouseEvent event) {
        imageEvent.setText(handle());
    }
    @FXML
    public void ajouterMateriel(){
        Materiel materiel = new Materiel();
        materiel.setNom(nomMateriel.getText());
        materiel.setDescription(descriptionMateriel.getText());
        materiel.setImage(imageEvent.getText());
        System.out.println(materiel);
        MaterielService materielService = new MaterielService();
        materielService.ajouter(materiel);
        precedent();
    }
    
    private void precedent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/panelAdmin.fxml"));
            Parent root = loader.load();

            PanelAdminController panelAdminController = loader.getController();
            nomMateriel.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }
}
