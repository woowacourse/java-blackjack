package view;

import java.util.Arrays;

public enum Answer {
    YES("y", true),
    NO("n", false);

    private final String name;
    private final boolean state;

    Answer(final String name, final boolean state) {
        this.name = name;
        this.state = state;
    }

    public static boolean from(final String name) {
        return Arrays.stream(Answer.values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .map(value -> value.state)
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력하세요."));
    }
}
