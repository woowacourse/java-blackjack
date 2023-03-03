package blackjack.domain.card;

public enum Rank {
    ACE(1, "A"), DEUCE(2, "2"), THREE(3, "3"), FOUR(4, "4"),
    FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"),
    NINE(9, "9"), TEN(10, "10"), JACK(10, "J"), QUEEN(10, "Q"),
    KING(10, "K");

    private final int value;
    private final String rankFormat;

    Rank(final int value, final String rankFormat) {
        this.value = value;
        this.rankFormat = rankFormat;
    }

    public int getValue() {
        return value;
    }

    public String getRankFormat() {
        return rankFormat;
    }
}
