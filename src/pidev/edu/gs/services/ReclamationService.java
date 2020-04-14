package pidev.edu.gs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import pidev.edu.gs.entities.Reclamation;
import pidev.edu.gs.utils.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ReclamationService {


    Connection cnx = ConnectionBD.getInstance().getCnx();
    
 public void ajouter2 (Reclamation rec){

        String req="insert into reclamation (description,etat,type,date) values(?,?,?,?)";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
           // pst.setInt(1, rec.getId()); //id auto increment
            pst.setString(1, rec.getDescription());
            pst.setString(2, rec.getEtat());
            pst.setString(3, rec.getType()); 
            pst.setDate(4,  rec.getDate());
            pst.executeUpdate();
            System.err.println("Reclamation Ajoutee ...");
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }
 }
     
      public boolean delete(int id){
    String sql="DELETE FROM reclamation WHERE id="+id;
    Statement ste;
     try {
         ste = cnx.createStatement();
         ste.execute(sql);
         return true;
     } catch (SQLException ex) {
    	 System.out.println(ex.getMessage());
     }
     return false;
}
   public void supprimer (Reclamation rec){
	    
        String req ="delete from reclamation where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, rec.getId());
            System.out.println("reclamation supprimee...");
            pst.executeUpdate();
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }
   }  
 
   public List<Reclamation> afficher (){
       System.out.println("begin aff reclmation");
      // List<Reclamation> list = new ArrayList<>();
        ObservableList <Reclamation> list =FXCollections.observableArrayList();

       String req ="select * from reclamation";
           
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            //Reclamation c = new Reclamation(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
            Reclamation c =new Reclamation();
            c.setId(rs.getInt(1));
            c.setDescription(rs.getString(2));
            c.setEtat(rs.getString(3));
            c.setType(rs.getString(4));
            c.setDate(rs.getDate(5));
             list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        for (Reclamation reclamation : list) {
            System.out.println(reclamation);
       }
        System.out.println("end aff reclamation");
       return list;
       
   }
   public void modifier(Reclamation c) {
        try {
            String requete = "UPDATE reclamation SET description=?,etat=?,type=?,date=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, c.getId());
            pst.setString(1, c.getDescription());
           
            pst.setString(2, c.getEtat());
            pst.setString(3, c.getType());
            pst.setDate(4, c.getDate());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
