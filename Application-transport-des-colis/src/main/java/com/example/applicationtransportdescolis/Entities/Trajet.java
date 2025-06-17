package com.example.applicationtransportdescolis.Entities;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int conducteurId;
    private String lieuDepart;

    @ElementCollection
    private List<String> etapes;

    private String destination;
    private String typeMarchandise;
    private float capaciteDesponible;

    public Trajet(int id, int conducteurId, String lieuDepart, List<String> etapes, String destination, String typeMarchandise, float capaciteDesponible) {
        this.id = id;
        this.conducteurId = conducteurId;
        this.lieuDepart = lieuDepart;
        this.etapes = etapes;
        this.destination = destination;
        this.typeMarchandise = typeMarchandise;
        this.capaciteDesponible = capaciteDesponible;
    }

    public Trajet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConducteurId() {
        return conducteurId;
    }

    public void setConducteurId(int conducteurId) {
        this.conducteurId = conducteurId;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public List<String> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<String> etapes) {
        this.etapes = etapes;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTypeMarchandise() {
        return typeMarchandise;
    }

    public void setTypeMarchandise(String typeMarchandise) {
        this.typeMarchandise = typeMarchandise;
    }

    public float getCapaciteDesponible() {
        return capaciteDesponible;
    }

    public void setCapaciteDesponible(float capaciteDesponible) {
        this.capaciteDesponible = capaciteDesponible;
    }
}
