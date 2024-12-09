package edu.octavio.poi.application.service;

import edu.octavio.poi.domain.pointofinterest.PointOfInterest;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRepository;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PointOfInterestServiceImplTest {
    @MockitoBean
    private PointOfInterestRepository repository;

    @Autowired
    private PointOfInterestServiceImpl service;

    @Test
    void createPointOfInterest() {
        PointOfInterest pointOfInterest = new PointOfInterest(1L,"The White House", 10L, 3L);
        when(repository.save(any())).thenReturn(pointOfInterest);
        PointOfInterestRequestDTO pointOfInterestRequestDTO = new PointOfInterestRequestDTO(pointOfInterest.getName(), pointOfInterest.getCoordinateX(), pointOfInterest.getCoordinateY());

        PointOfInterestResponseDTO result = service.createPointOfInterest(pointOfInterestRequestDTO);
        assertNotNull(result);
        assertEquals("The White House", result.name());
        assertEquals(10L, result.coordinateX());
        assertEquals(3L, result.coordinateY());
        assertEquals(1L, result.id());
        verify(repository).save(any());
    }

    @Test
    void getPointOfInterestById() {
        PointOfInterest pointOfInterest = new PointOfInterest(1L,"The White House", 10L, 3L);
        when(repository.findById(1L)).thenReturn(Optional.of(pointOfInterest));

        PointOfInterestResponseDTO result = service.getPointOfInterestById(1L);
        assertNotNull(result);
        assertEquals("The White House", result.name());
        assertEquals(10L, result.coordinateX());
        assertEquals(3L, result.coordinateY());
        verify(repository).findById(1L);
    }

    @Test
    void getAllPointOfInterests() {
        List<PointOfInterest> points = List.of(
                new PointOfInterest(1L, "The White House", 10L, 3L),
                new PointOfInterest(2L, "The Eiffel Tower", 15L, 8L)
        );

        when(repository.findAll()).thenReturn(points);
        List<PointOfInterestResponseDTO> result = service.getAllPointOfInterests();
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("The White House", result.getFirst().name());
        assertEquals(10L, result.getFirst().coordinateX());
        assertEquals(3L, result.getFirst().coordinateY());
        assertEquals(1L, result.getFirst().id());

        assertEquals("The Eiffel Tower", result.get(1).name());
        assertEquals(15L, result.get(1).coordinateX());
        assertEquals(8L, result.get(1).coordinateY());
        assertEquals(2L, result.get(1).id());
        verify(repository).findAll();
    }

    @Test
    void deletePointOfInterest() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);
        service.deletePointOfInterest(id);
        verify(repository).deleteById(id);
    }

    @Test
    void updatePointOfInterest() {
        PointOfInterest existing = new PointOfInterest(1L, "The White House", 10L, 3L);
        PointOfInterest updated = new PointOfInterest(1L, "The Capitol", 12L, 5L);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(updated);
        PointOfInterestResponseDTO result = service.updatePointOfInterest(1L, new PointOfInterestRequestDTO(updated.getName(), updated.getCoordinateX(), updated.getCoordinateY()));

        assertNotNull(result);
        assertEquals("The Capitol", result.name());
        assertEquals(12L, result.coordinateX());
        assertEquals(5L, result.coordinateY());
        verify(repository).findById(1L);
        verify(repository).save(any());
    }

    @Test
    void listPointOfInterestInRange() {
        List<PointOfInterest> points = List.of(
                new PointOfInterest(1L, "The White House", 10L, 3L),
                new PointOfInterest(2L, "The Capitol", 12L, 5L)
        );

        Long x = 10L, y = 3L, distance = 5L;
        when(repository.findAll()).thenReturn(points);
        List<PointOfInterestResponseDTO> result = service.listPointOfInterestInRange(x, y, distance);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(repository).findAll();
    }
}