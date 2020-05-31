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
public class Addresse {
    
    private int id;
    private int numTel;
    private String mail;
    private String pays;
    private String ville;
    private int pinCode;

    @Override
    public String toString() {
        return "Addresse{" + "id=" + id + ", numTel=" + numTel + ", mail=" + mail + ", pays=" + pays + ", ville=" + ville + ", pinCode=" + pinCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 2;
        //hash = 4 * hash + this.id;
        //hash = 1 * hash + this.numTel;
        //hash = 4 * hash + Objects.hashCode(this.mail);
        //hash = 13 * hash + Objects.hashCode(this.pays);
        hash = 13 * hash + Objects.hashCode(this.ville);
        hash = 2 * hash + this.pinCode;
        return hash;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
