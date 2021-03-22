package blackjack.domain.state.hand;

import java.util.Objects;

public class Score {

    private static final Score BLACKJACK = new Score(21);
    private static final Score CHANGE_TO_ACE = new Score(10);
    private static final Score DEALER_HIT = new Score(16);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score changeHardAce() {
        if (this.isBust()) {
            return this.minus(CHANGE_TO_ACE);
        }
        return this;
    }

    public boolean isBust() {
        return this.isHigher(BLACKJACK);
    }

    public boolean isDealerLimitScore() {
        return this.isHigher(DEALER_HIT);
    }

    public boolean isBlackjack() {
        return this.equals(BLACKJACK);
    }

    public boolean isHigher(final Score score) {
        return this.value > score.value;
    }

    private Score minus(Score score) {
        return new Score(this.value - score.value);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
