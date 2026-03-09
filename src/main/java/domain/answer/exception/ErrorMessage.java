package domain.answer.exception;

import domain.common.exception.ExceptionInformation;

import static domain.answer.Answer.NO;
import static domain.answer.Answer.YES;

public enum ErrorMessage implements ExceptionInformation {

    DRAW_DECISION_INPUT_ERROR(String.format("%s 또는 %s을 입력해주세요.", YES.getAnswer(), NO.getAnswer()))
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
