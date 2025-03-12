package object.view;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String name;

    Answer(final String name) {
        this.name = name;
    }

    public static Answer from(final String name) {
        return Arrays.stream(Answer.values())
                .filter(value -> value.name.equals(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력하세요."));
    }
}
