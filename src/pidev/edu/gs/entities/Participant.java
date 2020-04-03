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
    
    private int idUser;
    private int idJeux;

    public Participant(int idUser, int idJeux) {
        this.idUser = idUser;
        this.idJeux = idJeux;
    }

    @Override
    public String toString() {
        return "Participant{" + "idUser=" + idUser + ", idJeux=" + idJeux + '}';
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdJeux() {
        return idJeux;
    }

    public void setIdJeux(int idJeux) {
        this.idJeux = idJeux;
    }
    
}
