package constant;

public enum ErrorMessage {

    INVALID_INPUT_PATTERN("입력 형식이 잘못됐습니다."),
    INVALID_BET_AMOUNT_UNIT("베팅 금액은 10 단위로 입력해야 합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
