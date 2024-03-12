package blackjack.domain.rule;

public class Score {

    private static final int MAX_SCORE = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isAbove(Score target) {
        return this.value > target.value;
    }

    public boolean isMaxScore() {
        return value == MAX_SCORE;
    }

    public int getValue() {
        return value;
    }
}
