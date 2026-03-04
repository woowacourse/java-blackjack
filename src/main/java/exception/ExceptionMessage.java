package exception;

public enum ExceptionMessage {

    INVALID_INPUT("잘못된 입력 형식입니다."),
    INVALID_NAME_RANGE("잘못된 이름 범위 입니다."),
    INVALID_NAME_FORMAT("잘못된 이름 형식 입니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
