package com.laaz.demo.controllers;

import com.laaz.demo.entities.Error;
import com.laaz.demo.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException ex) {
        Error error = new Error(ex.getMessage(), "Not Found", ex.getErrorCode());
        return ResponseEntity.status(ex.getErrorCode()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDBException(Exception e) {
        Error error = new Error(e.getMessage(), "Conflict", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
//
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        Error error = new Error(e.getMessage(), "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

////    Not found exception
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> handleNotFoundUrlException(Exception e) {
        Error error = new Error(e.getMessage(), "Not Found", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

//    // Duplicate exception
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Error> handleDuplicateKeyException(Exception e) {
        Error error = new Error(e.getMessage(), "Conflict Duplicated", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }




}
