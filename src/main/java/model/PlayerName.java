package model;

import constant.PlayerErrorCode;
import exception.GameException;

public record PlayerName(String value) {
    public PlayerName {
        if (value.isBlank()) {
            throw new GameException(PlayerErrorCode.INPUT_IS_BLANK);
        }
    }
}
