package pidev.edu.gs.entities;

import java.sql.Date;

public class Reponse {
      private int id ;
      private String reponse;
      private Date dateReponse;

    public Reponse(int id, String reponse, Date dateReponse) {
        this.id = id;
        this.reponse = reponse;
        this.dateReponse = dateReponse;
    }
   public Reponse()
   {
       
   }
   public Reponse( int id)
   { this.id=id;
   }
    public Reponse(String reponse, Date dateReponse) {
        this.reponse = reponse;
        this.dateReponse = dateReponse;
    }

    public int getId() {
        return id;
    }

    public String getReponse() {
        return reponse;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", reponse=" + reponse + ", dateReponse=" + dateReponse + '}';
    }
     
   
      
}
