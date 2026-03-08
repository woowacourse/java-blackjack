package meesage;

public enum ErrorMessage {

    EMPTY_INPUT("[ERROR] 사용자 이름은 공백을 입력할 수 없습니다."),
    INVALID_NAMES_SIZE("[ERROR] 하나 이상의 사용자 이름을 입력하세요."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
