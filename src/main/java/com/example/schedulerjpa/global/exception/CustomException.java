package com.example.schedulerjpa.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final Exceptions exceptions;

  public CustomException(Exceptions exceptions) {
    super(exceptions.getMessage());
    this.exceptions = exceptions;
  }

  public CustomException(Exceptions exceptions, String message) {
    super(message);
    this.exceptions = exceptions;
  }
}