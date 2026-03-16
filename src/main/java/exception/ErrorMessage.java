package exception;

import static domain.participant.Dealer.DEALER_NAME;
import static domain.participant.Participants.MAXIMUM_BOUND;
import static domain.participant.Participants.MINIMUM_BOUND;
import static domain.participant.Player.MAXIMUM_BET_AMOUNT;
import static domain.participant.Player.MINIMUM_BET_AMOUNT;

public enum ErrorMessage {
    PLAYER_NAME_LENGTH_ERROR("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다."),
    INPUT_EMPTY_ERROR("빈 값을 입력할 수 없습니다."),
    DUPLICATE_DEALER_NAME("딜러 이름 - " + DEALER_NAME + "과 중복할 수 없습니다."),
    DUPLICATE_PLAYER_NAME("플레이어 이름은 중복될 수 없습니다."),
    BET_AMOUNT_OUT_OF_RANGE("베팅 금액은 " + MINIMUM_BET_AMOUNT + "원 이상 " + MAXIMUM_BET_AMOUNT + "원 이하여야 합니다."),
    INVALID_HIT_STAND_INPUT_ERROR("y 또는 n만 입력 가능합니다."),
    PLAYER_COUNT_OUT_OF_RANGE("플레이어는 " + MINIMUM_BOUND + "명 이상, " + MAXIMUM_BOUND + "명 이하여야 합니다."),
    DECK_EMPTY("덱에 남아있는 카드가 없습니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
