package blackjack.view.input;

import java.util.Arrays;

public enum BlackjackCommand {
    HIT("y"),
    STAND("n");

    private final String value;

    BlackjackCommand(final String value) {
        this.value = value;
    }

    public static BlackjackCommand from(final String value) {
        return Arrays.stream(BlackjackCommand.values())
                     .filter(command -> value.equals(command.value))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(
                             String.format("%s %s 명령어만 가능합니다.", HIT, STAND)));
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isStand() {
        return this == STAND;
    }
}
