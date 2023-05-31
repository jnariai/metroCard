package com.jnariai.exceptions;

public class RecordNotFoundException extends RuntimeException{
  public RecordNotFoundException(String id){
    super("Record with id " + id + " not found!");
  }  
}
