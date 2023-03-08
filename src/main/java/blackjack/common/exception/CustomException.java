package blackjack.common.exception;

public class CustomException extends RuntimeException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public CustomException(String message) {
        super(ERROR_PREFIX + message);
    }
}
