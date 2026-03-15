package domain.answer.exception;

import exception.BlackjackGameException;
import exception.ExceptionInformation;

public class AnswerException extends BlackjackGameException {

    public AnswerException(ExceptionInformation exceptionInformation) {
        super(exceptionInformation);
    }

}
