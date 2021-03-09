package blackjack.domain.participant;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int TEN = 10;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score reduceScoreIfBust() {
        if (this.isBust()) {
            return new Score(this.value - TEN);
        }
        return this;
    }

    public boolean isBust() {
        return this.value > BLACKJACK;
    }

    public int getValue() {
        return this.value;
    }
}
