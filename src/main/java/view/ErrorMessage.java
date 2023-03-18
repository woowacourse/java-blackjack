package view;

public enum ErrorMessage {
    ERROR_TAG("[ERROR]"),
    BLANK(" "),
    INVALID_DRAWING_CARD_REQUEST("입력은 y 혹은 n이어야 합니다."),
    INVALID_NUMBER_OF_PLAYER("플레이어의 수는 1명 이상, 7명 이하여야 합니다."),
    NAME_IS_EMPTY("이름은 공백일 수 없습니다."),
    NAME_IS_NULL("이름은 null일 수 없습니다."),
    NAME_IS_DEALER("이름은 '딜러'일 수 없습니다."),
    NAME_IS_DUPLICATED("이름이 중복되었습니다."),
    NAME_CONTAINS_COMMA("이름에 쉼표(,)가 들어갈 수 없습니다."),
    INVALID_NAME_LENGTH("이름의 길이는 10자 이하여야 합니다."),
    NO_DEALER("딜러가 존재하지 않습니다."),
    DECK_IS_EMPTY("덱이 비었습니다."),
    NOT_NATURAL_NUMBER("입력이 자연수가 아닙니다."),
    NO_RESULT("결과가 존재하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_TAG.message + BLANK.message + message;
    }
}
