package blackjack.domain.player;

public final class Score {

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score min() {
        return new Score(0);
    }

    public int getValue() {
        return value;
    }
}
