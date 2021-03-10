package blackjack.domain.result;

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
                .orElseThrow(() -> new IllegalArgumentException("y, n로만 대답할 수 있습니다."));
    }

    public boolean isYes() {
        return YES.equals(this);
    }
}
