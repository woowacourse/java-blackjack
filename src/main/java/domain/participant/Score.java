package domain.participant;

import java.util.Objects;

public final class Score {

    private static final Score MAX = new Score(21);
    private static final Score BONUS = new Score(10);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score applyBonusScore() {
        if (canAddBonusScore()) {
            return this.addBonus();
        }
        return this;
    }

    private boolean canAddBonusScore() {
        return this.addBonus().isSmallerOrEqualsTo(MAX);
    }

    public boolean isBlackJack() {
        return this.isEquals(MAX);
    }

    private Score addBonus() {
        return new Score(this.value + Score.BONUS.value);
    }

    public boolean isEquals(final Score score) {
        return this.equals(score);
    }

    public boolean isGreaterThan(final Score score) {
        return this.value > score.value;
    }

    public boolean isSmallerOrEqualsTo(final Score score) {
        return this.value <= score.value;
    }

    public boolean isSmallerThan(final Score score) {
        return this.value < score.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
