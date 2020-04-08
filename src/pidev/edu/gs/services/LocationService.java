/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.entities.Location;
import pidev.edu.gs.entities.Materiel;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author pc 2017
 */
public class LocationService {
  
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter(Location location){
        String requete = "insert into location (DateDebut,DateFin,Materiel) values (?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, location.getDateDebut());
            pst.setDate(2, location.getDateDebut());
            pst.setInt(3, location.getIdMateriel());
            System.out.println(pst);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
    }
   

     public ObservableList<Location> afficher(){
        ObservableList<Location> list = FXCollections.observableArrayList();
        String req = "select * from location  ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Location materiel = new Location(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4));
                        System.out.println(materiel.getIdMateriel());

                list.add(materiel);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste commandes admin recupere");
        return list;
    }



   /* public List<Location> afficher() {
ObservableList <Location> list =FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM location";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }    
   */ 

     
    public void supprimerLocation(int idLocation){
        String req = "delete from location where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idLocation);
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("materiel supprimée!");
        } catch (SQLException ex) {
        }
    } 
    

    public void supprimer(String id)
         {
                
             String requete="DELETE FROM location WHERE id='"+id+"' ";     
             Statement st;
             try {
              st = cnx.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Location supprimé");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

    public void modifier(Location t) {
        try {
            String requete = "UPDATE location SET DateDebut=?,DateFin=?,Materiel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, t.getDateDebut());
            pst.setDate(2, t.getDateFin());
            pst.setInt(3, t.getIdMateriel());
            pst.setInt(4,t.getId());
            pst.executeUpdate();
            System.out.println("Location modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }























    
}
