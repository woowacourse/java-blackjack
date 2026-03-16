package dto.status;

import constant.ErrorMessage;

public record PlayerName(String name) {
    public PlayerName {
        validate(name);
    }

    private void validate(String name) {
        validateNameEmpty(name);
    }

    private void validateNameEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NO_EMPTY_NAME.getMessage());
        }
    }
}
