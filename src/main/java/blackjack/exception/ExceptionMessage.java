package blackjack.exception;

public enum ExceptionMessage {

    INVALID_INPUT("잘못된 입력 형식입니다."),
    INVALID_NUMBER("잘못된 숫자 형식입니다."),
    INVALID_NAME_RANGE("잘못된 이름 범위 입니다."),
    INVALID_NAME_FORMAT("잘못된 이름 형식 입니다."),
    COMMAND_NOT_FOUND("존재하지 않는 명령어 입니다."),
    DUPLICATED_PARTICIPANT_NAME("중복된 참여자 이름 입니다."),
    BETTING_AMOUNT_OUT_OF_RANGE("가능한 베팅 금액 범위를 벗어났습니다."),
    INVALID_BETTING_UNIT("베팅 단위는 %d원여야 합니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
