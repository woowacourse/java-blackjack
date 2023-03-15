package domain.card;

import java.util.Objects;

public class Score {
    private static final Score BLACK_JACK_SCORE = new Score(21);
    private static final Score ACE_OFFSET_SCORE = new Score(10);
    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean isBust() {
        return isGreaterThan(BLACK_JACK_SCORE);
    }

    public boolean isAddable() {
        return isLessThan(BLACK_JACK_SCORE);
    }

    public boolean isGreaterThan(final Score other) {
        return other.value < this.value;
    }

    public boolean isLessThan(final Score other) {
        return other.value > this.value;
    }

    public Score add(final Score addValue) {
        return new Score(this.value + addValue.value);
    }

    public Score addAceOffSet() {
        return add(ACE_OFFSET_SCORE);
    }

    public boolean isAddableAceOffSet() {
        Score scoreAddedAceOffSet = addAceOffSet();
        return !scoreAddedAceOffSet.isBust();
    }

    public boolean isBlackjackScore() {
        return this.equals(BLACK_JACK_SCORE);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Score other = (Score) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
