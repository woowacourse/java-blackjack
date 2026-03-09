package domain;

import exception.ExceptionMessage;
import java.util.Arrays;

public enum GameCommand {

    Y("y"),
    N("n");

    private String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }

    public boolean isYes() {
        return this == Y;
    }
}
