package model;

public class Score implements Comparable {

    private static final int BUST_CONDITION = 21;

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public int compareScoreCondition() {
        return value - BUST_CONDITION;
    }

    public boolean isBust() {
        return compareScoreCondition() > 0;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(value, ((Score) o).value);
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
}
