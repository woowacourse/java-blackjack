package domain;

import java.util.Arrays;

public enum PlayerAnswer {
    YES("y"),
    NO("n");

    private String answer;

    PlayerAnswer(String answer) {
        this.answer = answer;
    }

    public static PlayerAnswer of(String answer) {
        return Arrays.stream(values())
                .filter(v -> v.answer.equals(answer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y와 n만 입력 가능합니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
