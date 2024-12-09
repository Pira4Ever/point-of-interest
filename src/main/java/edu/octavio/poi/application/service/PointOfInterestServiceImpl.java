package edu.octavio.poi.application.service;

import edu.octavio.poi.application.usecases.PointOfInterestUseCases;
import edu.octavio.poi.domain.pointofinterest.PointOfInterest;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRepository;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;
import edu.octavio.poi.infrastructure.config.exceptions.PointOfInterestNotFoundException;
import edu.octavio.poi.utils.mappers.PointOfInterestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestUseCases {
    private final PointOfInterestRepository repository;
    private final PointOfInterestMapper mapper;

    public PointOfInterestServiceImpl(PointOfInterestRepository repository, PointOfInterestMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PointOfInterestResponseDTO createPointOfInterest(PointOfInterestRequestDTO data) {
        return this.mapper.toDto(this.repository.save(this.mapper.dtoToEntity(data)));
    }

    @Override
    public PointOfInterestResponseDTO getPointOfInterestById(Long id) {
        Optional<PointOfInterestResponseDTO> pointOfInterest = this.repository.findById(id).map(this.mapper::toDto);
        if (pointOfInterest.isPresent()) {
            return pointOfInterest.get();
        } else {
            throw new PointOfInterestNotFoundException();
        }
    }

    @Override
    public List<PointOfInterestResponseDTO> getAllPointOfInterests() {
        return this.repository.findAll().stream().map(this.mapper::toDto).toList();
    }

    @Override
    public void deletePointOfInterest(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public PointOfInterestResponseDTO updatePointOfInterest(Long id, PointOfInterestRequestDTO data) {
        Optional<PointOfInterest> pointOfInterestOptional = this.repository.findById(id);
        if (pointOfInterestOptional.isEmpty()) throw new PointOfInterestNotFoundException();
        PointOfInterest pointOfInterest = pointOfInterestOptional.get();
        pointOfInterest.setCoordinateX(data.coordinateX());
        pointOfInterest.setCoordinateY(data.coordinateY());
        pointOfInterest.setName(data.name());
        return this.mapper.toDto(this.repository.save(pointOfInterest));
    }

    @Override
    public List<PointOfInterestResponseDTO> listPointOfInterestInRange(Long positionX, Long positionY, Long distance) {
        return this.getAllPointOfInterests().stream().filter(entity -> Math.hypot(Math.abs(positionX - entity.coordinateX()), Math.abs(positionY - entity.coordinateY())) <= distance).toList();
    }
}
