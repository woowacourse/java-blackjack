package blackjack.domain.player;

public final class Score {

    private static final int MAX = 21;
    private static final int MIN = 0;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score min() {
        return new Score(MIN);
    }

    public boolean isBust() {
        return value > MAX;
    }

    public int getValue() {
        return value;
    }
}
