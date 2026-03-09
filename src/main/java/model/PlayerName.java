package model;


import constant.ErrorMessage;

public record PlayerName(String value) {
    public PlayerName {
        if (value.isBlank()) {
            ErrorMessage.INPUT_IS_BLANK.throwException();
        }
    }
}

