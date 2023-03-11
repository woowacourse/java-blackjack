package view;

import java.util.Arrays;

public enum Answer {

    MORE_CARD("y"),
    CARD_STOP("n");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer from(String answer) {
        return Arrays.stream(values())
                .filter(value -> value.answer.equals(answer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y 나 n 만을 입력해주세요."));
    }

    public boolean isMoreCard() {
        return this == MORE_CARD;
    }

}
