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
public class Panier {
    
    private int id;
    private int idProduit;
    private int idClient;
    private int quantite;

    public Panier(int id, int idProduit, int idClient, int quantite) {
        this.id = id;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idProduit=" + idProduit + ", idClient=" + idClient + ", quantiteProduit=" + quantite + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
}
