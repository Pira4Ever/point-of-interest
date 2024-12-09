package edu.octavio.poi.domain.pointofinterest;

import java.util.List;
import java.util.Optional;

public interface PointOfInterestRepository {
    PointOfInterest save(PointOfInterest pointOfInterest);
    Optional<PointOfInterest> findById(Long id);
    List<PointOfInterest> findAll();
    void deleteById(Long id);
}
