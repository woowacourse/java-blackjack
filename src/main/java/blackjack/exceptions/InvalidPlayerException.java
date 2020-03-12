package blackjack.exceptions;

public class InvalidPlayerException extends IllegalArgumentException {
    public InvalidPlayerException(final String message) {
        super(message);
    }
}
