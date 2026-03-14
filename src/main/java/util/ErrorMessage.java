package util;

import static domain.card.Deck.CARD_SIZE;
import static domain.player.Money.MIN_BETTING_MONEY;


public enum ErrorMessage {
    DECK_SIZE("카드 사이즈는 %d 이어야 합니다.".formatted(CARD_SIZE)),
    DECK_DUPLICATE("카드는 중복될 수 없습니다."),
    INDEX_RANGE("인덱스의 범위가 올바르지 않습니다."),
    CARDS_IS_EMPTY("덱이 비었습니다."),
    PLAYER_NAME("플레이어 이름은 2~7자이어야 합니다."),
    INPUT_BLANK("입력값이 비어있습니다."),
    NUMBER_FORMAT_ERROR("숫자 형식이 아닙니다."),
    INPUT_WRONG("입력값은 'y' or 'n' 이어야 합니다."),
    HANDS_CARDS_SIZE("처음 게임 시작 시, 두장을 나눠줘야한다."),
    MONEY_RANGE("%d원 이상 배팅해야 합니다.".formatted(MIN_BETTING_MONEY)),
    EXIST_PLAYER("존재하지 않는 플레이어입니다."),
    ;


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
