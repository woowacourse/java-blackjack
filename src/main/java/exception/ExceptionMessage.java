package exception;

public enum ExceptionMessage {
    EMPTY_CARD_DECK("[ERROR] 카드가 모두 소진되었습니다."),
    INPUT_ERROR("[ERROR] 잘못된 입력입니다."),
    MONEY_INPUT_ERROR("[ERROR] 1000 이상의 숫자를 입력해주시기 바랍니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
