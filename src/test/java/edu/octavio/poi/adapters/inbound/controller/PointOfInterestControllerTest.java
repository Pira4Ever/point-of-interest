package edu.octavio.poi.adapters.inbound.controller;

import edu.octavio.poi.application.usecases.PointOfInterestUseCases;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PointOfInterestController.class)
class PointOfInterestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PointOfInterestUseCases useCases;

    @Test
    void getAllPointsOfInterest() throws Exception {
        List<PointOfInterestResponseDTO> points = List.of(
                new PointOfInterestResponseDTO(1L, "The White House", 10L, 3L),
                new PointOfInterestResponseDTO(2L, "The Eiffel Tower", 15L, 8L)
        );
        when(useCases.getAllPointOfInterests()).thenReturn(points);

        mockMvc.perform(get("/poi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("The White House"))
                .andExpect(jsonPath("$.[0].coordinateX").value(10L))
                .andExpect(jsonPath("$.[0].coordinateY").value(3L))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].name").value("The Eiffel Tower"))
                .andExpect(jsonPath("$.[1].coordinateX").value(15L))
                .andExpect(jsonPath("$.[1].coordinateY").value(8L))
                .andExpect(jsonPath("$.[1].id").value(2L));
    }

    @Test
    void getPointOfInterestById() throws Exception {
        PointOfInterestResponseDTO dto = new PointOfInterestResponseDTO(1L, "Park", 10L, 20L);
        when(useCases.getPointOfInterestById(1L)).thenReturn(dto);
        mockMvc.perform(get("/poi/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Park"))
                .andExpect(jsonPath("$.coordinateX").value(10L))
                .andExpect(jsonPath("$.coordinateY").value(20L))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void createPointOfInterest() throws Exception {
        PointOfInterestRequestDTO dto = new PointOfInterestRequestDTO("The Eiffel Tower", 15L, 8L);
        when(useCases.createPointOfInterest(dto)).thenReturn(new PointOfInterestResponseDTO(1L, "The Eiffel Tower", 15L, 8L));
        mockMvc.perform(post("/poi").accept(MediaType.APPLICATION_JSON).content("{\"name\": \"The Eiffel Tower\", \"coordinateX\": 15, \"coordinateY\": 8}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/poi/1"))
                .andExpect(jsonPath("$.name").value("The Eiffel Tower"))
                .andExpect(jsonPath("$.coordinateX").value(15L))
                .andExpect(jsonPath("$.coordinateY").value(8L))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deletePointOfInterestById() throws Exception {
        doNothing().when(useCases).deletePointOfInterest(1L);
        mockMvc.perform(delete("/poi/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updatePointOfInterest() throws Exception {
        PointOfInterestResponseDTO updated = new PointOfInterestResponseDTO(1L, "The Capitol", 12L, 5L);
        PointOfInterestRequestDTO request = new PointOfInterestRequestDTO("The Capitol", 12L, 5L);
        when(useCases.updatePointOfInterest(eq(1L), any())).thenReturn(updated);
        mockMvc.perform(put("/poi/1").accept(MediaType.APPLICATION_JSON).content(request.toJson()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("The Capitol"))
                .andExpect(jsonPath("$.coordinateX").value(12L))
                .andExpect(jsonPath("$.coordinateY").value(5L))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void listPointOfInterestInRange() throws Exception {
        List<PointOfInterestResponseDTO> points = List.of(
                new PointOfInterestResponseDTO(1L, "The White House", 10L, 3L)
        );
        when(useCases.listPointOfInterestInRange(0L, 0L, 11L)).thenReturn(points);
        mockMvc.perform(get("/poi/range").param("positionX", "0").param("positionY", "0").param("distance", "11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("The White House"))
                .andExpect(jsonPath("$.[0].coordinateX").value(10L))
                .andExpect(jsonPath("$.[0].coordinateY").value(3L))
                .andExpect(jsonPath("$.[0].id").value(1L));
    }
}
