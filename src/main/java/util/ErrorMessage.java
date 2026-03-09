package util;

import domain.player.Deck;

public enum ErrorMessage {
    DECK_SIZE("카드 사이즈는 %d 이어야 합니다.".formatted(Deck.CARD_SIZE)),
    DECK_DUPLICATE("카드는 중복될 수 없습니다."),
    INDEX_RANGE("인덱스의 범위가 올바르지 않습니다."),
    PLAYER_NAME("플레이어 이름은 2~7자이어야 합니다."),
    INPUT_BLANK("입력값이 비어있습니다."),
    INPUT_WRONG("입력값은 'y' or 'n' 이어야 합니다."),

    HANDS_CARDS_SIZE("처음 게임 시작 시, 두장을 나눠줘야한다."),
    BURST_TOTAL_SCORE("토탈 스코어는 0 이하일 수 없습니다."),
    ;


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
