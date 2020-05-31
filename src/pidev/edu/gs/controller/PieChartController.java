/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.controller;

import pidev.edu.gs.entities.Livraison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import pidev.edu.gs.services.LivraisonContoller;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PieChartController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButton1Action(ActionEvent event) {
         LivraisonContoller ps = new LivraisonContoller();
  Livraison l=new Livraison();
           String relais="point a relais";
           String domicile="a domicile";
           String adresse="par adresse";
           
         String ch = "point a relais ("+Integer.toString(ps.getnbrdeniveau(relais)*100/ps.getnbrdelivraison())+" % )";
         String ch1 = "a domicile ("+Integer.toString(ps.getnbrdeniveau(domicile)*100/ps.getnbrdelivraison())+" % )";
         String ch2 = "par adresse("+Integer.toString(ps.getnbrdeniveau(adresse)*100/ps.getnbrdelivraison())+" % )";
        
       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              
             new PieChart.Data(ch, ps.getnbrdeniveau(relais)*100/ps.getnbrdelivraison()),
             new PieChart.Data(ch1, ps.getnbrdeniveau(domicile)*100/ps.getnbrdelivraison()),
             new PieChart.Data(ch2,ps.getnbrdeniveau(adresse)*100/ps.getnbrdelivraison() )
             
             
         );
       
         
        piechart.setTitle("Statistiques des livraisons");
        piechart.setData(pieChartData);
    }
        
    
    
}
