package edu.octavio.poi.domain.pointofinterest;

public record PointOfInterestRequestDTO(String name, Long coordinateX, Long coordinateY) {
    @Override
    public String toString() {
        return "PointOfInterestRequestDTO{" +
                "name='" + name + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }

    public String toJson() {
        return "{\"name\": \"" + name + "\", \"coordinateX\": " + coordinateX + ", \"coordinateY\": " + coordinateY + '}';
    }
}