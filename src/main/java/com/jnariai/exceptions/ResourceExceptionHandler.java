package com.jnariai.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler (EntityNotFoundException.class)
  @ResponseStatus (HttpStatus.NOT_FOUND)
  @Hidden
  public ResponseEntity<Object> notFoundException(EntityNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler (EntityExistsException.class)
  @ResponseStatus (HttpStatus.CONFLICT)
  @Hidden
  public ResponseEntity<Object> entityAlreadyExist(EntityExistsException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler (InsufficientFundsException.class)
  @ResponseStatus (HttpStatus.PAYMENT_REQUIRED)
  @Hidden
  public ResponseEntity<Object> insufficientFunds(InsufficientFundsException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.PAYMENT_REQUIRED);
  }

  @ExceptionHandler (value = {IllegalArgumentException.class})
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @Hidden
  public ResponseEntity<Object> illegalArgument(IllegalArgumentException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

}
