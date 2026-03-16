package exception;

public enum ErrorMessage {
    PLAYER_NAME_IS_EMPTY("플레이어의 이름은 빈 값이 아니여야 합니다."),
    PLAYER_NAME_IS_DUPLICATE("플레이어의 이름은 중복되지 않아야 합니다."),
    PLAYERS_INVALID_COUNT("플레이어의 수는 1명 이상 8명 이하여야 합니다."),

    MONEY_INVALID_FORMAT("베팅 금액은 숫자만 가능합니다."),
    MONEY_INVALID_RANGE("베팅 금액은 1,000원 이상 300,000원 이하로 입력해주세요."),
    MONEY_INVALID_UNIT("베팅 금액은 10원 단위이어야 합니다."),

    CARD_SUIT_NOT_EXIST("존재하지 않는 카드 문양입니다."),
    CARD_VALUE_NOT_EXIST("존재하지 않는 카드 점수입니다.")
    ;

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(PREFIX + message);
    }
}
