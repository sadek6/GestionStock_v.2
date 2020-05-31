/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;

import java.awt.Image;

import java.util.Objects;

import java.sql.Date;


/**
 *
 * @author Lenovo
 */
public class Chauffeur {
    private int numpass;
    private String nom_image ;
     private String Prenom ;
     private String niveau ;
     private int numerotel;
     private String Nom;
     
     private int nbr ;
     private Date datenain;
;
    public Chauffeur(int numpass, String nom_image, String Prenom, String niveau, int numerotel, String Nom, int nbr, Date datenain) {
        this.numpass = numpass;
        this.nom_image = nom_image;
        this.Prenom = Prenom;
        this.niveau = niveau;
        this.numerotel = numerotel;
        this.Nom = Nom;
        this.nbr = nbr;
        this.datenain = datenain;
       
       
    
   
       
    }

    public Chauffeur(int numpass) {
        this.numpass=numpass;
    }

  

    public Chauffeur() {
       
    }

    public Chauffeur(String nom_image, String Prenom, String niveau, int numerotel, String Nom, int nbr, Date datenain) {
        this.nom_image = nom_image;
        this.Prenom = Prenom;
        this.niveau = niveau;
        this.numerotel = numerotel;
        this.Nom = Nom;
        this.nbr = nbr;
        this.datenain = datenain;
    }

   

    


    public int getNumpass() {
        return numpass;
    }

    public void setNumpass(int numpass) {
        this.numpass = numpass;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

  



    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getNumerotel() {
        return numerotel;
    }

    public void setNumerotel(int numerotel) {
        this.numerotel = numerotel;
    }

  

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public Date getDatenain() {
        return datenain;
    }

    public void setDatenain(Date datenain) {
        this.datenain = datenain;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.numpass;
        hash = 43 * hash + Objects.hashCode(this.nom_image);
        hash = 43 * hash + Objects.hashCode(this.Prenom);
        hash = 43 * hash + Objects.hashCode(this.niveau);
        hash = 43 * hash + Objects.hashCode(this.Nom);
        hash = 43 * hash + this.numerotel;
        hash = 43 * hash + this.nbr;
        hash = 43 * hash + Objects.hashCode(this.datenain);
        return hash;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "numpass=" + numpass + ", nom_image=" + nom_image + ", Prenom=" + Prenom + ", niveau=" + niveau + ", Nom=" + Nom + ", numerotel=" + numerotel + ", nbr=" + nbr + ", datenain=" + datenain + '}';
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
        final Chauffeur other = (Chauffeur) obj;
        if (this.numpass != other.numpass) {
            return false;
        }
        if (this.numerotel != other.numerotel) {
            return false;
        }
        if (this.nbr != other.nbr) {
            return false;
        }
        if (!Objects.equals(this.nom_image, other.nom_image)) {
            return false;
        }
        if (!Objects.equals(this.Prenom, other.Prenom)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (!Objects.equals(this.datenain, other.datenain)) {
            return false;
        }
        return true;
    }


   

   

   

   

     
    
    
    
}
