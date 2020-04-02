/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;

import java.util.Objects;

/**
 *
 * @author Mohamed
 */
public class Commande {
    
    private int id;
    private int idClient;
    private int prixTotale;
    private String etat;
    private int adresse;
    private String nomClient;

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Commande() {
    }

    public Commande(int id, int idClient, int prixTotale, String etat) {
        this.id = id;
        this.idClient = idClient;
        this.prixTotale = prixTotale;
        this.etat = etat;
    }

    public Commande(int id, int prixTotale, String etat, String nomClient) {
        this.id = id;
        this.prixTotale = prixTotale;
        this.etat = etat;
        this.nomClient = nomClient;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idClient=" + idClient + ", prixTotale=" + prixTotale + ", etat=" + etat + ", adresse=" + adresse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 2;
        hash = 4 * hash + this.idClient;
        //hash = 4 * hash + this.prixTotale;
        //hash = 2 * hash + Objects.hashCode(this.etat);
        hash = 2 * hash + this.adresse;
        return hash;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(int prixTotale) {
        this.prixTotale = prixTotale;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getAdresse() {
        return adresse;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }
    
}
