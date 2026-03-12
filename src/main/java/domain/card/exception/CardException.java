package domain.card.exception;

import exception.BlackjackGameException;
import exception.ExceptionInformation;

public class CardException extends BlackjackGameException {

    public CardException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
