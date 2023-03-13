package blackjack.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final List<Score> cache = new ArrayList<>();

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score plusTenIfNotBusted() {
        if (value + ACE_ADDITIONAL_VALUE > BLACKJACK) {
            return this;
        }
        return new Score(value + ACE_ADDITIONAL_VALUE);
    }

    public boolean isBiggerThan(final Score other) {
        return value > other.value;
    }

    public boolean isOverBlackjack() {
        return value > BLACKJACK;
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
