/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;

/**
 *
 * @author Mohamed
 */
public class JeuxConcours {
    
    private int id;
    private String nomJeux;
    private int prix;
    private int nbParticipants;
    private int etat;

    public JeuxConcours() {
    }

    public JeuxConcours(int id, String nomJeux, int prix, int nbParticipants, int etat) {
        this.id = id;
        this.nomJeux = nomJeux;
        this.prix = prix;
        this.nbParticipants = nbParticipants;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "JeuxConcours{" + "id=" + id + ", nomJeux=" + nomJeux + ", prix=" + prix + ", nbParticipants=" + nbParticipants + ", etat=" + etat + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomJeux() {
        return nomJeux;
    }

    public void setNomJeux(String nomJeux) {
        this.nomJeux = nomJeux;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
