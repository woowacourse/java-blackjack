package domain.participant;

public record Score(int value) {
    public static Score valueOf(final int value) {
        Score score = Scores.getScore(value);
        if (score == null) {
            return new Score(value);
        }
        return score;
    }

    public boolean isBigger(final Score other) {
        return this.value > other.value;
    }
}
