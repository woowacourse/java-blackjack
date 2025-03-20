package console;

import java.util.Arrays;

public enum Answer {
    YES("y", true),
    NO("n", false);

    private final String input;
    private final boolean isYes;

    Answer(String input, boolean isYes) {
        this.input = input;
        this.isYes = isYes;
    }

    public static Answer of(String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.input.equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] y 또는 n 으로 입력해주세요."));
    }

    public boolean isYes() {
        return isYes;
    }
}
