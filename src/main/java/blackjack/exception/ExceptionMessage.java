package blackjack.exception;

public enum ExceptionMessage {
    EMPTY_CARD_DECK("카드의 수가 부족합니다."),
    BEFORE_CARD_DISTRIBUTION("카드분배가 선행되어야 합니다."),
    NOT_ALLOWED_EMPTY_NICKNAME("닉네임은 공백을 허용하지 않습니다."),
    CANNOT_HIT("히트가 불가능한 상태에서 히트를 시도하고 있습니다.");


    private final String content;

    ExceptionMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
