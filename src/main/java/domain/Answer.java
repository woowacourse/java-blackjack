package domain;

import constants.ErrorCode;
import exception.InvalidCommandException;
import java.util.Arrays;

public enum Answer {

    HIT("y"),
    STAY("n");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }

    public static Answer from(final String value) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(ErrorCode.INVALID_COMMAND));
    }

    public boolean isHit() {
        return HIT.equals(this);
    }
}
