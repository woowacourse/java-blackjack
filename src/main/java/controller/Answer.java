package controller;

import java.util.Arrays;

public enum Answer {
    YES("y"), NO("n");

    private final String value;

    Answer(String value) {
        this.value = value;
    }

    public static Answer of(String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> input.equals(answer.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
