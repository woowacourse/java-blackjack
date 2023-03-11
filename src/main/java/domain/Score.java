package domain;

public class Score {

    private static final int MIN = 0;

    public static final Score BLACKJACK = new Score(21);
    public static final Score BONUS = new Score(10);

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score() {
        this(MIN);
    }

    public Score add(Score other) {
        return new Score(score + other.score);
    }

    public boolean canAddBonus() {
        return this.add(BONUS).isNotBust();
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK);
    }

    private boolean isNotBust() {
        return !isBust();
    }

    public boolean isGreaterThan(Score other) {
        return score > other.score;
    }

    public boolean isSameWith(Score other) {
        return score == other.score;
    }

    public boolean isLessThan(Score other) {
        return this.score < other.score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score)o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
