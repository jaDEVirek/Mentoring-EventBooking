package com.jadevirek.eventbooking.handlers.eceptions;

/**
 * Class for handling exception on creating team data
 */
public class CreateEntityException extends RuntimeException {

  public CreateEntityException(Throwable cause, String message) {
    super("`Creating: " + message + " failed. During some of errors`", cause);
  }
}
