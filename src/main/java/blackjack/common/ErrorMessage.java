package blackjack.common;

public enum ErrorMessage {
    INVALID_CARD_VALUE("카드 타입에 맞지 않는 값입니다."),
    INVALID_DECK_SIZE("카드 개수는 52개여야 합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
