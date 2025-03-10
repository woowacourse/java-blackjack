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

    public Score plus(int num) {
        return new Score(value + num);
    }

    public Score minus(int num) {
        return new Score(value - num);
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(value, o.value);
    }
}
