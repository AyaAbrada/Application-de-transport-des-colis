package com.example.applicationtransportdescolis.Repositories;

import com.example.applicationtransportdescolis.Entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepositorie extends JpaRepository<Demande, Integer> {
    Demande getDemandeByExpediteurIdAndTrajetId(Long expediteurId, int trajetId);
}
