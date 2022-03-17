package blackjack.domain.participant.condition;

public enum PlayerCount {

    ALLOWED_MINIMUM_COUNT(0),
    ALLOWED_MAXIMUM_COUNT(8);

    private final int count;

    PlayerCount(final int count) {
        this.count = count;
    }

    public static boolean isCountOutOfRange(final int count) {
        return (count < ALLOWED_MINIMUM_COUNT.count) || (ALLOWED_MAXIMUM_COUNT.count < count);
    }

}
