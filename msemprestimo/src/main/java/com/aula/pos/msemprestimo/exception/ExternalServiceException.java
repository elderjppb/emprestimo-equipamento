package com.aula.pos.msemprestimo.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends ApiException {

    public ExternalServiceException(HttpStatus status, String message) {
        super(status, message);
    }
}
