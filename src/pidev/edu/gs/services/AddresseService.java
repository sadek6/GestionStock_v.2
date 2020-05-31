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
import pidev.edu.gs.entities.Addresse;
import pidev.edu.gs.entities.Commande;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class AddresseService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    //ok
    public void add(Addresse addresse){
        
        String req = "insert into addresse (id, numTel, mail, pays, ville, pinCode) values(?, ?, ?, ?, ?, ?);";
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, addresse.getId());
                pst.setInt(2, addresse.getNumTel());
                pst.setString(3, addresse.getMail());
                pst.setString(4, addresse.getPays());
                pst.setString(5, addresse.getVille());
                pst.setInt(6, addresse.getPinCode());
                pst.executeUpdate();
                System.out.println("addresse crée!");
            } catch (SQLException ex) {
            }
    }
    
    public Addresse findAdresse(int idAddresse) throws SQLException{
        Addresse addresse = new Addresse();
        String req = "SELECT * FROM addresse WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, idAddresse);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            addresse.setId(rs.getInt(1));
            addresse.setNumTel(rs.getInt(2));
            addresse.setMail(rs.getString(3));
            addresse.setPays(rs.getString(4));
            addresse.setVille(rs.getString(5));
            addresse.setPinCode(rs.getInt(6));
            return addresse;
        }
        return null;
    }
    
    public void modifierAdresse(Addresse addresse){
        String req = "update addresse set numTel = ?, mail = ?, pays = ?, ville = ?, pinCode = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, String.valueOf(addresse.getNumTel()));
            pst.setString(2, addresse.getMail());
            pst.setString(3, addresse.getPays());
            pst.setString(4, addresse.getVille());
            pst.setInt(5, addresse.getPinCode());
            pst.setInt(6, addresse.getId());
            System.out.println("ok1");
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("ok2");
            System.out.println("modification adresse ok ");
        } catch (SQLException ex) {
        }
    }
}
