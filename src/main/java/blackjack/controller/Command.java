package blackjack.controller;

import java.util.Arrays;

public enum Command {
    DRAW("y"),
    STAND("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력하세요."));
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
