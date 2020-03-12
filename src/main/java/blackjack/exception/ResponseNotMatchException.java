package blackjack.exception;

public class ResponseNotMatchException extends RuntimeException {
    public ResponseNotMatchException(String message) {
        super(message);
    }
}
