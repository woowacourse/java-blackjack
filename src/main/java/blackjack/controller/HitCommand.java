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

    public static HitCommand of(final String inputCommand) {
        return Arrays.stream(values())
                .filter(clientCommand -> clientCommand.command.equalsIgnoreCase(inputCommand))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

    public boolean isQuit() {
        return this != HitCommand.END;
    }

}
