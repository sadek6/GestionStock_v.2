package pidev.edu.gs.entities;

import java.sql.Date;

public class Reclamation {
	 private int id ;
	 private String description;
	 private String etat ;
	 private String type ;
	 private Date date ;
         private String reponse;
	
	  
	   public Reclamation(int id, String description,Date date, String etat, String type, String reponse) {
	        this.id = id;
	        this.description =description ;
	        this.etat = etat;
	        this.type = type;
	        this.date = date;
	        
	    }
	   public Reclamation(String description,Date date, String etat, String type, String reponse) {
	        
	        this.description =description ;
	        this.etat = etat;
	        this.type = type;
	        this.date = date;
	      
	    }
	   public Reclamation(int id) {
		   this.id=id;
	       
	   }
   public Reclamation() {
       
   }
	   
	   
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getEtat() {
			return etat;
		}
		public void setEtat(String etat) {
			this.etat = etat;
		}
		public  Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", etat=" + etat + ", type=" + type + ", date=" + date + '}';
    }
		


}
