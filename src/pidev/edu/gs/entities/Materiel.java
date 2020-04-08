/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;

/**
 *
 * @author pc 2017
 */
public class Materiel {
    
    private int id;
    private String nom;
    private String image;
    private String description;

    public Materiel() {
    }

    public Materiel(int id, String nom, String image, String description) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
    }

    @Override
    public String toString() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
