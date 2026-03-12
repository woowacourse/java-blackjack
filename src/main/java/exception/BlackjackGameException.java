package exception;

public class BlackjackGameException extends RuntimeException {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public BlackjackGameException(ExceptionInformation exceptionInformation) {
        super(ERROR_MESSAGE_PREFIX + exceptionInformation.getErrorMessage());
    }

}
