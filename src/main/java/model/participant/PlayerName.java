package model.participant;

import constant.ErrorMessage;

public class PlayerName {
    private final String name;

    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        validateNameEmpty(name);
    }

    private void validateNameEmpty(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NO_EMPTY_NAME.getMessage());
        }
    }
}
