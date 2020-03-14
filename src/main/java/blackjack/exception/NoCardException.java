package blackjack.exception;

public class NoCardException extends RuntimeException {
    public NoCardException (String message) {
        super(message);
    }
}
