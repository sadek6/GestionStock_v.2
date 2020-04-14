package pidev.edu.gs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.gs.entities.Reponse;
import pidev.edu.gs.utils.ConnectionBD;

public class ReponseService {
  Connection cnx = ConnectionBD.getInstance().getCnx();
    
 public void AjouterRep (Reponse rep){

        String req = "insert into reponse (reponse,dateReponse) values(?,?)" ;
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
           // pst.setInt(1, rep.getId()); //id auto increment
            pst.setString(1, rep.getReponse());
            pst.setDate(2, rep.getDateReponse());
            pst.executeUpdate();
            System.err.println("Reponse Ajoutee ...");
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }
 }
     
      public boolean delete(int id){
    String sql="DELETE FROM reponse WHERE id="+id;
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
   public void supprimer (Reponse rec){
	    
        String req ="delete from reponse where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, rec.getId());
            System.out.println("reponse supprimee...");
            pst.executeUpdate();
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }
   }  
 
   public List<Reponse> afficher (){
       
      // List<Reclamation> list = new ArrayList<>();
	   ObservableList <Reponse> list =FXCollections.observableArrayList();

       String req ="select * from reponse";
           
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            Reponse c = new Reponse(rs.getInt(1),rs.getString(2),rs.getDate(3));
             list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return list;
       
   }
   public void modifier(Reponse c) {
        try {
            String requete = "UPDATE reponse SET reponse=?,dateReponse=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, c.getId());
            pst.setString(1, c.getReponse());
           
            pst.setDate(2, c.getDateReponse());
           
            pst.executeUpdate();
            System.out.println("Réponse modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
