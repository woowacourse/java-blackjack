package blackjack.exception;

public class UserNameEmptyException extends RuntimeException {
    public UserNameEmptyException(String message) {
        super(message);
    }
}
