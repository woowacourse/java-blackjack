public enum ErrorMessage {
    DECK_SIZE("카드 사이즈는 %d 이어야 합니다.".formatted(Deck.CARD_SIZE)),
    DECK_DUPLICATE("카드는 중복될 수 없습니다."),
    INDEX_RANGE("인덱스의 범위가 올바르지 않습니다."),

    //입력에 의한 예외가 아님. 코딩이 잘못된거임.
    HANDS_CARDS_SIZE("처음 게임 시작 시, 두장을 나눠줘야한다."),
    ACE_SCORE("ACE는 스코어가 변동된다."),
    BURST_TOTAL_SCORE("토탈 스코어는 0 이하일 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
