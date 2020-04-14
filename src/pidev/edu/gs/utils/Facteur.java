/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pidev.edu.gs.entities.Commande;

/**
 *
 * @author Mohamed
 */
public class Facteur {
    
    public static void facteur(Commande commande) throws FileNotFoundException, DocumentException{
        
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Mohamed/Desktop/test/test"+commande.getId()+".pdf"));
        document.open();
        document.add(new Paragraph("Facteur"));
        document.add(new Paragraph("Prix total = "+commande.getPrixTotale()));
        document.add(new Paragraph("----------------------------------------"));
        
        Connection cnx = ConnectionBD.getInstance().getCnx();
        String req = "SELECT P.nom, P.prix FROM panier_vendu AS PV JOIN produit AS P ON PV.produit_id = P.id WHERE PV.commande_id = ? ;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commande.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                document.add(new Paragraph("Nom produit : "+ rs.getString(1)));
                document.add(new Paragraph("Prix produit : "+ rs.getInt(2)));
                document.add(new Paragraph("----------------------------------------"));
            }
        } catch (SQLException ex) {
        }
        
        document.close();
        System.out.println("end pdf generate");
    }
    
}
