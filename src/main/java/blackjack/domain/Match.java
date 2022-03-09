package blackjack.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Match {
    WIN(1),
    LOSE(-1),
    DRAW(0)
    ;

    private final int result;

    Match(int result) {
        this.result = result;
    }

    public static Optional<Match> of(int result) {
        return Arrays.stream(values())
                .filter(value -> value.result == result)
                .findFirst();
    }
}
