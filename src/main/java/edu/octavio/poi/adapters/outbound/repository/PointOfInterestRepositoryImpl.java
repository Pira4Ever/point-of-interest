package edu.octavio.poi.adapters.outbound.repository;

import edu.octavio.poi.adapters.outbound.entities.JpaPointOfInterestEntity;
import edu.octavio.poi.domain.pointofinterest.PointOfInterest;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRepository;
import edu.octavio.poi.utils.mappers.PointOfInterestMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PointOfInterestRepositoryImpl implements PointOfInterestRepository {
    private final JpaPointOfInterestRepository jpaPointOfInterestRepository;
    private final PointOfInterestMapper mapper;

    public PointOfInterestRepositoryImpl(JpaPointOfInterestRepository jpaPointOfInterestRepository, PointOfInterestMapper mapper) {
        this.jpaPointOfInterestRepository = jpaPointOfInterestRepository;
        this.mapper = mapper;
    }

    @Override
    public PointOfInterest save(PointOfInterest pointOfInterest) {
        JpaPointOfInterestEntity pointOfInterestEntity = new JpaPointOfInterestEntity(pointOfInterest);
        this.jpaPointOfInterestRepository.save(pointOfInterestEntity);
        return new PointOfInterest(pointOfInterestEntity.getId(), pointOfInterestEntity.getName(), pointOfInterestEntity.getCoordinateX(), pointOfInterestEntity.getCoordinateY());
    }

    @Override
    public Optional<PointOfInterest> findById(Long id) {
        Optional<JpaPointOfInterestEntity> pointOfInterestEntity = this.jpaPointOfInterestRepository.findById(id);
        return pointOfInterestEntity.map(mapper::jpaToDomain);
    }

    @Override
    public List<PointOfInterest> findAll() {
        return this.jpaPointOfInterestRepository.findAll().stream().map(entity -> new PointOfInterest(entity.getId(), entity.getName(), entity.getCoordinateX(), entity.getCoordinateY())).toList();
    }

    @Override
    public void deleteById(Long id) {
        this.jpaPointOfInterestRepository.deleteById(id);
    }
}
