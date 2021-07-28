package com.example.wellD.linesegment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Generic response class used for error handling
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {

    private LocalDateTime timestamp;
    private String message;

}
