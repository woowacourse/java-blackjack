package common;

public enum ErrorMessage {
    INVALID_CARD_VALUE("카드 타입에 맞지 않는 값입니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
