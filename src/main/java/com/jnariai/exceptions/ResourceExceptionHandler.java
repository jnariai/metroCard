package com.jnariai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> notFoundException(){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<Object> entityAlreadyExist(){
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource already exist");
  }
  
}
