package model.card.exception;

public class InvalidDeckSizeException extends RuntimeException {
  public InvalidDeckSizeException(String message) {
    super(message);
  }
}
