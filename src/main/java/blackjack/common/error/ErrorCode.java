package blackjack.common.error;

public enum ErrorCode {

    NULL_OR_EMPTY_CARDS("카드가 존재하지 않습니다."),
    NULL_PICK_STRATEGY("PickStrategy가 null 입니다."),
    EMPTY_CARD_DECK("카드 덱에 더 이상 카드가 존재하지 않습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
