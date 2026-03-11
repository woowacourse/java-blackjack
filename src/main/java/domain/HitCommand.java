package domain;

import java.util.Arrays;

public enum HitCommand {
    YES("y"),
    NO("n")
    ;

    private final String input;

    HitCommand(String input) {
        this.input = input;
    }

    public static HitCommand from(String input) {
        return Arrays.stream(HitCommand.values())
                .filter(c -> c.input.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
