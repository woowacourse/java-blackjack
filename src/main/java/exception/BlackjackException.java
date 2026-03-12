package exception;

public class BlackjackException extends IllegalArgumentException {

    public static final String ERROR_PREFIX = "[ERROR] ";

    public BlackjackException(String message) {
        super(ERROR_PREFIX + message);
    }
}
