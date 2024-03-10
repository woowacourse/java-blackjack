package blackjack.exception;

public class NeedRetryException extends IllegalArgumentException {

    public NeedRetryException(final String message) {
        super(message);
    }
}
