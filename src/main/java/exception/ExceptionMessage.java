package exception;

public enum ExceptionMessage {

    INVALID_INPUT("[ERROR] 쉼표를 기준으로 한글 또는 영문 이름을 입력해 주세요."),
    INVALID_NAME_RANGE("[ERROR] 이름은 2자 이상 7자 이하이어야 합니다."),
    INVALID_NAME_FORMAT("[ERROR] 이름은 한글 또는 영문만 가능합니다."),
    COMMAND_NOT_FOUND("[ERROR] 존재하지 않는 명령어 입니다. (y/n)"),
    DUPLICATED_PARTICIPANT_NAME("[ERROR] 중복된 참여자 이름이 존재합니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
