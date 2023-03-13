package blackjack.exception;

public class BlackJackGameException extends RuntimeException {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public BlackJackGameException(String message) {
        super(ERROR_PREFIX + message);
    }
}
