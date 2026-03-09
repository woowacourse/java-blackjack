package blackjack.model;

public enum ErrorMessage {

    ERROR_EMPTY_INPUT("입력값은 공백일 수 없습니다."),
    ERROR_NOT_Y_N_INPUT("입력값은 y 또는 n만 가능합니다."),
    ERROR_INVALID_PLAYER_NAME("플레이어의 이름은 영어 or 한글로만 이루어질 수 있습니다."),
    ;

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

