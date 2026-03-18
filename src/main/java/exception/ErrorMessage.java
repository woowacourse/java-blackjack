package exception;

public enum ErrorMessage {
    EMPTY_NAME("닉네임에 빈 문자열을 입력할 수 없습니다."),
    INVALID_NAME("닉네임은 2~8글자여야 합니다."),
    INVALID_YN("y 혹은 n만 입력 가능합니다."),
    EMPTY_DECK("덱에 카드가 없습니다."),
    INVALID_BETTING_AMOUNT("배팅 금액은 0보다 커야합니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
