package blackjack.common;

public enum ErrorMessage {

    INVALID_CARD_VALUE("카드 타입에 맞지 않는 값입니다."),
    INVALID_DECK_SIZE("카드 개수는 52개여야 합니다."),
    DUPLICATED_CARD_EXISTED("중복된 카드가 존재합니다."),
    EMPTY_DECK_SIZE("모든 카드를 소진하였습니다."),
    CAN_NOT_NULL("null일 수 는 없습니다."),
    INVALID_CONFIRMATION_INPUT("y, n만 입력 가능합니다."),
    EXCEED_PLAYER_MEMBERS("7명을 초과할 수 없습니다."),
    NEED_PLAYER_MEMBERS("플레이어가 존재해야 합니다."),
    INVALID_CARD_INDEX("해당 위치에 카드를 가지고 있지 않습니다!"),
    USE_VALID_NAME("이름은 공백일 수 없습니다"),
    INVALID_TRANSLATE("잘못된 변환입니다."),
    MAXIMUM_BETTING_MONEY("최대 베팅 금액은 1억까지 가능합니다!"),
    BETTING_MONEY_IS_POSITIVE("베팅 금액은 양수여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
