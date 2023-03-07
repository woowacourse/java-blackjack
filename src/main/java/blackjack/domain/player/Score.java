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

    public Score plus(final Score score) {
        return new Score(score.value + value);
    }

    public int getValue() {
        return value;
    }
}
