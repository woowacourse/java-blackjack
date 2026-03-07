package blackjack.model;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String label;

    Answer(String label) {
        this.label = label;
    }

    public static Answer from(String label) {
        return Arrays.stream(values())
            .filter(answer -> answer.label.equals(label))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("부적절한 응답입니다."));
    }
}
