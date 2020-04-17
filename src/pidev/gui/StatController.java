/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev.edu.gs.services.CommandeService;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class StatController implements Initializable {

    @FXML
    private AnchorPane container_client;
    private Button button;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private Text nombret;
    @FXML
    private Text nombre;
    @FXML
    private PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                try {

                    
                    
                    //User u= Session.getLoggedInUser();
                    //userName.setText(u.getUsername());
                    CommandeService m=null;
                    m = new CommandeService();
                    
                    
                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(
                                   // new PieChart.Data("Total", m.CountParc()),
                                    new PieChart.Data("traite", m.CountParcByCateg("traite")),
                                    new PieChart.Data("en cours", m.CountParcByCateg("en cours")));

                    
                    chart.getData().addAll(pieChartData);
                    
                    
                    final Label caption = new Label("");
                    caption.setTextFill(Color.DARKORANGE);
                    caption.setStyle("-fx-font: 24 arial;");
                    Tooltip container = new Tooltip();
                    container.setGraphic(caption);
                    
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isShowing())
                                {
                                    container.hide();
                                }
                                
                                caption.setText(String.valueOf((int)data.getPieValue()));
                                container.show(stage, e.getScreenX(), e.getScreenY());
                                });
                    });
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_EXITED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isHideOnEscape())
                                {
                                    container.hide();
                                }
                                
                           
                                });
                    });
                } catch (SQLException ex) {
                    Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        
        
        
        
        // TODO
    }    


    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void mouseClick(MouseEvent event) {
    }

    
}
