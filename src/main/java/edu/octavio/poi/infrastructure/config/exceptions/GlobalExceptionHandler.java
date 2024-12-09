package edu.octavio.poi.infrastructure.config.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.octavio.poi.domain.error.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(PointOfInterestNotFoundException.class)
    private ResponseEntity<ErrorResponseDTO> pointOfInterestNotFoundHandler(PointOfInterestNotFoundException exception) {
        return ResponseEntity.status(404).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<ErrorResponseDTO> generalHandler(Throwable exception) {
        final String message = "Unexpected server error.";
        LOGGER.error(message, exception);
        return ResponseEntity.status(500).body(new ErrorResponseDTO(message));
    }
}