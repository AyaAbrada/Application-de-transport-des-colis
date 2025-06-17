package com.example.applicationtransportdescolis.Entities;
import jakarta.persistence.*;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int expeduteurId ;
    private int trajetId;
    private float poids;
    private String type;
    private float longeur;
    private float largeur;
    private float hauteur;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Demande() {

    }

    public Demande( int id ,int expeduteurId, int trajetId, float poids, String type, float longeur, float largeur, float hauteur, Statut statut) {
        this.id = id;
        this.expeduteurId = expeduteurId;
        this.trajetId = trajetId;
        this.poids = poids;
        this.type = type;
        this.longeur = longeur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpeduteurId() {
        return expeduteurId;
    }

    public void setExpeduteurId(int expeduteurId) {
        this.expeduteurId = expeduteurId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLongeur() {
        return longeur;
    }

    public void setLongeur(float longeur) {
        this.longeur = longeur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


}
