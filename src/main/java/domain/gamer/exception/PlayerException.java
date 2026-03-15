package domain.gamer.exception;

import exception.BlackjackGameException;
import exception.ExceptionInformation;

public class PlayerException extends BlackjackGameException {

    public PlayerException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
