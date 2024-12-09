package edu.octavio.poi.domain.pointofinterest;


public class PointOfInterest {
    private Long id;
    private String name;
    private Long coordinateX;
    private Long coordinateY;

    public PointOfInterest() {}

    public PointOfInterest(Long id, String name, Long coordinateX, Long coordinateY) {
        this.id = id;
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
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
