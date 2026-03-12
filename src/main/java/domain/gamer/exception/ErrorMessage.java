package domain.gamer.exception;

import exception.ExceptionInformation;

public enum ErrorMessage implements ExceptionInformation {

    NAME_BLANK_ERROR("이름은 빈칸일 수 없습니다."),
    NAME_LENGTH_ERROR("이름은 1자 이상, 5자 이하의 문자입니다.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}
