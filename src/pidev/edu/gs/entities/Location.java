/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.gs.entities;

import java.sql.Date;

/**
 *
 * @author pc 2017
 */
public class Location {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private int idMateriel;
   
    public Location(int id, Date dateDebut, Date dateFin, int idMateriel) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idMateriel = idMateriel;
    }
    public Location( Date dateDebut, Date dateFin, int  idMateriel) {
       
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idMateriel = idMateriel;
    }
    public Location(int idMateriel) {
        this.idMateriel = idMateriel;
    }



   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }
    
    
    
    
}
