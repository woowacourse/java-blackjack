package domain.betting.exception;

import exception.ExceptionInformation;

public enum ErrorMessage implements ExceptionInformation {

    BETTING_MONEY_IS_NOT_STRING("베팅 금액은 문자일 없습니다. 다시 입력해주세요."),
    BETTING_MONEY_IS_NOT_NEGATIVE("베팅 금액은 음수일 수 없습니다. 다시 입력해주세요."),

    DUPLICATED_PLAYER_BETTING("베팅을 두 번 이상할 수 없습니다."),
    DUPLICATED_PLAYER_PROFIT("한 베팅금액에 두 번 이상의 수익이 발생할 수 없습니다."),
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
