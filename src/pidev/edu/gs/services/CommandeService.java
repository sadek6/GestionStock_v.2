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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class CommandeService {

    Connection cnx = ConnectionBD.getInstance().getCnx();

    public void createCommande(Commande commande) {

        String req = "insert into commande (id, client_id, prixtotal, etat, addresse_id) values(?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commande.getId());
            pst.setInt(2, commande.getIdClient());
            pst.setInt(3, commande.getPrixTotale());
            pst.setString(4, commande.getEtat());
            pst.setInt(5, commande.getAdresse());
            pst.executeUpdate();
            System.out.println("commande crée!");
        } catch (SQLException ex) {
        }
    }

    public ObservableList<Commande> afficher(int idUser) {

        ObservableList<Commande> list = FXCollections.observableArrayList();
        String req = "select * from commande where client_id = ?;";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idUser);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande p = new Commande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                p.setAdresse(rs.getInt(5));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste commandes recupere");

        return list;
    }
    
    public ObservableList<Commande> afficherAdmin(){
        ObservableList<Commande> list = FXCollections.observableArrayList();
        String req = "select c.id, c.prixtotal, c.etat , u.username from commande c inner join user u on u.id = c.client_id;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande p = new Commande(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste commandes admin recupere");
        return list;
    }
    
    public ObservableList<Commande> chercher(String mot){
        ObservableList<Commande> list = FXCollections.observableArrayList();
        String req = "select c.id, c.prixtotal, c.etat , u.username from commande c inner join user u on u.id = c.client_id where u.username like '"+mot+"%' or c.id like '"+mot+"%' or c.prixtotal like '"+mot+"%' or c.etat like '"+mot+"%';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande p = new Commande(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste commandes admin recherche recupere");
        return list;
    }
    
    public void remove(int idCommande){
        
        String req = "delete from commande where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idCommande);
            pst.executeUpdate();
            System.out.println("commade supprimée!");
        } catch (SQLException ex) {
        }
    }
    
    public void traiter(int idCommande){
        
        String req = "update commande set etat = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, "traite");
            pst.setInt(2, idCommande);
            pst.executeUpdate();
            System.out.println("commade traite!");
        } catch (SQLException ex) {
        }
    }
    
    public int CountParcByCateg(String c) throws SQLException {  
     
     int count=0;
    String requete="SELECT count(*) from commande where etat='"+c+"'";
    Statement st;
    st = cnx.createStatement();
   try {
              st = cnx.createStatement(); 
              ResultSet rs = st.executeQuery(requete);
              while (rs.next()){
            count = rs.getInt("count(*)");
              }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return count;
        

      
 } 
    
     
}
