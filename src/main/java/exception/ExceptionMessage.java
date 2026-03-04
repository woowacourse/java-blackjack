package exception;

public enum ExceptionMessage {

    INVALID_INPUT("잘못된 입력 형식입니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
