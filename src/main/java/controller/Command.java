package controller;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static boolean isRetry(String value) {
        return findBy(value) == YES;
    }

    private static Command findBy(String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow();
    }
}
