/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;

import pidev.edu.gs.entities.Produit;
import pidev.edu.gs.utils.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mohamed
 */
public class ProduitService {

    Connection myConnection = ConnectionBD.getInstance().getCnx();

    public List<Produit> afficher() {

     List<Produit> list = new ArrayList<>();
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

    public Produit getProduct(int idProdduct) {

        String req = "select nom, prix, quantite from produit where id = ? ";
        Produit p = null;
        try {
            PreparedStatement pst = myConnection.prepareStatement(req);
            pst.setInt(1, idProdduct);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p= new Produit(rs.getInt(1), rs.getString(2),rs.getFloat(3),
                        rs.getInt(4));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }



    public void ajouter(Produit prod) {
        try {
            String requete = "INSERT INTO produit (nom,prix,quantite) VALUES (?,?,?)";
            PreparedStatement pst = myConnection.prepareStatement(requete);
            pst.setString(1, prod.getNom());
            pst.setFloat(2, prod.getPrix());
            pst.setInt(3, prod.getQuantite());
            pst.executeUpdate();
            System.out.println("Produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }



    public void modifier(Produit p) {
        try {
            String requete = "UPDATE produit SET nom=?,quantite=?,prix=? WHERE id=?";
            PreparedStatement pst = myConnection.prepareStatement(requete);
            pst.setInt(4, p.getId());
            pst.setString(1, p.getNom());
            pst.setInt(2, p.getQuantite());
            pst.setFloat(3, p.getPrix());
            pst.executeUpdate();
            System.out.println("Produit modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Produit p) {
        try {
            String requete = "DELETE FROM produit WHERE id=?";
            PreparedStatement pst = myConnection.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Produit> getAllProduit(){
        List<Produit> ListP = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,prix,quantite FROM produit";
            PreparedStatement pst = myConnection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrix(rs.getFloat(3));
                p.setQuantite(rs.getInt(4));
                ListP.add(p);
            }


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return ListP;
    }




}
