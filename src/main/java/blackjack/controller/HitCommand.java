package blackjack.controller;

import java.util.Arrays;

public enum HitCommand {
    TRY("y"),
    END("n"),
    ;

    final String command;

    HitCommand(final String command) {
        this.command = command;
    }

    public static HitCommand of(String inputCommand) {
        return Arrays.stream(values())
                .filter(clientCommand -> clientCommand.command.equalsIgnoreCase(inputCommand))
                .findAny()
                .get();
    }

    public boolean isQuit() {
        return this != HitCommand.END;
    }

}
