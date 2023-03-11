package view;

public enum ExceptionMessage {
    INVALID_DRAWING_CARD_REQUEST("입력은 y 혹은 n이어야 합니다."),
    INVALID_NUMBER_OF_PLAYER("플레이어의 수는 1명 이상, 7명 이하여야 합니다."),
    NAME_IS_EMPTY("이름은 공백일 수 없습니다."),
    NAME_IS_NULL("이름은 null일 수 없습니다."),
    NAME_IS_DEALER("이름은 '딜러'일 수 없습니다."),
    NAME_IS_DUPLICATED("참가자들의 이름에 중복이 있으면 안됩니다."),
    NAME_CONTAINS_COMMA("이름에 쉼표(,)가 들어갈 수 없습니다."),
    INVALID_NAME_LENGTH("이름의 길이는 10자 이하여야 합니다."),
    NO_DEALER("딜러가 존재하지 않습니다."),
    WRONG_ACCESS("플레이어가 접근할 수 없는 메서드입니다."),
    LEAK_BET_AMOUNT("배팅 금액은 최소 1000원 입니다."),
    TYPE_MISS_MATCH_BET_AMOUNT("배팅 금액은 정수만 가능합니다.");

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
