package model.participant;

import constant.ErrorMessage;

public record PlayerName(String name) {
    public PlayerName(String name) {
        this.name = normalize(name);
    }

    private static String normalize(String value) {
        String name = value.strip();
        validateNameEmpty(name);
        return name;
    }

    private static void validateNameEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NO_EMPTY_NAME.getMessage());
        }
    }
}
