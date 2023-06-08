package com.nymblelabs.travelagency.Exception;

public class InvalidPostBodyException extends RuntimeException{
  private String message;

  public InvalidPostBodyException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
