package blackjack.object.view;

public enum ErrorMessage {
    INVALID_PLAYER_NAME_LENGTH("플레이어의 이름은 최소 2글자, 최대 5글자로 제한 한다."),
    INVALID_PLAYER_NAME_FORMAT("플레이어의 이름에 공백을 포함할 수 없다."),
    INVALID_PLAYER_COUNT("플레이어 명수는 최소 1명, 최대 6명 까지 이다."),
    NAME_CANNOT_BE_DUPLICATED("플레이어의 이름은 중복 불가하다."),
    NAME_CANNOT_BE_EQUAL_DEALER_NAME("'딜러'라는 이름은 사용이 불가능 하다."),
    INVALID_HIT_RESPONSE("플레이어의 Hit 응답이 올바르지 못합니다."),
    INVALID_BETTING_AMOUNT_FORMAT("베팅 금액은 숫자 형식으로 입력해 주세요."),
    BETTING_IS_POSSIBLE_FROM("베팅 금액은 1000원 부터 가능 합니다."),
    BETTING_IS_POSSIBLE_TO("10만원 이상은 게임이 아닌 도박 입니다. - 도박 방지 위원회");

    private static final String ERROR_SIGN = "[ERROR] ";

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_SIGN + message;
    }
}
