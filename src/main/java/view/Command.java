package view;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String value) {
        return Arrays.stream(Command.values())
                .filter(c -> c.command.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력입니다."));
    }

    public boolean isNo() {
        return this == NO;
    }

    public boolean isYes() {
        return this == YES;
    }
}
