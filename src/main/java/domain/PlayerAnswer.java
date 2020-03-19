package domain;

import java.util.Arrays;

public enum PlayerAnswer {
    ANSWER_YES("y"),
    ANSWER_NO("n");

    private final String answer;

    PlayerAnswer(String answer) {
        this.answer = answer;
    }

    public static PlayerAnswer of(String answer) {
        return Arrays.stream(values())
                .filter(value -> value.answer.equals(answer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public boolean isAgree() {
        return this == ANSWER_YES;
    }
}
