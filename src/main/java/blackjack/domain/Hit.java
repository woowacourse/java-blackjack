package blackjack.domain;

import java.util.Arrays;

public enum Hit {
    YES("y"),
    NO("n");

    private final String answer;

    Hit(String answer) {
        this.answer = answer;
    }

    public static Hit of(String answer) {
        return Arrays.stream(values())
                .filter(value -> value.answer.equals(answer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isYes(Hit hit) {
        return YES.equals(hit);
    }
}
