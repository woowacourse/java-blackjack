package blackjack.exception;

public class InvalidNameInputException extends IllegalArgumentException {
    public InvalidNameInputException(String message) {
        super(message);
    }
}
