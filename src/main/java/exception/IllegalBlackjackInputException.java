package exception;

public class IllegalBlackjackInputException extends IllegalArgumentException {

    private static final String prefix = "[ERROR] : ";

    public IllegalBlackjackInputException(final String exceptionMessage) {
        super(prefix + exceptionMessage);
    }
}
