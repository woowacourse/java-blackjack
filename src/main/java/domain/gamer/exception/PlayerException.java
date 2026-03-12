package domain.gamer.exception;

import domain.common.exception.BlackjackGameException;
import domain.common.exception.ExceptionInformation;

public class PlayerException extends BlackjackGameException {
    public PlayerException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }
}
