package edu.octavio.poi.adapters.outbound.entities;

import edu.octavio.poi.domain.pointofinterest.PointOfInterest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Table(name = "points_of_interest")
@Entity
public class JpaPointOfInterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Long coordinateX;
    private Long coordinateY;

    public JpaPointOfInterestEntity() {}

    public JpaPointOfInterestEntity(Long id, String name, Long coordinateX, Long coordinateY) {
        this.id = id;
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public JpaPointOfInterestEntity(PointOfInterest pointOfInterest) {
        this.id = pointOfInterest.getId();
        this.name = pointOfInterest.getName();
        this.coordinateX = pointOfInterest.getCoordinateX();
        this.coordinateY = pointOfInterest.getCoordinateY();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Long coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Long getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Long coordinateY) {
        this.coordinateY = coordinateY;
    }
}
