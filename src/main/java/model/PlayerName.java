package model;

import exception.GameException;

public record PlayerName(String value) {

    public PlayerName {
        if (value.isBlank()) {
            throw new GameException("빈 값을 입력하셨습니다.");
        }
    }
}
