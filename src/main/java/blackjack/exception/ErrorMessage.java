package blackjack.exception;

public enum ErrorMessage {
    DUPLICATED("이름은 중복될 수 없습니다."),
    OUT_OF_NAME_LENGTH("이름 길이는 최소 2자 이상, 5자 이하여야 합니다."),
    INVALID_PLAYERS("플레이어는 2명 이상 5명 이하여야 합니다."),
    INVALID_INPUT("유효하지 않은 입력입니다."),
    CARD_EMPTY("카드가 비어있습니다."),
    AMOUNT_NOT_ZERO("배팅 금액은 0원 이상이여야 합니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
