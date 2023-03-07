package domain.participant;

public final class Score {

    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
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

    private Score add(Score score) {
        return new Score(this.value + score.value);
    }

    private boolean isSmallerOrEqualsTo(Score score) {
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
}
