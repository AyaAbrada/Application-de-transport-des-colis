package com.example.applicationtransportdescolis.Dto;
import java.util.List;

public record TrajetDto(
        String lieuDepart,
        List<String> etapes,
        String destination,
        String typeMarchandise,
        float capaciteDesponible
) {}
