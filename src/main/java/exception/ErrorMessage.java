package exception;

import static domain.participant.Participants.MAXIMUM_BOUND;
import static domain.participant.Participants.MINIMUM_BOUND;

public enum ErrorMessage {
    PLAYER_NAME_LENGTH_ERROR("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다."),
    INPUT_EMPTY_ERROR("빈 값을 입력할 수 없습니다."),
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
