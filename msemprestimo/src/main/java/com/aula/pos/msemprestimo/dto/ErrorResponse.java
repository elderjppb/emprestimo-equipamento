package com.aula.pos.msemprestimo.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime dataHora,
        HttpStatus httpStatus,
        String message,
        List<FieldErrorResponse> fieldErrors
) {
    public record FieldErrorResponse(
            String field,
            String message
    ) {
    }
}
