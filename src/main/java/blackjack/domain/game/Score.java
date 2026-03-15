package blackjack.domain.game;

public record Score(int value) {

    public boolean isBiggerThan(Score other) {
        return this.value > other.value;
    }

    public boolean isBiggerThan(final int otherValue) {
        return isBiggerThan(new Score(otherValue));
    }

    public boolean isLessThanOrEqual(final int otherValue) {
        return !isBiggerThan(otherValue);
    }

    public boolean isEqualTo(Score other) {
        return this.value == other.value;
    }

    public boolean isEqualTo(final int otherValue) {
        return isEqualTo(new Score(otherValue));
    }
}
