package view;

import java.util.Arrays;

public enum Answer {

    YES("y", true),
    NO("n", false),
    ;

    private final String input;
    private final boolean accepted;

    Answer(String input, boolean accepted) {
        this.input = input;
        this.accepted = accepted;
    }

    public static boolean selectAnswer(String input) {
        return Arrays.stream(values())
                .filter(answer -> answer.input.equals(input))
                .findFirst()
                .map(value -> value.accepted)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 예는 y, 아니오는 n을 입력하세요."));
    }
}
