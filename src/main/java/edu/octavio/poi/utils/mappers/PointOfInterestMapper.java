package edu.octavio.poi.utils.mappers;

import edu.octavio.poi.adapters.outbound.entities.JpaPointOfInterestEntity;
import edu.octavio.poi.domain.pointofinterest.PointOfInterest;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PointOfInterestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "dto.name", target = "name"),
            @Mapping(source = "dto.coordinateX", target = "coordinateX"),
            @Mapping(source = "dto.coordinateY", target = "coordinateY"),
    })
    PointOfInterest dtoToEntity(PointOfInterestRequestDTO dto);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.name", target = "name"),
            @Mapping(source = "entity.coordinateX", target = "coordinateX"),
            @Mapping(source = "entity.coordinateY", target = "coordinateY")
    })
    PointOfInterestResponseDTO toDto(PointOfInterest entity);

    @Mappings({
            @Mapping(source = "jpa.id", target = "id"),
            @Mapping(source = "jpa.name", target = "name"),
            @Mapping(source = "jpa.coordinateX", target = "coordinateX"),
            @Mapping(source = "jpa.coordinateY", target = "coordinateY")
    })
    PointOfInterest jpaToDomain(JpaPointOfInterestEntity jpa);
}
