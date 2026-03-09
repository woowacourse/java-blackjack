package model;


import constant.ErrorMessage;

public record PlayerName(String value) {
    public PlayerName {
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
    }
}

