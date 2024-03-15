package blackjack.domain.game;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score other) {
        return this.add(other.value);
    }

    public Score add(int value) {
        return new Score(this.value + value);
    }

    public boolean isBlackjack() {
        return this.value == BLACKJACK;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isPlayerHit() {
        return !isBust();
    }

    public boolean isDealerHit() {
        return this.value <= DEALER_HIT_THRESHOLD;
    }

    public boolean canHit(Score threshold) {
        return value <= threshold.value;
    }

    public boolean isBiggerThan(Score other) {
        return this.value > other.value;
    }

    public boolean isLessThan(Score other) {
        return this.value < other.value;
    }

    public int getValue() {
        return value;
    }
}
