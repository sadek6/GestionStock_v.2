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
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Mohamed
 */
public class ParticiperService {

    Connection cnx = ConnectionBD.getInstance().getCnx();

    public boolean isParticiper(int idUser, int idJeux) throws SQLException {

        String req = "select * from listeparticipant where idJeux = ? and idUser = ?;";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, idJeux);
        pst.setInt(2, idUser);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return false;
        }
        return true;

    }

    public void participer(int idUser, int idJeux) throws SQLException {
        if (isParticiper(idUser, idJeux)) {
            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
            String req = "insert into listeparticipant (idJeux, idUser) values(?, ?);";
            try {

                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, idJeux);
                pst.setInt(2, idUser);
                System.out.println(pst);
                pst.executeUpdate();
                jeuxConcoursService.incNbParticipants(idJeux);
                System.out.println("participation confirmée");
            } catch (SQLException ex) {
            }
        } else {
            System.out.println("déjà inscrit");
        }
    }

    public void quitter(int idUser, int idJeux) throws SQLException {
        if (!isParticiper(idUser, idJeux)) {
            JeuxConcoursService jeuxConcoursService = new JeuxConcoursService();
            String req = "delete from listeparticipant where idUser = ? and idJeux = ?;";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, idUser);
                pst.setInt(2, idJeux);
                pst.executeUpdate();
                jeuxConcoursService.decNbParticipants(idJeux);
                System.out.println("annuler la participation");
            } catch (SQLException ex) {
            }
        }
    }

}
