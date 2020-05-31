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
           //pst.setInt(1, rec.getId()); //id auto increment
            pst.setString(1, rec.getDescription());
            pst.setString(2, rec.getEtat());
            pst.setString(3, rec.getType()); 
            pst.setString(4,  rec.getDate());
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
       String desc="Pas de reponse";
        // List<Reclamations> list = new ArrayList<>();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
//ObservableList <Reclamations> list2 =FXCollections.observableArrayList();
        ReponseService rps = new ReponseService();
        try {
            String req = "SELECT id,description,etat,type,date,idUser,reponse FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
          
            while (rs.next()) {
                 if(rs.getInt(7)!=0)
                 {   String req2="Select reponse from reponse where id="+rs.getInt(7);
                     
                   PreparedStatement pst2 = cnx.prepareStatement(req2);
                    ResultSet rs2 = pst2.executeQuery();
                     
                     System.out.println("aaaaaaa"); 
                    
                    while(rs2.next())
                    {    desc=rs2.getString("reponse") ; 
                    System.out.println(desc+"22222");}
                 }
                Reclamation r = new Reclamation(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),desc);
           // desc="Pas de reponse";  /// 
            System.out.println(desc+"33");
            //r.setReponse(desc);
                list.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }/*
            for(Reclamations r:list){
                if(r.getReponseid()==0){
                    r.setReponse("Pas du Reponse");
                    System.out.println(r);
                }
                list2.add(r);
            }*/
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
            pst.setString(4, c.getDate());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
