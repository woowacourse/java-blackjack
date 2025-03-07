package blackjack.controller;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }

    public static Answer from(final String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 응답입니다. 다시 입력해주세요."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
