public enum ErrorMessage {
    ERROR_DECK_SIZE("카드 사이즈는 %d 이어야 합니다.".formatted(Deck.CARD_SIZE)),
    ERROR_DECK_DUPLICATE("카드는 중복될 수 없습니다."),
    ERROR_INDEX_RANGE("인덱스의 범위가 올바르지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
