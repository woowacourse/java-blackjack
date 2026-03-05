package controller;

import java.util.Arrays;

public enum Continuation {
    CONTINUE("y"),
    STOP("n");

    private final String input;

    Continuation(String input) {
        this.input = input;
    }

    public static Continuation from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y와 n을 입력해주세요."));
    }

    public boolean isStop() {
        return this == STOP;
    }

    public boolean isContinue() {
        return this == CONTINUE;
    }
}
