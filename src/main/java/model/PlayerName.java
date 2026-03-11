package model;

import exception.GameException;

public record PlayerName(String value) {
    private static final String INPUT_IS_BLANK = "빈 값을 입력하셨습니다.";

    public PlayerName {
        if (value.isBlank()) {
            throw new GameException(INPUT_IS_BLANK);
        }
    }
}
