package domain.answer.exception;

import domain.common.exception.BlackjackGameException;
import domain.common.exception.ExceptionInformation;

public class AnswerException extends BlackjackGameException {
    public AnswerException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }
}
