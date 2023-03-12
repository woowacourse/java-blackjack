package domain.command;

import domain.message.ExceptionMessage;
import java.util.Arrays;

public enum Command {
    DRAW("y"),
    STOP("n");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command fromCommand(final String command) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_PERMITTED.getMessage()));
    }
}
