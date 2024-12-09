package edu.octavio.poi.adapters.outbound.repository;

import edu.octavio.poi.adapters.outbound.entities.JpaPointOfInterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointOfInterestRepository extends JpaRepository<JpaPointOfInterestEntity, Long> {
}
