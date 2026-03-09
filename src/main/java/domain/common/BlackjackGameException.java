package domain.common;

public class BlackjackGameException extends IllegalArgumentException {
    public BlackjackGameException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation.getErrorMessage());
    }
}
