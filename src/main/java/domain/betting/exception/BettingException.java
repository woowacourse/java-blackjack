package domain.betting.exception;

import exception.BlackjackGameException;
import exception.ExceptionInformation;

public class BettingException extends BlackjackGameException {

    public BettingException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
