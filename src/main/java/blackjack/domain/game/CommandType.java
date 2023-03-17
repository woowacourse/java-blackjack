package blackjack.domain.game;

import java.util.Arrays;

public enum CommandType {
    YES("y", true), NO("n", false);

    private final String command;
    private final boolean canHit;

    CommandType(final String command, final boolean canHit) {
        this.command = command;
        this.canHit = canHit;
    }

    public static boolean canHit(final String command) {
        return Arrays.stream(values())
                .filter(commandType -> commandType.command.equals(command.toLowerCase()))
                .map(commandType -> commandType.canHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 커맨드입니다."));
    }
}
