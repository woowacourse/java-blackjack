package blackjack.controller;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

public enum BlackjackCommand {
    HIT("y"),
    STAY("n");

    private final String command;

    BlackjackCommand(final String command) {
        this.command = command;
    }

    public static BlackjackCommand from(final String command) {
        var commandMessages = Arrays.stream(values())
                .map(value -> value.command)
                .collect(joining(" 또는 "));
        return Arrays.stream(values())
                .filter(value -> value.isSameCommand(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(commandMessages + "을 입력해야 합니다. 입력값:" + command));
    }

    private boolean isSameCommand(final String command) {
        return this.command.equalsIgnoreCase(command);
    }
}
