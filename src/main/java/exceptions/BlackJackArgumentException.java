package exceptions;

public class BlackJackArgumentException extends RuntimeException {

  private static final String PREFIX = "[ERROR]";

  public BlackJackArgumentException(final String message) {
    super(PREFIX + message);
  }
}
