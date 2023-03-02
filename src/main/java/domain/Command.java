package domain;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    HOLD("n"),
    ;

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String input) {
        return Arrays.stream(Command.values())
                .filter(c -> c.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y와 n만 입력 가능합니다."));
    }
}
