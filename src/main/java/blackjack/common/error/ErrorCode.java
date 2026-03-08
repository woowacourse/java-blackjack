package blackjack.common.error;

public enum ErrorCode {

    NULL_OR_EMPTY_CARDS("카드가 존재하지 않습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
