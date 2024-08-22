package com.ntconsult.desafio_java.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ValidationResponse> handleInvalidRequestException(InvalidRequestException ex,
                                                                                  HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationResponse response = new ValidationResponse(new Date(), "Invalid Request", status.value(),
                ex.getMessage(), request.getRequestURI());

        for (FieldMessage f : ex.getFields()) {
            response.addError(f.getFieldName(), f.getMessage());
        }

        return ResponseEntity.status(status).body(response);
    }
}
