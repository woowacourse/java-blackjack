package model.score;

public class Score implements Comparable<Score> {

    private static final int BUST_CONDITION = 21;

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public boolean isBust() {
        return compareScoreCondition() > 0;
    }

    public int compareScoreCondition() {
        return value - BUST_CONDITION;
    }

    public Score plus(int sum) {
        return new Score(value + sum);
    }

    public Score minus(int sum) {
        return new Score(value - sum);
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(value, o.value);
    }
}
