package blackjack.domain.card;

public enum CardScore {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    public static final int ACE_ADD_SCORE = 10;
    private final int score;

    CardScore(int score) {
        this.score = score;
    }

    public int get() {
        return score;
    }
}
