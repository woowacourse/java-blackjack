package blackjack.domain;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer of(String answer) {
        return Arrays.stream(values())
                .filter(value -> value.answer.equals(answer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isYes(Answer answer) {
        return YES.equals(answer);
    }
}
