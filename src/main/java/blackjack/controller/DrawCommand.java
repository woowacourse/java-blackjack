package blackjack.controller;

import java.util.Arrays;

public enum DrawCommand {

    DRAW("y"),
    STAY("n");
    public final String command;

    DrawCommand(final String command) {
        this.command = command;
    }

    public static DrawCommand from(final String command) {
        return Arrays.stream(values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y, n 만 입력 가능합니다"));
    }

}
