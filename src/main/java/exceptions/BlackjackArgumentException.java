package exceptions;

public final class BlackjackArgumentException extends IllegalArgumentException {

  private static final String PREFIX = "[ERROR]";

  public BlackjackArgumentException(final String message) {
    super(PREFIX + message);
  }
}
