package blackjack.exception;

public class UserNameEmptyException extends IllegalArgumentException {
    public UserNameEmptyException(String message) {
        super(message);
    }
}
