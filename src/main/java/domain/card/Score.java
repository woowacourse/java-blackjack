package domain.card;

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

    public boolean isLessThan(Score other) {
        return other.value > this.value;
    }

    public boolean isGreaterThan(Score other) {
        return other.value < this.value;
    }

    public Score add(Score addValue) {
        return new Score(this.value + addValue.value);
    }

    public Score addAceOffSet() {
        return add(ACE_OFFSET_SCORE);
    }

    public boolean isAddableAceOffSet() {
        Score scoreAddedAceOffSet = addAceOffSet();
        return !scoreAddedAceOffSet.isBust();
    }

    @Override
    public boolean equals(Object obj) {
        Score other = (Score) obj;
        return this.value == other.value;
    }

    public int value() {
        return value;
    }
}
