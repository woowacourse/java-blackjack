package exception;

public enum ErrorMessage {
    EMPTY_NAME("닉네임에 빈 문자열을 입력할 수 없습니다."),
    DUPLICATE_NAME("닉네임은 서로 달라야 합니다."),
    INVALID_MONEY("베팅 금액은 숫자로 입력해야 합니다."),
    NEGATIVE_MONEY("베팅 금액은 양의 정수여야 합니다."),
    INVALID_YN("y 혹은 n만 입력 가능합니다."),
    EMPTY_DECK("덱에 카드가 없습니다."),

    CANNOT_HIT_ON_BLACKJACK("BlackJack 상태에서는 hit할 수 없습니다."),
    CANNOT_HIT_ON_BUST("BUST 상태에서는 hit할 수 없습니다."),
    CANNOT_HIT_ON_STAY("STAY 상태에서는 hit할 수 없습니다."),
    CANNOT_CALCULATE_WINNING_STATUS_ON_HIT("HIT 상태에서는 승패를 계산할 수 없습니다.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
