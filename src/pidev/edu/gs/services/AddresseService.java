/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pidev.edu.gs.entities.Addresse;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class AddresseService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
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
}
