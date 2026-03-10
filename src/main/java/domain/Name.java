package domain;

import constant.PolicyConstant;
import exception.ErrorMessage;

public record Name(
    String value
) {

    public Name {
        validate(value);
    }

    private static void validate(String value) {
        validatePlayerNameBlank(value);
        validatePlayerNameLengthOutOfRange(value);
    }

    private static void validatePlayerNameLengthOutOfRange(String value) {
        if (!(PolicyConstant.PLAYER_NAME_MIN_LENGTH <= value.length()
            && value.length() <= PolicyConstant.PLAYER_NAME_MAX_LENGTH)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
        }
    }

    private static void validatePlayerNameBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_BLANK.getMessage());
        }
    }
}
