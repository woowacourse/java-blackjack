package domain.participant;

import static constant.GameRule.MAX_NAME_LENGTH;
import static constant.GameRule.MIN_NAME_LENGTH;
import static message.ErrorMessage.PLAYER_NAME_OUT_OF_RANGE;

public record Name(String name) {

    public Name {
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_OUT_OF_RANGE.getMessage());
        }
    }
}
