package com.jnariai.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler (EntityNotFoundException.class)
  public ResponseEntity<Object> notFoundException(String message) {
    return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler (EntityExistsException.class)
  public ResponseEntity<Object> entityAlreadyExist(String message) {
    return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler (InsufficientFundsException.class)
  public ResponseEntity<Object> insufficientFunds() {
    return new ResponseEntity<>("Insufficient Funds!", new HttpHeaders(), HttpStatus.PAYMENT_REQUIRED);
  }

}
