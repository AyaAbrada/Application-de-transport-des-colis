package com.example.applicationtransportdescolis.Controllers;
import com.example.applicationtransportdescolis.Configuration.JwtUtils;
import com.example.applicationtransportdescolis.Dto.DemandeDto;
import com.example.applicationtransportdescolis.Entities.Demande;
import com.example.applicationtransportdescolis.Entities.Statut;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.DemandeRepositorie;
import com.example.applicationtransportdescolis.Repositories.TrajetRepositorie;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import com.example.applicationtransportdescolis.Services.DemandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
@CrossOrigin
public class DemandeController {

    private final JwtUtils jwtUtils;
    private final UserRepositorie userRepositorie;
    private final TrajetRepositorie trajetRepository;
    private final DemandeRepositorie demandeRepository;
    private final DemandeService demandeService;


    public DemandeController(
            JwtUtils jwtUtils,
            UserRepositorie userRepositorie,
            TrajetRepositorie trajetRepository,
            DemandeRepositorie demandeRepository ,
            DemandeService demandeService
    ) {
        this.jwtUtils = jwtUtils;
        this.userRepositorie = userRepositorie;
        this.trajetRepository = trajetRepository;
        this.demandeRepository = demandeRepository;
        this.demandeService = demandeService;


    }
    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable int id) {
        return demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec l'id " + id));
    }

    @PostMapping
    public Demande createDemande(
            @RequestBody DemandeDto demandeDto,
            @RequestHeader("Authorization") String authHeader) {

        // 1. Extraire token
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        // 2. Extraire le username depuis le token
        String username = jwtUtils.extractUsername(token);

        // 3. Trouver l'utilisateur
        User expediteur = userRepositorie.findByUsername(username);
        if (expediteur == null) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        // 4. Trouver le trajet
        Trajet trajet = trajetRepository.findById(demandeDto.getTrajetId())
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));

        // 5. Créer et sauvegarder la demande
        Demande demande = new Demande();
        demande.setPoids(demandeDto.getPoids());
        demande.setType(demandeDto.getType());
        demande.setLongeur(demandeDto.getLongeur());
        demande.setLargeur(demandeDto.getLargeur());
        demande.setHauteur(demandeDto.getHauteur());
        demande.setStatut(Statut.EN_ATTENTE); // valeur par défaut
        demande.setExpediteur(expediteur);
        demande.setTrajet(trajet);

        return demandeRepository.save(demande);
    }
    @PutMapping("/{id}/statut")
    public Demande traiterDemande(
            @PathVariable int id,
            @RequestParam("action") String action,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        return demandeService.traiterDemande(id, action, token);
    }


}
