package com.example.applicationtransportdescolis.Repositories;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrajetRepositorie extends JpaRepository<Trajet, Integer> {

    @Query("SELECT t FROM Trajet t " +
            "WHERE (:destination IS NULL OR t.destination = :destination) " +
            "AND (:lieuDepart IS NULL OR t.lieuDepart = :lieuDepart) " +
            "AND (:typeMarchandise IS NULL OR t.typeMarchandise = :typeMarchandise)")
    List<Trajet> findByCriteria(
            @Param("destination") String destination,
            @Param("lieuDepart") String lieuDepart,
            @Param("typeMarchandise") TypeMarchandise typeMarchandise);

    //find all annonce by conducteur
        List<Trajet> findAllByConducteurIsNotNull();
    }


