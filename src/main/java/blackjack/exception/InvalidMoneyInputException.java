package blackjack.exception;

public class InvalidMoneyInputException extends IllegalArgumentException {
    public InvalidMoneyInputException(String message) {
        super(message);
    }
}
