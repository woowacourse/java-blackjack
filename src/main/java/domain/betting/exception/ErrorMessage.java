package domain.betting.exception;

import domain.common.exception.ExceptionInformation;

public enum ErrorMessage implements ExceptionInformation {

    BETTING_MONEY_IS_NOT_STRING("베팅 금액은 문자일 없습니다. 다시 입력해주세요."),
    BETTING_MONEY_IS_NOT_NEGATIVE("베팅 금액은 음수일 수 없습니다. 다시 입력해주세요."),
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
