package domain.participant;

import java.util.Objects;

public final class Score {

    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
    private static final Score FILL_BOUNDARY_INCLUSIVE = new Score(16);
    private static final Score BONUS = new Score(10);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean canAddBonusScore() {
        return this.add(BONUS).isSmallerOrEqualsTo(BUST_BOUNDARY_EXCLUSIVE);
    }

    public Score addBonusScore() {
        return this.add(BONUS);
    }

    public boolean isBust() {
        return this.isGreaterThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public boolean isHittableForDealer() {
        return this.isSmallerOrEqualsTo(FILL_BOUNDARY_INCLUSIVE);
    }

    public boolean isHittableForPlayer() {
        return this.isSmallerThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public boolean wins(final Score score) {
        return this.isGreaterThan(score);
    }

    public boolean draws(final Score score) {
        return this.equals(score);
    }

    private boolean isSmallerThan(final Score score) {
        return this.value < score.value;
    }

    private boolean isGreaterThan(final Score score) {
        return this.value > score.value;
    }

    private Score add(Score score) {
        return new Score(this.value + score.value);
    }

    private boolean isSmallerOrEqualsTo(final Score score) {
        return this.value <= score.value;
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
