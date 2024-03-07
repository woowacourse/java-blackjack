package domain;

import java.util.Arrays;

public enum Command {
    YES("y"), NO("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command get(String input) {
        return Arrays.stream(values())
                .filter(command -> command.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("명령어는 y,n만 가능합니다."));
    }
}
