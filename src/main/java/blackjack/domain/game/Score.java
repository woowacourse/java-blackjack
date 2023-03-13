package blackjack.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {

    private static final Score min = new Score(0);
    private static final Score max = new Score(21);
    private static final Score aceAdditionalValue = new Score(10);
    private static final List<Score> cache = new ArrayList<>();

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score plusTenIfNotBusted() {
        if (value + aceAdditionalValue.value > max.value) {
            return this;
        }
        return new Score(value + aceAdditionalValue.value);
    }

    public boolean isMax() {
        return this == max;
    }

    public boolean isOverThanMax() {
        return value > max.value;
    }

    public boolean isBiggerThan(final Score other) {
        return value > other.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }

}
