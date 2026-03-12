package domain.answer.exception;

import exception.ExceptionInformation;

import static domain.answer.DrawDecision.NO;
import static domain.answer.DrawDecision.YES;

public enum ErrorMessage implements ExceptionInformation {

    DRAW_DECISION_INPUT_ERROR(String.format("%s 또는 %s을 입력해주세요.", YES.getDecision(), NO.getDecision()))
    ;

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}
