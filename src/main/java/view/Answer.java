package view;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String input;

    Answer(String input) {
        this.input = input;
    }

    public static Answer of(String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.input.equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] y 또는 n 으로 입력해주세요."));
    }
}
