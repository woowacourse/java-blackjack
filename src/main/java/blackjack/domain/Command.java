package blackjack.domain;

import java.util.Arrays;

public enum Command {
    HIT("y", true),
    STAY("n", false);

    private final String value;
    private final boolean hit;

    Command(final String value, final boolean hit) {
        this.value = value;
        this.hit = hit;
    }

    public static Command from(final String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n만 입력 가능합니다."));
    }

    public boolean isHit() {
        return this.hit;
    }
}
