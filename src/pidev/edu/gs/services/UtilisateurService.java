/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;


import pidev.edu.gs.entities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author youssef
 */
public class UtilisateurService {
    
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;

    public UtilisateurService() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
       /////////////Ajouter///////////////////////
    // @Override
    public void Ajouteruser( Utilisateur p) throws SQLException {
 
                try {
       
            String req = "insert into user(`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `nom_image`) "
                    + "values('" + p.getId() + "','" + p.getUsername() + "','" + p.getUsernameCanonical()
                    + "','" + p.getEmail() + "','" + p.getEmailCanonical() + "','"+ p.getEnabled()+ "','" + p.getSalt() +
                    "','" + p.getPassword() +"','" + p.getLastLogin() +"','" + p.getConfirmationToken() +"','" + p.getPasswordRequestedAt() +"','" + p.getRoles() +"','" + p.getNomUser() + "','" + p.getPrenomUser()  +p.getImage()+"')";

            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      /////// supprimer //////
    
    /* public void supprimeruser(int id) {

        try {
            String req = "delete from user where id = " + id;

           
            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public void supprimer(Utilisateur t) {
            try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  }
    
   
    
      /////Modifier///////
     
     
     /* public void modifieruser( String username, String password , String email, int id  ) {
        try {
            String req = "update user set username='" + username
                    + "set password" + password + "set email" + email + "' where id='" + id + "'";
         
            try {
                stm = cnx.createStatement();
                
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
     
    public void modifier(Utilisateur t) {
        try {
            String requete = "update user set username='" + t.getNomUser()
                    + "set password" + t.getPassword() + "set email" + t.getEmail() + "' where id='" + t.getId() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getNomUser());
            pst.setString(2, t.getPassword());
            pst.setString(3,t.getEmail());
            pst.executeUpdate();
            System.out.println("Utilisateur modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }
       ///// Afficher tt les User////
    
      public List< Utilisateur> getuser(){
       //List< Utilisateur>  Utilisateur = new ArrayList<>(); 
ObservableList <Utilisateur> Utilisateur =FXCollections.observableArrayList();       
        String req = "select * from user ";
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
            Utilisateur u =new  Utilisateur(
                            rs.getInt("id"),
                           rs.getString("username"),
                           rs.getString("email"),
                           rs.getInt("enabled"),
                           rs.getString("Roles"),
                           rs.getString("nom_image"));      
              Utilisateur.add(u);                
        }
        
        return  Utilisateur;
    }
    catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        return  Utilisateur;
    } 
      
        ///// Afficher les admins////
    
      public List< Utilisateur> getadmins(){
       List< Utilisateur>  Utilisateur = new ArrayList<>();        
        String req = "select * from user where roles = a:1:{i:0;s:10:\"ROLE_AGENT\";} ";
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
            Utilisateur u =new  Utilisateur(
                           rs.getString("username"),
                           rs.getString("email"),
                           rs.getInt("enabled"),
                           rs.getString("Roles"),
                           rs.getString("image"));      
              Utilisateur.add(u);                
        }
    }
    catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        return  Utilisateur;
    }
        ///// Afficher tt les clients////
    
      public List< Utilisateur> getclient(){
       List< Utilisateur>  Utilisateur = new ArrayList<>();        
        String req = "select * from user where roles = a:1:{i:0;s:11:\"ROLE_CLIENT\";} ";
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
            Utilisateur u =new  Utilisateur(
                           rs.getString("username"),
                           rs.getString("email"),
                           rs.getInt("enabled"),
                           rs.getString("Roles"),
                           rs.getString("image"));      
              Utilisateur.add(u);                
        }
    }
    catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        return  Utilisateur;
    }
      
        ///////// Recherche par nom /////////

 public List<Utilisateur> chercheruser(String nom) {
        List<Utilisateur> Utilisateur = new ArrayList();
        String req = "select * from user where nom = " + nom;
        try {
            stm = cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {    
                   Utilisateur u =new  Utilisateur(
                           rs.getString("username"),
                           rs.getString("email"),
                           rs.getInt("enabled"),
                           rs.getString("Roles"),
                           rs.getString("image"));      
              Utilisateur.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Utilisateur;
    }  
         ///////// Recherche par id /////////

 public List<Utilisateur> chercheruser2(int id) {
        List<Utilisateur> Utilisateur = new ArrayList();
        String req = "select * from user where id = " + id;
        try {
            stm = cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {    
                   Utilisateur u =new  Utilisateur(
                           rs.getString("username"),
                           rs.getString("email"),
                           rs.getInt("enabled"),
                           rs.getString("Roles"),
                           rs.getString("image"));      
              Utilisateur.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Utilisateur;
    }
 
 public List<Utilisateur> getAllUser(){
        List<Utilisateur> ListP = new ArrayList<>();
        try {
            String requete = "SELECT id,username,email,enabled FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                Utilisateur p = new Utilisateur();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setEnabled(rs.getInt(4));
                ListP.add(p);
                
            }


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return ListP;
    }
 public Utilisateur seConnecter(String login) throws SQLException {

        Utilisateur utilisateur = new Utilisateur();

        String req = "Select * from user WHERE username='" + login + "'or email='" + login + "'";
        Statement statement = ConnectionBD.getInstance().getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(req);

        while (resultSet.next()) {
            utilisateur.setId(resultSet.getInt("id"));
            utilisateur.setUsername(resultSet.getString("username"));
            utilisateur.setPassword(resultSet.getString("password"));
            utilisateur.setRoles(resultSet.getString("roles"));
        }
        
        return utilisateur;
    }
 
}

 
