package error;

public enum ErrorMessage {
    IS_BLANK("공백이 입력되었습니다."),
    INPUT_ONLY_Y_OR_N("입력은 y 혹은 n으로만 가능합니다."),
    ONLY_PERMIT_NUMBER("숫자만 입력 가능합니다."),
    OVER_MAX_PARTICIPANT("게임의 최대 참여자는 %d명을 넘을 수 없습니다."),
    NOT_EXIST_DEALER("딜러가 존재하지 않습니다."),
    INVALID_PARTICIPANT_SIZE("게임의 최대 참여자는 %d명을 넘을 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}
