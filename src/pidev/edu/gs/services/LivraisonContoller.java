/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;

import entites.Livraison;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.gs.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class LivraisonContoller implements IService<Livraison> {
   
      Connection cnx=ConnectionBD.getInstance().getCnx();
       private Connection con;
    private Statement ste;


  
    @Override
     public void ajouter(Livraison l){
     String req="insert into livraison(adresse,niveau,commandeass,periode,nomch,datedelivraison) values ('"+l.getAdresse()+"',"
             + "'"+l.getNiveau()+"','"+l.getCommandeass()+"','"+l.getPeriode()+"','"+l.getNomch()+"','"+l.getDatedelivraison()+"')";
    try{
     Statement st =cnx.createStatement();
     st.executeUpdate(req);
             }catch(SQLException e){
                 System.out.println(e.getMessage());
             }}
        public boolean delete(int id){
        String sql="DELETE FROM livraison WHERE id="+id;
        Statement s;
         try {
             ste = cnx.createStatement();
             ste.execute(sql);
             return true;
         } catch (SQLException ex) {
             Logger.getLogger(LivraisonContoller.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
    }
     
         @Override
       public void supprimer(Livraison l){
       String req ="delete from livraison where id=?";
       try{
           PreparedStatement pst =cnx.prepareStatement(req);
           pst.setInt(1,l.getId());
           System.out.println("livraison supprimé");
           pst.executeUpdate();
       
       }catch(SQLException e){}
        
       }
@Override
    public void modifier(Livraison t) {
        try {
            String requete = "UPDATE livraison SET adresse=?,niveau=?,commandeass=?,periode=?,nomch=?,datedelivraison WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getId());
            pst.setString(1, t.getAdresse());
            pst.setString(2, t.getNiveau());
            pst.setInt(3, t.getCommandeass());
            pst.setString(4, t.getPeriode());
            pst.setInt(5, t.getNomch());
            pst.setDate(6, t.getDatedelivraison());
           

            
            pst.executeUpdate();
            System.out.println("Personne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Livraison> afficher() {
        List<Livraison> list =new ArrayList<>();
       String req="select * from livraison";
       try{
           PreparedStatement pst =cnx.prepareStatement(req);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
   Livraison p = new Livraison(rs.getInt("id"),rs.getString("adresse"),rs.getString("niveau"),
           rs.getInt("commandeass"),rs.getString("periode"),rs.getInt("nomch"),rs.getDate("datedelivraison"));
               list.add(p);
           }}catch(SQLException e){
           System.out.println(e.getSQLState());
       }
       
       
       return list; }
      
  /* public List<Livraison> RechercheLivraisonParDate() throws SQLException {
        List<Livraison> list=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
     while (rs.next()) {                
               Integer idliv=rs.getInt("idliv");
               String destination=rs.getString("destination");
               String etatl=rs.getString("etatl");
               Integer idc=rs.getInt("idc");
               Integer idl=rs.getInt("idl");
               Livraison p=new Livraison(idliv, destination, etatl, idc, idl);
     list.add(p);
     }
    return list; 
    }*/
    @Override
     public List<Livraison> RechercheLivraisonParDate() {
                 List<Livraison> list=new ArrayList<>();
String s="select * from livraison liv,chauffeur c WHERE c.numpass=liv.nomch ORDER BY liv.datedelivraison ASC";
        try{
          PreparedStatement pst =cnx.prepareStatement(s);
   
    ResultSet rs=pst.executeQuery();
    
     while (rs.next()) {                
               Integer id=rs.getInt("id");
               String adresse=rs.getString("adresse");
               String niveau=rs.getString("niveau");
               Integer commandeass=rs.getInt("commandeass");
               String periode=rs.getString("periode");
               Integer nomch=rs.getInt("nomch");
               Date datedelivraison=rs.getDate("datedelivraison");
               Livraison p=new Livraison(id,adresse,niveau,commandeass,periode,nomch,datedelivraison);
     list.add(p);}
     }
    catch(SQLException e){
           System.out.println("erreur");}
    return list; 
    }

 

 

    @Override
    public List<Livraison> getChauffeurByNum(int numpass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       public boolean ModifierEvenenmentPlace(Livraison t) throws SQLDataException {
        String query = "UPDATE livraison SET adresse=?,niveau=?,commandeass=?,periode=?,nomch=?,datedelivraison=? WHERE id=?";
		PreparedStatement pst;
        try {
                pst = cnx.prepareStatement(query);
            
                pst.setInt(7, t.getId());
            pst.setString(1, t.getAdresse());
            pst.setString(2, t.getNiveau());
            pst.setInt(3, t.getCommandeass());
            pst.setString(4, t.getPeriode());
            pst.setInt(5, t.getNomch());
            pst.setDate(6, t.getDatedelivraison());
                pst.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonContoller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                    }
}


    

