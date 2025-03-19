package exception;

public class CustomException extends RuntimeException {

    private static final String ERROR_HEADER = "[ERROR]";

    public CustomException(String message) {
        super(ERROR_HEADER + message);
    }
}
