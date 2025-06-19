package com.example.applicationtransportdescolis.Controllers;
import com.example.applicationtransportdescolis.Dto.TrajetDto;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import com.example.applicationtransportdescolis.Services.TrajetService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trajets")
@CrossOrigin
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    @GetMapping
    public List<Trajet> index() {
        return trajetService.listAllTrajets();
    }

    @GetMapping("/{id}")
    public Optional<Trajet> show(@PathVariable int id) {
        return trajetService.showTrajet(id);
    }

    @PostMapping
    public Trajet create(@RequestBody TrajetDto trajet) {
        return trajetService.createNewTrajet(trajet);
    }


}
