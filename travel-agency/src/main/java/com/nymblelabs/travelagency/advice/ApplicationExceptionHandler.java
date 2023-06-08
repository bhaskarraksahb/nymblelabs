package com.nymblelabs.travelagency.advice;

import com.nymblelabs.travelagency.Exception.InvalidPostBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidPostBodyException.class)
  public String TravelPackageCreateException(InvalidPostBodyException travelPackageException) {
    return travelPackageException.getMessage();
  }
}
