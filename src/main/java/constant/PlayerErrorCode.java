package constant;

public enum PlayerErrorCode implements ErrorCode {
    NO_PLAYER_NAME_DEALER("PLAYER_001", "플레이어는 '딜러'라는 이름을 가질 수 없습니다."),
    DUPLICATED_NAME("PLAYER_002", "중복된 이름이 있습니다."),
    INPUT_IS_BLANK("PLAYER_003", "빈 값을 입력하셨습니다.");

    private final String code;
    private final String message;

    PlayerErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

