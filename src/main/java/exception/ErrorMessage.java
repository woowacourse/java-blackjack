package exception;

import static domain.participant.Participants.MAXIMUM_BOUND;
import static domain.participant.Participants.MINIMUM_BOUND;

public enum ErrorMessage {
    PLAYER_COUNT_OUT_OF_RANGE("플레이어는 " + MINIMUM_BOUND + "명 이상, " + MAXIMUM_BOUND + "명 이하여야 합니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
