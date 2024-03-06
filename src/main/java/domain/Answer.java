package domain;

import java.util.Arrays;

public enum Answer {
    HIT("y"),
    STAY("n");

    private final String value;

    Answer(final String value) {
        this.value = value;
    }


    public static Answer from(final String value) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 대답입니다."));
    }
}
