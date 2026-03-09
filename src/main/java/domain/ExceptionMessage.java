package domain;

public enum ExceptionMessage {
    BLANK_NAME_NOT_ALLOWED("빈 값을 입력할 수 없습니다."),
    NAME_OUT_OF_RANGE(String.format("이름은 %d ~ %d자 내여야 합니다.", 1, 10)),
    ALREADY_EXIST_NAME("이름은 중복될 수 없습니다."),
    EMPTY_CARDS("뽑을 수 있는 카드가 존재하지 않습니다."),
    INVALID_HIT_STAND_RESPONSE("입력은 y 또는 n으로만 입력해야 합니다."),
    SCORE_OUT_OF_RANGE(String.format("점수는 %d ~ %d점 사이여야 합니다.", 1, 11));
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
