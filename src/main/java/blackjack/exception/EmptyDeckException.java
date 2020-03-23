package blackjack.exception;

public class EmptyDeckException extends RuntimeException {
    public EmptyDeckException(String message) {
        super(message);
    }
}
