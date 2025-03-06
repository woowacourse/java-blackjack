package exception;

public class ErrorException extends IllegalArgumentException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public ErrorException(String message) {
        super(ERROR_PREFIX + message);
    }
}
