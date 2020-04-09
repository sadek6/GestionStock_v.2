package pidev.edu.gs.services;

import pidev.edu.gs.entities.Categorie;
import pidev.edu.gs.utils.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie {



    Connection cnx = ConnectionBD.getInstance().getCnx();


    public void ajouter(Categorie cat) {
        try {
            String requete = "INSERT INTO categorie (nom_categorie,description) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, cat.getNomCategorie());
            pst.setString(2, cat.getDescription());
            pst.executeUpdate();
            System.out.println("Catégorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Categorie t) {
        try {
            String requete = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void modifier(Categorie t) {
        try {
            String requete = "UPDATE categorie SET nom_categorie=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId());
            pst.setString(1, t.getNomCategorie());
            pst.setString(2, t.getDescription());
            pst.executeUpdate();
            System.out.println("Catégorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Categorie> afficher() {
        List<Categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Categorie(rs.getInt(1), rs.getString(2), rs.getString("nom_categorie")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }








}
