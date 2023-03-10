package domain.participant;

import java.util.Arrays;
import java.util.Objects;

public enum Decision {
    HIT("y"),
    STAND("n"),
    ;

    private final String command;

    Decision(String command) {
        this.command = command;
    }

    public static Decision from(String command) {
        return Arrays.stream(Decision.values())
                .filter(decision -> Objects.equals(decision.command, command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y나 n이어야 합니다."));
    }
}
