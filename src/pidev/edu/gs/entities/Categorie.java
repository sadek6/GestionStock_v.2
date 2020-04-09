package pidev.edu.gs.entities;

public class Categorie {


    private int id;
    private String nomCategorie;
    private String description;


    public int getId() {
        return id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + ", Description=" + description + '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nomCategorie = nom;
        this.description = description;
    }

    public Categorie(String nom, String description) {
        this.nomCategorie = nom;
        this.description = description;
    }




}
