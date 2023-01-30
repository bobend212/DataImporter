package com.example.DataImporter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
