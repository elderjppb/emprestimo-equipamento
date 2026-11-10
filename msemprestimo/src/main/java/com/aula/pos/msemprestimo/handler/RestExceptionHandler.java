package com.aula.pos.msemprestimo.handler;

import com.aula.pos.msemprestimo.dto.ErrorResponse;
import com.aula.pos.msemprestimo.exception.ApiException;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        return build(ex.getStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> new ErrorResponse.FieldErrorResponse(e.getField(), e.getDefaultMessage()))
                .toList();

        return build(HttpStatus.BAD_REQUEST, "Dados inválidos", fields);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequest(Exception ex) {
        return build(HttpStatus.BAD_REQUEST, "Requisição inválida", null);
    }

    @ExceptionHandler({FeignException.ServiceUnavailable.class, RetryableException.class})
    public ResponseEntity<ErrorResponse> handleServiceUnavailable(FeignException ex) {
        return build(HttpStatus.SERVICE_UNAVAILABLE, "Serviço externo indisponível, tente novamente", null);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        return build(HttpStatus.BAD_GATEWAY, "Erro na comunicação com serviço externo", null);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "Rota não encontrada", null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Método não suportado", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message, List<ErrorResponse.FieldErrorResponse> fieldErrors) {
        return ResponseEntity.status(status).body(new ErrorResponse(LocalDateTime.now(), status, message, fieldErrors));
    }
}
