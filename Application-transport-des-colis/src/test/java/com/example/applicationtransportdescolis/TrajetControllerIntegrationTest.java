package com.example.applicationtransportdescolis;
import com.example.applicationtransportdescolis.Entities.Trajet;
import com.example.applicationtransportdescolis.Entities.TypeMarchandise;
import com.example.applicationtransportdescolis.Repositories.TrajetRepositorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest 
@AutoConfigureMockMvc
public class TrajetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrajetRepositorie trajetRepositorie;

    @BeforeEach
    void setup() {
        trajetRepositorie.deleteAll();

        Trajet trajet = new Trajet();
        trajet.setLieuDepart("Tunis");
        trajet.setDestination("Sfax");
        trajet.setCapaciteDesponible(50f);
        trajet.setEtapes(Collections.singletonList("Kairouan"));
        trajet.setTypeMarchandise(TypeMarchandise.FRAGILE);

        trajetRepositorie.save(trajet);
    }

    @Test
    void testGetAllTrajets_shouldReturnList() throws Exception {
        mockMvc.perform(get("/trajets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lieuDepart").value("Tunis"))
                .andExpect(jsonPath("$[0].destination").value("Sfax"));
    }

    @Test
    void testGetTrajetById_shouldReturnTrajet() throws Exception {
        int id = trajetRepositorie.findAll().get(0).getId();

        mockMvc.perform(get("/trajets/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lieuDepart").value("Tunis"))
                .andExpect(jsonPath("$.destination").value("Sfax"));
    }
}
