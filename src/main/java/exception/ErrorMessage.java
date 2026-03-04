package exception;

public enum ErrorMessage {
    EMPTY_DECK("덱에 카드가 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
