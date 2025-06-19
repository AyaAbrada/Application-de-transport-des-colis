package com.example.applicationtransportdescolis.Entities;
import jakarta.persistence.*;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float poids;
    private String type;
    private float longeur;
    private float largeur;
    private float hauteur;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private User expediteur;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;

    public Demande() {
    }

    public Demande(int id, float poids, String type, float longeur, float largeur, float hauteur, Statut statut, User expediteur, Trajet trajet) {
        this.id = id;
        this.poids = poids;
        this.type = type;
        this.longeur = longeur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.statut = statut;
        this.expediteur = expediteur;
        this.trajet = trajet;
    }

    // Getters and setters

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public float getPoids() { return poids; }

    public void setPoids(float poids) { this.poids = poids; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public float getLongeur() { return longeur; }

    public void setLongeur(float longeur) { this.longeur = longeur; }

    public float getLargeur() { return largeur; }

    public void setLargeur(float largeur) { this.largeur = largeur; }

    public float getHauteur() { return hauteur; }

    public void setHauteur(float hauteur) { this.hauteur = hauteur; }

    public Statut getStatut() { return statut; }

    public void setStatut(Statut statut) { this.statut = statut; }

    public User getExpediteur() { return expediteur; }

    public void setExpediteur(User expediteur) { this.expediteur = expediteur; }

    public Trajet getTrajet() { return trajet; }

    public void setTrajet(Trajet trajet) { this.trajet = trajet; }

}
