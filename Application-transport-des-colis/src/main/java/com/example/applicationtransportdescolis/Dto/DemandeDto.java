package com.example.applicationtransportdescolis.Dto;

public class DemandeDto {
    private float poids;
    private String type;
    private float longeur;
    private float largeur;
    private float hauteur;
    private int expediteurId;
    private int trajetId;

    // Getters
    public float getPoids() {
        return poids;
    }

    public String getType() {
        return type;
    }

    public float getLongeur() {
        return longeur;
    }

    public float getLargeur() {
        return largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public int getExpediteurId() {
        return expediteurId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    // Setters
    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLongeur(float longeur) {
        this.longeur = longeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public void setExpediteurId(int expediteurId) {
        this.expediteurId = expediteurId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }
}
