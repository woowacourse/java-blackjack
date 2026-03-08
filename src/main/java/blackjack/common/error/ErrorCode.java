package blackjack.common.error;

public enum ErrorCode {

    NULL_OR_EMPTY_CARDS("카드가 존재하지 않습니다."),
    NULL_PICK_STRATEGY("PickStrategy가 null 입니다."),
    EMPTY_CARD_DECK("카드 덱에 더 이상 카드가 존재하지 않습니다."),
    NO_NAME_PARTICIPANT_NAME("참가자 이름이 존재하지 않거나 공백입니다."),
    NULL_HANDS("Hands가 null 입니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
