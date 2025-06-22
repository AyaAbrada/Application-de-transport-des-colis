package com.example.applicationtransportdescolis.Services;

import com.example.applicationtransportdescolis.Configuration.JwtUtils;
import com.example.applicationtransportdescolis.Dto.DemandeDto;
import com.example.applicationtransportdescolis.Entities.Demande;
import com.example.applicationtransportdescolis.Entities.Statut;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.DemandeRepositorie;
import com.example.applicationtransportdescolis.Repositories.TrajetRepositorie;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    private final DemandeRepositorie demandeRepository;
    private final TrajetRepositorie trajetRepositorie;
    private final UserRepositorie userRepositorie;
    private final JwtUtils jwtUtils;
    private final TrajetService trajetService;

    public DemandeService(DemandeRepositorie demandeRepository,
                          TrajetRepositorie trajetRepositorie,
                          UserRepositorie userRepositorie,
                          JwtUtils jwtUtils,
                          TrajetService trajetService) {
        this.demandeRepository = demandeRepository;
        this.trajetRepositorie = trajetRepositorie;
        this.userRepositorie = userRepositorie;
        this.jwtUtils = jwtUtils;
        this.trajetService = trajetService;
    }

    public Demande getDemandeById(int id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public List<Demande> listAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande createDemande(DemandeDto demandeDto, String token) {
        String usernameFromToken = jwtUtils.extractUsername(token);
        User user = userRepositorie.findByUsername(usernameFromToken);

        if (user.getId() != demandeDto.getExpediteurId()) {
            throw new RuntimeException("Vous ne pouvez créer une demande que pour vous-même.");
        }

        Trajet trajet = trajetService.showTrajet(demandeDto.getTrajetId())
                .orElseThrow(() -> new RuntimeException("Trajet introuvable"));

        Demande existingDemande = demandeRepository.getDemandeByExpediteurIdAndTrajetId(user.getId(), trajet.getId());
        if (existingDemande != null) {
            throw new RuntimeException("Vous avez déjà fait une demande pour ce trajet.");
        }

        Demande newDemande = new Demande();
        newDemande.setPoids(demandeDto.getPoids());
        newDemande.setType(demandeDto.getType());
        newDemande.setLongeur(demandeDto.getLongeur());
        newDemande.setLargeur(demandeDto.getLargeur());
        newDemande.setHauteur(demandeDto.getHauteur());
        newDemande.setStatut(Statut.EN_ATTENTE);
        newDemande.setExpediteur(user);
        newDemande.setTrajet(trajet);

        return demandeRepository.save(newDemande);
    }

    public Demande traiterDemande(int demandeId, String action, String token) {
        String username = jwtUtils.extractUsername(token);
        User conducteur = userRepositorie.findByUsername(username);

        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));

        Trajet trajet = demande.getTrajet();

        if (trajet.getConducteur().getId() != conducteur.getId()) {
            throw new RuntimeException("Vous n’êtes pas autorisé à traiter cette demande.");
        }

        if (action.equalsIgnoreCase("accepter")) {
            demande.setStatut(Statut.ACCEPTEE);
        } else if (action.equalsIgnoreCase("refuser")) {
            demande.setStatut(Statut.REFUSEE);
        } else {
            throw new RuntimeException("Action non valide : " + action);
        }

        return demandeRepository.save(demande);
    }
}
