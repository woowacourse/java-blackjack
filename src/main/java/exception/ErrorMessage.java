package exception;

public enum ErrorMessage {
    PLAYER_DUPLICATED("게임 참가자의 이름은 중복 되어선 안됩니다."),
    PLAYER_COUNT_OUT_OF_RANGE("게임 참가자의 수는 2~8명 사이여야 합니다."),
    INVALID_YES_NO_INPUT("\"y\" 또는 \"n\"을 입력해야 합니다."),
    PLAYER_NAME_LENGTH_OUT_OF_RANGE("게임 참가자의 이름은 2~5글자 사이여야 합니다."),
    PLAYER_NAME_BLANK("게임 참가자의 이름은 공백이 될 수 없습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
