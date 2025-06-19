package com.example.applicationtransportdescolis.Entities;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lieuDepart;

    @ElementCollection
    private List<String> etapes;

    private String destination;

    @Enumerated(EnumType.STRING)
    private TypeMarchandise typeMarchandise;

    private float capaciteDesponible;


    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private User conducteur;

    public Trajet() {
    }


    public Trajet(int id, String lieuDepart, List<String> etapes, String destination, float capaciteDesponible, User conducteur , TypeMarchandise typeMarchandise) {
        this.id = id;
        this.lieuDepart = lieuDepart;
        this.etapes = etapes;
        this.destination = destination;
        this.capaciteDesponible = capaciteDesponible;
        this.conducteur = conducteur;
        this.typeMarchandise = typeMarchandise;
    }

    // Getters and setters


    public TypeMarchandise getTypeMarchandise() {
        return typeMarchandise;
    }

    public void setTypeMarchandise(TypeMarchandise typeMarchandise) {
        this.typeMarchandise = typeMarchandise;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLieuDepart() { return lieuDepart; }

    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }

    public List<String> getEtapes() { return etapes; }

    public void setEtapes(List<String> etapes) { this.etapes = etapes; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    public float getCapaciteDesponible() { return capaciteDesponible; }

    public void setCapaciteDesponible(float capaciteDesponible) { this.capaciteDesponible = capaciteDesponible; }

    public User getConducteur() { return conducteur; }

    public void setConducteur(User conducteur) { this.conducteur = conducteur; }
}
