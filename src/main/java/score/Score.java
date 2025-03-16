package score;

public class Score implements Comparable<Score> {

    private static final int BUST_CONDITION = 21;

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public boolean isBust() {
        return value > BUST_CONDITION;
    }

    public boolean isOver(int hitCondition) {
        return value < hitCondition;
    }

    public boolean isSatisfiedBlackJackScore() {
        return value == BUST_CONDITION;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(value, o.value);
    }

}
