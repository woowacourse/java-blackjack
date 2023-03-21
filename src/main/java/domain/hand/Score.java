package domain.hand;

public class Score {

    public static final Score ZERO = new Score(0);
    public static final Score BLACKJACK = new Score(21);
    public static final Score BONUS = new Score(10);

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    Score add(Score other) {
        return new Score(score + other.score);
    }

    boolean canAddBonus() {
        return !this.add(BONUS).isBust();
    }

    boolean isBust() {
        return this.isGreaterThan(BLACKJACK);
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
