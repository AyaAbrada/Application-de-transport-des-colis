package com.example.applicationtransportdescolis.Dto;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import java.util.List;

public record TrajetDto(
        String lieuDepart,
        List<String> etapes,
        String destination,
        TypeMarchandise typeMarchandise,
        float capaciteDesponible
) {}
