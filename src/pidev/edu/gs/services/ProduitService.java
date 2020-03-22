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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.utils.ConnectionBD;


/**
 *
 * @author Mohamed
 */
public class ProduitService {

    Connection myConnection = ConnectionBD.getInstance().getCnx();

    public ObservableList<Produit> afficher() {

        ObservableList<Produit> list = FXCollections.observableArrayList();
        String req = "select id,nom,prix,quantite from produit;";

        try {
            PreparedStatement pst = myConnection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
                //Personne p = new Personne(rs.getInt("id"), rs.getString(2), rs.getString(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("liste produit recupere");
        return list;

    }

    public int getProductPrice(int idProdduct) {

        String req = "select prix from produit where id = ? ";
        int price = 0;
        try {
            PreparedStatement pst = myConnection.prepareStatement(req);
            pst.setInt(1, idProdduct);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                price = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
}
