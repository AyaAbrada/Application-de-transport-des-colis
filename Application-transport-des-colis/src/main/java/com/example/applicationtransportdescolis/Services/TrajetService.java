package com.example.applicationtransportdescolis.Services;
import com.example.applicationtransportdescolis.Configuration.JwtUtils;
import com.example.applicationtransportdescolis.Dto.TrajetDto;
import com.example.applicationtransportdescolis.Entities.Role;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.TrajetRepositorie;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {

    private final TrajetRepositorie trajetRepositorie;
    private final UserRepositorie userRepositorie;
    private final JwtUtils jwtUtils;

    public TrajetService(
            TrajetRepositorie trajetRepositorie,
            UserRepositorie userRepositorie,
            JwtUtils jwtUtils
    ) {
        this.trajetRepositorie = trajetRepositorie;
        this.userRepositorie = userRepositorie;
        this.jwtUtils = jwtUtils;
    }

    public List<Trajet> listAllTrajets() {
        return trajetRepositorie.findAll();
    }

    public Optional<Trajet> showTrajet(int id) {
        return trajetRepositorie.findById(id);
    }

    public Trajet createNewTrajet(TrajetDto dto) {


        Trajet newTrajet = new Trajet();
        newTrajet.setConducteur(null);
        newTrajet.setLieuDepart(dto.lieuDepart());
        newTrajet.setEtapes(dto.etapes());
        newTrajet.setDestination(dto.destination());
        newTrajet.setTypeMarchandise(dto.typeMarchandise());
        newTrajet.setCapaciteDesponible(dto.capaciteDesponible());

        return trajetRepositorie.save(newTrajet);
    }
}
