package domain;

public enum CommonExceptionMessage {
    FIELD_CAN_NOT_BE_NULL("[ERROR] 멤버 변수로 NULL을 넣을 수 없습니다.");

    private final String message;

    CommonExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
