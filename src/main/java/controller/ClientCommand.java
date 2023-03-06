package controller;

import java.lang.ref.Cleaner;
import java.util.Arrays;

public enum ClientCommand {

    CONTINUE("y"),
    QUIT("n");

    private static final String NO_SUCH_COMMAND = "[ERROR] 해당 명령은 존재하지 않습니다.";

    private final String command;

    ClientCommand(final String command) {
        this.command = command;
    }

    public static ClientCommand of(String targetCommand) {
        return Arrays.stream(values())
                .filter(clientCommand -> clientCommand.command.equals(targetCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_COMMAND));
    }

    public boolean isQuit() {
        return this == ClientCommand.QUIT;
    }
}
