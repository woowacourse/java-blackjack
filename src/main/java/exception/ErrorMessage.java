package exception;

public enum ErrorMessage {
    EMPTY_NAME("닉네임에 빈 문자열을 입력할 수 없습니다."),
    DUPLICATE_NAME("닉네임은 서로 달라야 합니다."),
    INVALID_YN("y 혹은 n만 입력 가능합니다."),
    EMPTY_DECK("덱에 카드가 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
