package meesage;

public enum ErrorMessage {

    EMPTY_INPUT("[ERROR] 사용자 이름은 공백을 입력할 수 없습니다."),
    NOT_STRICT_NUMERIC("[ERROR] 입력값은 숫자여야 합니다.(0, 음수, 공백 X)"),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
