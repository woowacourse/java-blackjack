package domain;

import java.util.Arrays;
import java.util.Objects;

public enum HitOrStand {
    HIT("y"),
    STAND("n"),
    ;

    private final String command;

    HitOrStand(final String command) {
        this.command = command;
    }

    public static HitOrStand from(final String command) {
        return Arrays.stream(HitOrStand.values())
                     .filter(decision -> Objects.equals(decision.command, command))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("y나 n이어야 합니다."));
    }
}
