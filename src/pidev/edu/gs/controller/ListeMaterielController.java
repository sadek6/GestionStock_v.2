/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import pidev.edu.gs.entities.Materiel;
import pidev.edu.gs.services.MaterielService;

/**
 * FXML Controller class
 *
 * @author pc 2017
 */
public class ListeMaterielController implements Initializable {

    @FXML
    private AnchorPane containerMateriel;
    @FXML
    private TableView<Materiel> listeMateriel;
    @FXML
    private TableColumn<Materiel, String> nomMateriel;
    @FXML
    private TableColumn<Materiel, String> imageMateriel;
    @FXML
    private TableColumn<Materiel, String> descriptionMateriel;
    @FXML
    private TableColumn supprimer;
    
    private ObservableList<Materiel> list;
    @FXML
    private TextField newNomMateriel;
     @FXML
    private ImageView iv;
    @FXML
    private TextField newDescriptionMateriel;
    @FXML
    private Label newId;
    @FXML
    private Button valider;
    @FXML
    private TextField mot;
    @FXML
    private Button imageEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newId.setVisible(false);
        showHide(false);
        populateTableView();
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
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }  
    @FXML
       private void uploadImage(ActionEvent event) {
        imageEvent.setText(handle());

    }

    private void populateTableView() {
        MaterielService materielService = new MaterielService();
        list = materielService.afficherMateriel();
        
        nomMateriel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        imageMateriel.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        descriptionMateriel.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        Callback<TableColumn<Materiel, String>, TableCell<Materiel, String>> cellFactoryRemove = (param) -> {

            final TableCell<Materiel, String> cell = new TableCell<Materiel, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Materiel p = getTableView().getItems().get(getIndex());
                            MaterielService materielService1 = new MaterielService();

                            materielService.supprimerMateriel(p.getId());
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
        listeMateriel.setItems(list);
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher materiel");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/gs/gui/listeMateriel.fxml"));
        Parent root = loader.load();
        containerMateriel.getChildren().setAll(root);
    }
    
    public void showHide(boolean b) {
        newNomMateriel.setVisible(b);
      imageEvent.setVisible(b);

        newDescriptionMateriel.setVisible(b);
        valider.setVisible(b);
    }
    
    @FXML
    public void getMateriel() throws SQLException {
        System.out.println("begin get m");
        Materiel materiel = new Materiel();
        materiel = listeMateriel.getSelectionModel().getSelectedItem();
        System.out.println(materiel);
        showHide(true);
        newId.setText(String.valueOf(materiel.getId()));
        newNomMateriel.setText(String.valueOf(materiel.getNom()));
        imageEvent.setText(materiel.getImage());

        newDescriptionMateriel.setText(materiel.getDescription());
        System.out.println("end get m");

    }
    
    @FXML
    public void modifierMateriel() throws IOException {

        
            Materiel materiel = new Materiel();
            materiel.setId(Integer.parseInt(newId.getText()));
            materiel.setNom(newNomMateriel.getText());
           materiel.setImage(imageEvent.getText());

            materiel.setDescription(newDescriptionMateriel.getText());
            System.out.println("begin mod @");
            MaterielService materielService = new MaterielService();
            materielService.modifierMateriel(materiel);
                        showHide(false);
refresh();
        
    }
    
    @FXML
    private void chercher(KeyEvent event) {
        System.out.println(mot.getText());
        MaterielService materielService = new MaterielService();
        list = materielService.chercher(mot.getText());
       
        
        nomMateriel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        imageMateriel.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionMateriel.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        listeMateriel.setItems(list);
    }

    
    
}
