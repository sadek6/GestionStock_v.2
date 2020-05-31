package pidev.edu.gs.entities;

import java.sql.Date;

public class Reclamation {
	 private int id ;
	 private String description;
	 private String etat ;
	 private String type ;
	 private String date ;
         private int idUser;
         private int reponse;
         private String Rep;

    public Reclamation(int id, String description, String date, String etat, String type, int reponse) {
        
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.date = date;
        this.reponse = reponse;
       
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    public Reclamation(String description, String etat, String type, String date, int reponse, String Rep) {
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.date = date;
        this.reponse = reponse;
        this.Rep = Rep;
    }

    public Reclamation(String description, String etat, String type, String date, int idUser, int reponse, String Rep) {
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.date = date;
        this.idUser = idUser;
        this.reponse = reponse;
        this.Rep = Rep;
    }

    public Reclamation(int id, String description, String etat, String type, String date, int idUser, int reponse, String Rep) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.date = date;
        this.idUser = idUser;
        this.reponse = reponse;
        this.Rep = Rep;
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
		public  String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

    public int getReponse() {
        return reponse;
    }

         
    public String getRep() {
        return Rep;
    }

   

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public void setReponse(String Reponse) {
        this.Rep = Reponse;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "description=" + description + ", etat=" + etat + ", type=" + type + ", date=" + date + ", idUser=" + idUser + ", reponse=" + reponse + ", Rep=" + Rep + '}';
    }

 

   

  

    
		


}
