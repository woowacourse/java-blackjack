package blackjack.domain.player;

public final class Score {

    private static final int MAX_VALUE = 21;
    private static final Score min = new Score(0);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score min() {
        return min;
    }

    public boolean isBust() {
        return value > MAX_VALUE;
    }

    public boolean isBlackjack() {
        return value == MAX_VALUE;
    }

    public boolean isSameScore(final Score score) {
        return score.value == value;
    }

    public Score plus(final Score score) {
        return new Score(score.value + value);
    }

    public boolean isBiggerThan(final Score score) {
        return value > score.value;
    }

    public int getValue() {
        return value;
    }
}
