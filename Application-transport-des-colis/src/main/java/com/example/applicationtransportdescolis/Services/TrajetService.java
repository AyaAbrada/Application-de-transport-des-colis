package com.example.applicationtransportdescolis.Services;

import com.example.applicationtransportdescolis.Configuration.JwtUtils;
import com.example.applicationtransportdescolis.Dto.TrajetDto;
import com.example.applicationtransportdescolis.Entities.Demande;
import com.example.applicationtransportdescolis.Entities.Role;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.TrajetRepositorie;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {

    private final TrajetRepositorie trajetRepositorie;
    private final UserRepositorie userRepositorie;
    private final JwtUtils jwtUtils;
    private final DemandeService demandeService;

    // Injection par constructeur
    public TrajetService(TrajetRepositorie trajetRepositorie, UserRepositorie userRepositorie,
                         JwtUtils jwtUtils, DemandeService demandeService) {
        this.trajetRepositorie = trajetRepositorie;
        this.userRepositorie = userRepositorie;
        this.jwtUtils = jwtUtils;
        this.demandeService = demandeService;
    }

    public List<Trajet> listAllTrajets() {
        return trajetRepositorie.findAll();
    }

    public Optional<Trajet> showTrajet(int id) {
        return trajetRepositorie.findById(id);
    }

    public Trajet createNewTrajet(TrajetDto trajet, String token) {

        String usernameFromToken = jwtUtils.extractUsername(token.substring(7));

        User user = userRepositorie.findByUsernameOrEmail(usernameFromToken);
        if (user == null) {
            throw new RuntimeException("User not found from token");
        }

        Demande demande = demandeService.getDemandeById(trajet.conducteurId());

        if (user.getRole() == Role.CONDUCTEUR) {
            Trajet newTrajet = new Trajet();

            newTrajet.setConducteurId(trajet.conducteurId());
            newTrajet.setLieuDepart(trajet.lieuDepart());
            newTrajet.setEtapes(trajet.etapes());
            newTrajet.setDestination(trajet.destination());
            newTrajet.setTypeMarchandise(trajet.typeMarchandise());
            newTrajet.setCapaciteDesponible(trajet.capaciteDesponible());

            return trajetRepositorie.save(newTrajet);

        } else {
            throw new RuntimeException("Unauthorized action");
        }
    }
}
