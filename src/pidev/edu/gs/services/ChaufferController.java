/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.services;



import pidev.edu.gs.entities.Chauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ChaufferController implements IService<Chauffeur> {
    
     Connection cnx= ConnectionBD.getInstance().getCnx();
    
       private Connection con;
    private Statement ste;
     

    @Override
     public void ajouter(Chauffeur c){
      
     String req="insert into chauffeur(numpass,nom_image,Prenom,niveau,numerotel,Nom,nbr,datenain) values ('"+c.getNumpass()+"',"
             + "'"+c.getNom_image()+"','"+c.getPrenom()+"','"+c.getNiveau()+"','"+c.getNumerotel()+"','"+c.getNom()+"','"+0+"','"+c.getDatenain()+"')";
     
       

    try{
     Statement st =cnx.createStatement();
     st.executeUpdate(req);
             }catch(SQLException e){
                 System.out.println(e.getMessage());
             }}
        @Override
       public void supprimer(Chauffeur c){
       String req ="delete from chauffeur where numpass=?";
       try{
           PreparedStatement pst =cnx.prepareStatement(req);
           pst.setInt(1,c.getNumpass());
           System.out.println("chauffeur supprimé");
           pst.executeUpdate();
       
       }catch(SQLException e){}
        
       }
@Override
    public void modifier(Chauffeur c ) {
         Connection cnx=ConnectionBD.getInstance().getCnx();
    
        try {
            String requete = "UPDATE chauffeur SET nom_image=?,Prenom=?,niveau=?,numerotel=?,Nom=?,nbr=?,datenain=? WHERE numpass=?";
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(8, c.getNumpass());
            
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getNiveau());
            pst.setInt(4, c.getNumerotel());
            pst.setString(5, c.getNom());
           pst.setInt(6, c.getNbr());
           pst.setDate(7, c.getDatenain());

           

            
            pst.executeUpdate();
            System.out.println("Chauffeur modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Chauffeur> afficher() {
        List<Chauffeur> liste =new ArrayList<>();
       String req="select * from chauffeur";
       try{
           PreparedStatement pst =cnx.prepareStatement(req);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
   Chauffeur p = new Chauffeur(rs.getInt("numpass"),rs.getNString("nom_image"),rs.getString("Prenom"),
           rs.getString("niveau"),rs.getInt("numerotel"),rs.getString("Nom"),rs.getInt("nbr"),rs.getDate("datenain"));
               liste.add(p);
           }}catch(SQLException e){
           System.out.println(e.getSQLState());
       }
       
       
       return liste; }
    @Override
       public List<Chauffeur> getChauffeurByNum(int numpass){
           List<Chauffeur> list=new ArrayList<>();
        String sql="Select * from chauffeur where numpass="+numpass;
                         

         try{
           PreparedStatement pst =cnx.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
   Chauffeur p = new Chauffeur(rs.getInt("numpass"),rs.getString("nom_image"),rs.getString("Prenom"),
           rs.getString("niveau"),rs.getInt("numerotel"),rs.getString("Nom"),rs.getInt("nbr"),rs.getDate("datenain"));
               list.add(p);
           }}catch(SQLException e){
           System.out.println(e.getSQLState());
            
       } return list;
    }


    @Override
    public List<Chauffeur> RechercheLivraisonParDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean supp(int id){
        String sql="DELETE FROM chauffeur WHERE numpass="+id;
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
   public void count(int numpass ){
         
           try {
            String requete = "update chauffeur set nbr = nbr+1 where numpass = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           
            pst.setInt(1, numpass);
            pst.executeUpdate();
           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         
    
    }
   public void counte(int numpass ){
         
           try {
            String requete = "update chauffeur set nbr = nbr-1 where numpass = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           
            pst.setInt(1, numpass);
            pst.executeUpdate();
           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         
    
    }
   }
    
    

