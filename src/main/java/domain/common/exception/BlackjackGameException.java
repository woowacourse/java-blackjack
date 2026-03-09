package domain.common.exception;

public class BlackjackGameException extends IllegalArgumentException {
    public BlackjackGameException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation.getErrorMessage());
    }
}
