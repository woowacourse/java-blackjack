package blackjack.common.error;

public enum ErrorCode {

    NO_NAME_PARTICIPANT_NAME("참가자 이름이 존재하지 않거나 공백입니다."),
    NAME_STARTS_OR_ENDS_WITH_SPACE("이름이 공백으로 시작하거나 끝납니다."),
    DUPLICATED_NAME("중복된 이름이 존재합니다."),

    NULL_HANDS("Hands가 null 입니다."),

    NULL_OR_EMPTY_CARDS("카드가 존재하지 않습니다."),

    NULL_PICK_STRATEGY("PickStrategy가 null 입니다."),
    EMPTY_CARD_DECK("카드 덱에 더 이상 카드가 존재하지 않습니다."),

    INVALID_INPUT("'y' 또는 'n'만 입력 가능합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
