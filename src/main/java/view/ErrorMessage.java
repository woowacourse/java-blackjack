package view;

public enum ErrorMessage {
    INVALID_DRAWING_CARD_REQUEST("입력은 y 혹은 n이어야 합니다."),
    INVALID_NUMBER_OF_PLAYER("플레이어의 수는 1명 이상, 7명 이하여야 합니다."),
    NAME_IS_EMPTY("이름은 공백일 수 없습니다."),
    NAME_IS_NULL("이름은 null일 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
