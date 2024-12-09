package edu.octavio.poi.adapters.inbound.controller;

import edu.octavio.poi.application.service.PointOfInterestServiceImpl;
import edu.octavio.poi.application.usecases.PointOfInterestUseCases;
import edu.octavio.poi.domain.error.ErrorResponseDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestRequestDTO;
import edu.octavio.poi.domain.pointofinterest.PointOfInterestResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poi")
@Tag(name = "Points of Interest controller", description = "RESTful API for managing points of interest.")
public class PointOfInterestController {
    private final PointOfInterestUseCases service;

    public PointOfInterestController(PointOfInterestUseCases service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all points of interest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(
                array = @ArraySchema(schema = @Schema(implementation = PointOfInterestResponseDTO.class)),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<List<PointOfInterestResponseDTO>> getAllPointsOfInterest() {
        return ResponseEntity.ok(this.service.getAllPointOfInterests());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get point of interest by id", description = "Retrieve the point of interest with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(
                    schema = @Schema(implementation = PointOfInterestResponseDTO.class), mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "404", description = "Not found point of interest with specified id", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<PointOfInterestResponseDTO> getPointOfInterestById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getPointOfInterestById(id));
    }

    @PostMapping
    @Operation(summary = "Create point of interest", description = "Create a new point of interest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successful", content = @Content(
                    schema = @Schema(implementation = PointOfInterestResponseDTO.class),
                    mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "400", description = "Error while creating point of interest", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "422", description = "Error while creating point of interest", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<PointOfInterestResponseDTO> createPointOfInterest(@RequestBody PointOfInterestRequestDTO data) {
        PointOfInterestResponseDTO createdPointOfInterest = this.service.createPointOfInterest(data);
        return ResponseEntity.status(201).header("Location", "/poi/" + createdPointOfInterest.id()).body(createdPointOfInterest);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete point of interest", description = "Delete the point of interest with the specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully", content = @Content(
                schema = @Schema(implementation = Void.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<Void> deletePointOfInterestById(@PathVariable Long id) {
        this.service.deletePointOfInterest(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Update point of interest", description = "Update the data of a point of interest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully", content = @Content(
                schema = @Schema(implementation = PointOfInterestResponseDTO.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "404", description = "Not found point of interest with specified id", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<PointOfInterestResponseDTO> updatePointOfInterest(@PathVariable Long id, @RequestBody PointOfInterestRequestDTO data) {
        return ResponseEntity.ok(this.service.updatePointOfInterest(id, data));
    }

    @GetMapping("/range")
    @Operation(summary = "Get points of interest in range", description = "Retrieve a list of points of interest in range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(
            array = @ArraySchema(schema = @Schema(implementation = PointOfInterestResponseDTO.class)),
            mediaType = "application/json"
        )),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                mediaType = "application/json"
            ))
    })
    public ResponseEntity<List<PointOfInterestResponseDTO>> listPointOfInterestInRange(@RequestParam Long positionX, @RequestParam Long positionY, @RequestParam Long distance) {
        return ResponseEntity.ok(this.service.listPointOfInterestInRange(positionX, positionY, distance));
    }
}
