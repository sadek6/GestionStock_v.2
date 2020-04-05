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
public class Participant {
    
    private int idParticipant;
    private String nomParticipant;
    private int idJeuxConcours;
    private String nomJeuxConcours;
    private int prix;

    public Participant(int idParticipant, String nomParticipant, int idJeuxConcours, String nomJeuxConcours, int prix) {
        this.idParticipant = idParticipant;
        this.nomParticipant = nomParticipant;
        this.idJeuxConcours = idJeuxConcours;
        this.nomJeuxConcours = nomJeuxConcours;
        this.prix = prix;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }

    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }

    public int getIdJeuxConcours() {
        return idJeuxConcours;
    }

    public void setIdJeuxConcours(int idJeuxConcours) {
        this.idJeuxConcours = idJeuxConcours;
    }

    public String getNomJeuxConcours() {
        return nomJeuxConcours;
    }

    public void setNomJeuxConcours(String nomJeuxConcours) {
        this.nomJeuxConcours = nomJeuxConcours;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
