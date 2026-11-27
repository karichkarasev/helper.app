package com.sandwich.helper.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DataIntegrityViolationException ex) {

        ErrorResponse error = new ErrorResponse(
                "Этот email уже зарегистрирован"
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}