package domain.message;

public enum ExceptionMessage {

    DEALER_NAME("딜러"),
    DRAW_COMMAND("y"),
    STOP_COMMAND("n"),
    PLAYER_INVALID_NUMBERS("플레이어의 수는 최소 1명, 최대 4명입니다."),
    PLAYER_NAME_NOT_DUPLICATED("참여자의 이름은 중복이 되면 안됩니다."),
    PLAYER_NAME_NOT_DEALER("참여자의 이름은 '" + DEALER_NAME.message + "'가 되면 안됩니다."),
    NAME_IS_EMPTY("이름은 비어있을 수 없습니다."),
    NAME_LENGTH_OVER("이름의 길이는 10자를 초과할 수 없습니다."),
    CARD_DECK_INVALID_SIZE("전체 카드의 수는 52장이어야 합니다."),
    CARD_DECK_DUPLICATED("중복된 카드가 존재합니다."),
    COMMAND_NOT_PERMITTED("y혹은 n을 입력해주세요."),
    BETTING_MONEY_NEED_MORE("배팅 금액은 최소 1000원입니다.");

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
