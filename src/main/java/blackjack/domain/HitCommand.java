package blackjack.domain;

import java.util.Arrays;

public enum HitCommand {
    TRY("Y"),
    END("N"),
    ;

    final String command;

    HitCommand(final String command) {
        this.command = command;
    }

    public static HitCommand of(String inputCommand) {
        return Arrays.stream(values())
                .filter(clientCommand -> clientCommand.command.equals(inputCommand))
                .findAny()
                .get();
    }

    public boolean isQuit() {
        return this == HitCommand.END;
    }

}
