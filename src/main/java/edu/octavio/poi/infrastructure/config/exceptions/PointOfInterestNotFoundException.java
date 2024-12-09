package edu.octavio.poi.infrastructure.config.exceptions;

public class PointOfInterestNotFoundException extends RuntimeException {
    public PointOfInterestNotFoundException() {
        super("Point of Interest not found!");
  }
}
