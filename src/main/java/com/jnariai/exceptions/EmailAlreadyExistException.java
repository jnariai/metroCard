package com.jnariai.exceptions;

public class EmailAlreadyExistException extends RuntimeException{
  public EmailAlreadyExistException(){
    super("This email already exist");
  }  
}
