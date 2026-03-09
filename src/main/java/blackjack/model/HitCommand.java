package blackjack.model;

import blackjack.exception.ErrorMessage;

import java.util.Arrays;

public enum HitCommand {
    YES("y"),
    NO("n");

    private final String command;

    HitCommand(String command) {
        this.command = command;
    }

    public static HitCommand from(String input) {
        return Arrays.stream(values())
                .filter(hitCommand -> hitCommand.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage()));
    }

    public boolean isHit() {
        return this == YES;
    }
}
