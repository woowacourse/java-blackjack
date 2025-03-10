package exception;

public class IllegalBlackjackStateException extends IllegalStateException {

    private static final String prefix = "[ERROR] : ";

    public IllegalBlackjackStateException(final String exceptionMessage) {
        super(prefix + exceptionMessage);
    }
}
