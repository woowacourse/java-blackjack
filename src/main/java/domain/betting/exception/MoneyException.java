package domain.betting.exception;

import domain.common.exception.BlackjackGameException;
import domain.common.exception.ExceptionInformation;

public class MoneyException extends BlackjackGameException {

    public MoneyException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
