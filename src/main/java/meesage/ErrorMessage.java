package meesage;

public enum ErrorMessage {

    EMPTY_INPUT("[ERROR] 사용자 이름은 공백을 입력할 수 없습니다."),
    INVALID_NAMES_SIZE("[ERROR] 하나 이상의 사용자 이름을 입력하세요."),
    INVALID_YES_OR_NO_INPUT("[ERROR] 카드를 추가할 경우 y, 추가하지 않을 경우 n을 입력하세요."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
