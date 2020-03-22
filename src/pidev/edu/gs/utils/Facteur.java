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
        document.add(new Paragraph("hello"));
        document.add(new Paragraph(commande.toString()));
        document.close();
        System.out.println("end pdf generate");
    }
    
}
