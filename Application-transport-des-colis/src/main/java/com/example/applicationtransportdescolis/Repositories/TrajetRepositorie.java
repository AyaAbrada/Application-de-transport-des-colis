package com.example.applicationtransportdescolis.Repositories;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrajetRepositorie extends JpaRepository<Trajet, Integer> {


    }


