package domain.betting.exception;

import exception.BlackjackGameException;
import exception.ExceptionInformation;

public class MoneyException extends BlackjackGameException {

    public MoneyException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
