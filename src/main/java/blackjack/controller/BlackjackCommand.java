package blackjack.controller;

import java.util.Arrays;

public enum BlackjackCommand {
    HIT("y"),
    STAY("n");

    static final String INVALID_COMMAND_MESSAGE = HIT.command + " 또는 " + STAY.command + "을 입력해야 합니다. 입력값:";

    private final String command;

    BlackjackCommand(final String command) {
        this.command = command;
    }

    public static BlackjackCommand from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.isSameCommand(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE + command));
    }

    private boolean isSameCommand(final String command) {
        return this.command.equalsIgnoreCase(command);
    }

    public boolean isHit() {
        return this == HIT;
    }
}
