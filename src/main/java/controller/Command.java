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
        return Arrays.stream(values())
                .filter(s -> s.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y, n 만 입력 가능합니다"));
    }

}
