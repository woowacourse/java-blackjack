package domain;

public enum ExceptionMessage {
    BLANK_NAME_NOT_ALLOWED("빈 값을 입력할 수 없습니다."),
    NAME_OUT_OF_RANGE(String.format("이름은 %d ~ %d자 내여야 합니다.", 1, 10)),
    ALREADY_EXIST_NAME("이름은 중복될 수 없습니다."),
    EMPTY_CARDS("뽑을 수 있는 카드가 존재하지 않습니다.");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
