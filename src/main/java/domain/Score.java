package domain;

public class Score {

    private static final int MIN = 0;

    private static final Score MAX = new Score(21);
    private static final Score BONUS = new Score(10);

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
        return this.addBonus().isNotBust();
    }

    public Score addBonus() {
        return this.add(BONUS);
    }

    public boolean isBust() {
        return this.isGreaterThan(MAX);
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
