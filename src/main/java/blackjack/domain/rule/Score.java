package blackjack.domain.rule;

public class Score {

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isAbove(Score target) {
        return this.value > target.value;
    }

    public int getValue() {
        return value;
    }
}
