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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.entities.Materiel;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author pc 2017
 */
public class MaterielService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter(Materiel material){
        String requete = "insert into materiel (nom,image,description) values (?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, material.getNom());
            pst.setString(2, material.getImage());
            pst.setString(3, material.getDescription());
            System.out.println(pst);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    public ObservableList<Materiel> afficherMateriel(){
        ObservableList<Materiel> list = FXCollections.observableArrayList();
        String req = "select * from materiel ;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Materiel materiel = new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(materiel);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste commandes admin recupere");
        return list;
    }
    
    public void supprimerMateriel(int idMateriel){
        String req = "delete from materiel where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idMateriel);
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("materiel supprimée!");
        } catch (SQLException ex) {
        }
    }
    
    public void modifierMateriel(Materiel materiel){
        String req = "update materiel set Nom = ?, image = ?, description = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, materiel.getNom());
            pst.setString(2, materiel.getImage());
            pst.setString(3, materiel.getDescription());
            pst.setInt(4, materiel.getId());
            System.out.println("ok1");
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("ok2");
            System.out.println("modification materiel ok ");
        } catch (SQLException ex) {
        }
    }
    
    public ObservableList<Materiel> chercher(String mot){
        ObservableList<Materiel> list = FXCollections.observableArrayList();
        String req = "select * from materiel where nom like '"+mot+"%' or description like '"+mot+"%';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Materiel materiel = new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(materiel);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste materiel recherche recupere");
        return list;
    }
    
}
