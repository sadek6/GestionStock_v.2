/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pidev.edu.gs.entities.PanierVendu;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class PanierVenduService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void add(PanierVendu panierVendu){
        
        String req = "insert into panier_vendu (produit_id, client_id, commande_id, quantite) values(?, ?, ?, ?);";
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, panierVendu.getIdProduit());
                pst.setInt(2, panierVendu.getIdClient());
                pst.setInt(3, panierVendu.getIdCommande());
                pst.setInt(4, panierVendu.getQuantite());
                pst.executeUpdate();
                System.out.println("panier vendu crée!");
            } catch (SQLException ex) {
            }
        
    }
    
}
