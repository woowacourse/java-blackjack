package domain.card.exception;

import domain.common.exception.BlackjackGameException;
import domain.common.exception.ExceptionInformation;

public class CardException extends BlackjackGameException {
    public CardException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }
}
