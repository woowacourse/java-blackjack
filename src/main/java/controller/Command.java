package controller;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        return Arrays.stream(Command.values())
                .filter(e -> e.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Command는 y, n 만 입력 가능합니다"));
    }
}
