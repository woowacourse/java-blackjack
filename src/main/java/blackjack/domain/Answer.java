package blackjack.domain;

import java.util.Arrays;

public enum Answer {
    YES("y"), NO("n");

    private static final String INVALID_VALUE_ERROR = "입력값이 y 또는 n이 아닙니다.";

    private final String value;

    Answer(String value) {
        this.value = value;
    }

    public static Answer from(String value) {
        return Arrays.stream(values())
                .filter(answer -> answer.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_VALUE_ERROR));
    }

    public boolean isYes() {
        return this.equals(YES);
    }
}
