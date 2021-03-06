package blackjack.exception;

public class NameException extends IllegalArgumentException {
    public NameException(final String errorMessage) {
        super(errorMessage);
    }
}