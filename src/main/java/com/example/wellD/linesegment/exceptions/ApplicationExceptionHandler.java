package com.example.wellD.linesegment.exceptions;

import com.example.wellD.linesegment.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global Error management Advice for Roxor Gaming APi,
 * manipulated and form readable and meaningful error to client
 *
 * @see {@link ControllerAdvice}
 *
 */

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<GenericResponse> inputValidationException(InvalidInputException e) {
        log.error("Error during the validation - {} ", e.getMessage());
        return new ResponseEntity<>(new GenericResponse(LocalDateTime.now(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
