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
import pidev.edu.gs.entities.JeuxConcours;
import pidev.edu.gs.entities.Utilisateur;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class JeuxConcoursService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void addJeuxConcours(JeuxConcours jeuxConcours){
        
        String req = "insert into jeuxconcours (nomJeux, prix, nbParticipants, etat) values(?, ?, ?, ?);";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, jeuxConcours.getNomJeux());
            pst.setInt(2, jeuxConcours.getPrix());
            pst.setInt(3, jeuxConcours.getNbParticipants());
            pst.setInt(4, jeuxConcours.getEtat());
            pst.executeUpdate();
            System.out.println("Jeux concours crée!");
        } catch (SQLException ex) {
        }
    }
    
    public void supprimerJeuxConcours(int id){
        String req = "delete from jeuxconcours where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("jeux concours supprimée!");
        } catch (SQLException ex) {
        }
    }
    
    public ObservableList<JeuxConcours> afficher() {

        ObservableList<JeuxConcours> list = FXCollections.observableArrayList();
        String req = "select * from jeuxconcours;";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                JeuxConcours p = new JeuxConcours(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste jeux concours recupere");

        return list;
    }
    
    public ObservableList<JeuxConcours> afficherJeuxDispo() {

        ObservableList<JeuxConcours> list = FXCollections.observableArrayList();
        String req = "select * from jeuxconcours;";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                JeuxConcours p = new JeuxConcours(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                if(p.getEtat() == 0)
                    list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste jeux concours recupere");

        return list;
    }
    
    public void modifierJeuxConcours(JeuxConcours jeuxConcours){
        System.out.println(jeuxConcours);
        String req = "update jeuxconcours set nomJeux = ?, prix = ?, nbParticipants = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, jeuxConcours.getNomJeux());
            pst.setInt(2, jeuxConcours.getPrix());
            pst.setInt(3, jeuxConcours.getNbParticipants());
            pst.setInt(4, jeuxConcours.getId());
            pst.executeUpdate();
            System.out.println(pst);
            System.out.println(pst);
            System.out.println("modification ok ");
        } catch (SQLException ex) {
        }
    }
    
    public void modifierJeuxConcoursT(JeuxConcours jeuxConcours){
        System.out.println(jeuxConcours);
        String req = "update jeuxconcours set nomJeux = ?, prix = ?, nbParticipants = ?, etat = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, jeuxConcours.getNomJeux());
            pst.setInt(2, jeuxConcours.getPrix());
            pst.setInt(3, jeuxConcours.getNbParticipants());
            pst.setInt(4, jeuxConcours.getEtat());
            pst.setInt(5, jeuxConcours.getId());
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println(pst);
            System.out.println("modification ok ");
        } catch (SQLException ex) {
        }
    }
    
    JeuxConcours findJeux(int idJeux){
        JeuxConcours jeuxConcours = new JeuxConcours();
        String req = "select * from jeuxconcours where id = ? ;";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idJeux);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                jeuxConcours = new JeuxConcours(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
        }
        System.out.println("jeux concour trouve");
        System.out.println(jeuxConcours);
        return jeuxConcours;
    }
    
    public void incNbParticipants(int idJeux){
        JeuxConcours jeuxConcours = findJeux(idJeux);
        jeuxConcours.setNbParticipants(jeuxConcours.getNbParticipants()+1);
        modifierJeuxConcours(jeuxConcours);
    }
    
    public void decNbParticipants(int idJeux){
        JeuxConcours jeuxConcours = findJeux(idJeux);
        jeuxConcours.setNbParticipants(jeuxConcours.getNbParticipants()-1);
        modifierJeuxConcours(jeuxConcours);
    }
    
    public ObservableList<Utilisateur> getUtilisateursJeux(int idJeux){  
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        String req = "SELECT LP.idUser FROM jeuxconcours AS JC JOIN listeparticipant AS LP ON JC.id = LP.idJeux WHERE JC.id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idJeux);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
                Utilisateur utilisateur = gestionUtilisateur.findUser(rs.getInt(1));
                list.add(utilisateur);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste utilisateur inscrit dans le jeux recupere");
        return list;
    }
    
}
