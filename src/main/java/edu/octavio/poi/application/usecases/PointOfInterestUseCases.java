package edu.octavio.poi.application.usecases;

import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;

import java.util.List;

public interface PointOfInterestUseCases {
    public PointOfInterestResponseDTO createPointOfInterest(PointOfInterestRequestDTO data);
    public PointOfInterestResponseDTO getPointOfInterestById(Long id);
    public List<PointOfInterestResponseDTO> getAllPointOfInterests();
    public void deletePointOfInterest(Long id);
    public PointOfInterestResponseDTO updatePointOfInterest(Long id, PointOfInterestRequestDTO data);
    public List<PointOfInterestResponseDTO> listPointOfInterestInRange(Long positionX, Long positionY, Long distance);
}
