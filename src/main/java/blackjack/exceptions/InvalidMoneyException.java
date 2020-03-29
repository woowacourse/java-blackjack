package blackjack.exceptions;

public class InvalidMoneyException extends IllegalArgumentException {
    public InvalidMoneyException() {
    }

    public InvalidMoneyException(final String message) {
        super(message);
    }

}
