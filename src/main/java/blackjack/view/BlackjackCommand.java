package blackjack.view;

import java.util.Arrays;

public enum BlackjackCommand {
    HIT("y"),
    STAY("n");

    private final String value;

    private BlackjackCommand(String value) {
        this.value = value;
    }

    public static BlackjackCommand from(String value) {
        return Arrays.stream(BlackjackCommand.values())
                     .filter(command -> value == command.value)
                     .findFirst()
                     .orElseThrow();
    }
}
