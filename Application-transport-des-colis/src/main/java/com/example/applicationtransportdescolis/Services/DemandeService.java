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
import java.util.Optional;


@Service
public class DemandeService {

    private final DemandeRepositorie demandeRepository;
    private final TrajetRepositorie trajetRepositorie;
    private final UserRepositorie userRepositorie;
    private final JwtUtils jwtUtils;
    private final TrajetService trajetService;


    public DemandeService(DemandeRepositorie demandeRepository, TrajetRepositorie trajetRepositorie, UserRepositorie userRepositorie, JwtUtils jwtUtils, TrajetService trajetService) {
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
        // Extraire le nom d'utilisateur depuis le token
        String usernameFromToken = jwtUtils.extractUsername(token);
        User user = userRepositorie.findByUsername(usernameFromToken);

        // Vérifier que l'utilisateur qui fait la demande est bien l'expéditeur
        if (user.getId() != demandeDto.getExpediteurId()) {
            throw new RuntimeException("Vous ne pouvez créer une demande que pour vous-même.");
        }

        // Récupérer le trajet demandé
        Trajet trajet = trajetService.showTrajet(demandeDto.getTrajetId())
                .orElseThrow(() -> new RuntimeException("Trajet introuvable"));

        // Vérifier si une demande pour ce trajet par ce user existe déjà
        Demande existingDemande = demandeRepository.getDemandeByExpediteurIdAndTrajetId(user.getId(), trajet.getId());
        if (existingDemande != null) {
            throw new RuntimeException("Vous avez déjà fait une demande pour ce trajet.");
        }

        // Créer la nouvelle demande
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

}
