package domain;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n")
    ;

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public static Command from(String input) {
        return Arrays.stream(Command.values())
                .filter(c -> c.input.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public boolean isNo() {
        return this == NO;
    }
}
