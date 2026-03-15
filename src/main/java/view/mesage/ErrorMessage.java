package view.mesage;

public enum ErrorMessage {
    EMPTY_NAME_INPUT("[ERROR] 사용자 이름은 공백을 입력할 수 없습니다."),
    INVALID_NAMES_EMPTY("[ERROR] 하나 이상의 사용자 이름을 입력하세요."),
    INVALID_NAMES_EXCEED_LIMIT("[ERROR] 25명 이하의 사용자 이름을 입력하세요."),
    EMPTY_BET_AMOUNT_INPUT("[ERROR]배팅 금액을 입력하세요."),
    INVALID_BET_AMOUNT("[ERROR]배팅 금액은 0보다 큰 정수만 입력 가능합니다."),
    INVALID_YES_OR_NO_INPUT("[ERROR] 카드를 추가할 경우 y, 추가하지 않을 경우 n을 입력하세요."),
    EMPTY_DECK("[ERROR] 카드 덱에 남아있는 카드가 없습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
