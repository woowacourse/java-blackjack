package error;

public enum ErrorMessage {
    INVALID_HIT_OR_STAND("y 또는 n만 입력해주세요."),
    INVALID_BETTING_FORMAT("숫자만 입력해주세요."),
    INVALID_BETTING_AMOUNT("배팅 금액은 1 이상이어야 합니다."),
    EMPTY_PLAYER_NAMES("플레이어 이름을 한 명 이상 입력해주세요."),
    INVALID_PLAYER_NAME("플레이어 이름은 비어 있을 수 없습니다."),
    DUPLICATED_PLAYER_NAME("중복된 플레이어 이름은 허용되지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
