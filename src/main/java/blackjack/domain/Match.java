package blackjack.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Match {
    WIN(1, "승"),
    LOSE(-1, "패"),
    DRAW(0, "무")
    ;

    private final int value;
    private final String result;

    Match(int value, String result) {
        this.value = value;
        this.result = result;
    }

    public static Optional<Match> of(int number) {
        return Arrays.stream(values())
                .filter(it -> it.value == number)
                .findFirst();
    }

    public String getResult() {
        return result;
    }

    public Match getOpposite() {
        if (this.equals(Match.WIN)) {
            return Match.LOSE;
        }
        if (this.equals(Match.LOSE)) {
            return Match.WIN;
        }
        return this;
    }
}
