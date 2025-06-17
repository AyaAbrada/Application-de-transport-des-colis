package com.example.applicationtransportdescolis.Services;

import com.example.applicationtransportdescolis.Entities.Demande;
import com.example.applicationtransportdescolis.Repositories.DemandeRepositorie;
import com.example.applicationtransportdescolis.Repositories.DemandeRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    private final DemandeRepositorie demandeRepository;

    @Autowired
    public DemandeService(DemandeRepositorie demandeRepositorie) {
        this.demandeRepository = demandeRepositorie;
    }

    public Demande getDemandeById(int id) {
        return demandeRepository.findById(id).orElse(null);
    }
}
