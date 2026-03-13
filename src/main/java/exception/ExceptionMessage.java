package exception;

public enum ExceptionMessage {
    EMPTY_CARD_DECK("[ERROR] 카드가 모두 소진되었습니다."),
    NAME_DUPLICATE_ERROR("[ERROR] 이름은 중복될 수 없습니다."),
    NAME_LENGTH_ERROR("[ERROR] 이름은 2글자 이상 10글자 이하여야 합니다."),
    NAME_NOT_INTEGER_ERROR("[ERROR] 이름에 숫자가 들어갈 수 없습니다."),
    INVALID_BET_AMOUNT("[ERROR] 배팅 단위가 올바르지 않습니다."),
    MIN_BET_AMOUNT_ERROR("[ERROR] 최소 배팅 금액 1000원 이상이어야 합니다."),
    INPUT_ERROR("[ERROR] 잘못된 입력입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
