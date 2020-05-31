/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;


import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Livraison {
    
    private int id ;
    private String adresse;
    private String niveau;
    private int commandeass;
    private String periode;
    private int nomch;
    private Date datedelivraison ;
    private String Nom;

   

    public Livraison(int id, String adresse, String niveau, int commandeass, String periode, int nomch, Date datedelivraison) {
        this.id = id;
        this.adresse = adresse;
        this.niveau = niveau;
        this.commandeass = commandeass;
        this.periode = periode;
        this.nomch = nomch;
        this.datedelivraison = datedelivraison;
    }

    


    public Livraison(int id) {
       this.id=id;
    }

    public Livraison(int id, String adresse, String niveau, int commandeass, String periode, int nomch, String Nom,Date datedelivraison) {
        this.id = id;
        this.adresse = adresse;
        this.niveau = niveau;
        this.commandeass = commandeass;
        this.periode = periode;
        this.nomch = nomch;
        this.Nom = Nom;
        this.datedelivraison = datedelivraison;
        
    }
    

    public Livraison(String adresse, String niveau, int commandeass, String periode, int nomch, Date datedelivraison) {
        this.adresse = adresse;
        this.niveau = niveau;
        this.commandeass = commandeass;
        this.periode = periode;
        this.nomch = nomch;
        this.datedelivraison = datedelivraison;
    }

    public Livraison(int id, String adresse, String niveau, int commandeass, String periode, String Nom,Date datedelivraison) {
        this.id = id;
        this.adresse = adresse;
        this.niveau = niveau;
        this.commandeass = commandeass;
        this.periode = periode;
        this.Nom = Nom;
        this.datedelivraison = datedelivraison;
        
    }
    

    public Livraison() {
     
    }
  

   


    public int getId() {
        return id;
    }

    public Date getDatedelivraison() {
        return datedelivraison;
    }

    public void setDatedelivraison(Date datedelivraison) {
        this.datedelivraison = datedelivraison;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getCommandeass() {
        return commandeass;
    }

    public void setCommandeass(int commandeass) {
        this.commandeass = commandeass;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public int getNomch() {
        return nomch;
    }

    public void setNomch(int nomch) {
        this.nomch = nomch;
    }
     public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.adresse);
        hash = 89 * hash + Objects.hashCode(this.niveau);
        hash = 89 * hash + this.commandeass;
        hash = 89 * hash + Objects.hashCode(this.periode);
        hash = 89 * hash + this.nomch;
        hash = 89 * hash + Objects.hashCode(this.datedelivraison);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livraison other = (Livraison) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.commandeass != other.commandeass) {
            return false;
        }
        if (this.nomch != other.nomch) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        if (!Objects.equals(this.periode, other.periode)) {
            return false;
        }
        if (!Objects.equals(this.datedelivraison, other.datedelivraison)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", adresse=" + adresse + ", niveau=" + niveau + ", commandeass=" + commandeass + ", periode=" + periode + ", nomch=" + nomch + ", datedelivraison=" + datedelivraison + '}';
    }
    }

    