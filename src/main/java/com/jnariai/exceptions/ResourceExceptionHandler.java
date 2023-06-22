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
  public ResponseEntity<Object> notFoundException(EntityNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler (EntityExistsException.class)
  public ResponseEntity<Object> entityAlreadyExist(EntityExistsException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler (InsufficientFundsException.class)
  public ResponseEntity<Object> insufficientFunds(InsufficientFundsException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.PAYMENT_REQUIRED);
  }

  @ExceptionHandler (value = {IllegalArgumentException.class})
  public ResponseEntity<Object> illegalArgument(IllegalArgumentException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

}
