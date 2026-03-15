package domain.participant;

import static message.ErrorMessage.PLAYER_NAME_OUT_OF_RANGE;

public record Name(String name) {
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;

    public Name {
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_OUT_OF_RANGE.getMessage());
        }
    }
}
