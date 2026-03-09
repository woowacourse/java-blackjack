package domain;

import constant.PolicyConstant;
import exception.ErrorMessage;

public record PlayerName(String name) {

    public PlayerName {
        validate(name);
    }

    private void validate(String name) {
        validateNotBlank(name);
        validatePlayerNameLength(name);
    }

    private void validatePlayerNameLength(String name) {
        if (!(PolicyConstant.PLAYER_NAME_MIN_LENGTH <= name.length()
                && name.length() <= PolicyConstant.PLAYER_NAME_MAX_LENGTH)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateNotBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_BLANK.getMessage());
        }
    }
}
