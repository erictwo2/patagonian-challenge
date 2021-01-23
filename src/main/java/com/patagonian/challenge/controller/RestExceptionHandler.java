package com.patagonian.challenge.controller;

import com.patagonian.challenge.exception.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, String>> handleException(NotFoundException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  protected ResponseEntity<Map<String, String>> handleException(ConstraintViolationException exception) {
    Map<String, String> errors = new HashMap<>();

    exception
      .getConstraintViolations()
      .forEach(
        violation -> {
          errors.put(((PathImpl) violation.getPropertyPath()).getLeafNode().getName(), violation.getMessage());
        }
      );

    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
