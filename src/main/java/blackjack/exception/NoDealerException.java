package blackjack.exception;

public class NoDealerException extends RuntimeException {
    public NoDealerException(String message) {
        super(message);
    }
}
