package common;

public enum ErrorMessage {
    HIT_OR_STAND_VALUE_MIS_MATCH("y 혹은 n만 입력 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
