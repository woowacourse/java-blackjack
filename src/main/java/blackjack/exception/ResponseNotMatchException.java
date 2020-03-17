package blackjack.exception;

public class ResponseNotMatchException extends IllegalArgumentException {
    public ResponseNotMatchException(String message) {
        super(message);
    }
}
