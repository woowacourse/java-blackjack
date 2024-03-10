package blackjack.model.deck;

public enum Score {
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
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    private static final int ACE_OPTIONAL_SCORE = 11;
    private static final int MAX_SCORE_WITHOUT_ONE_ACE = 10;

    private final int value;

    Score(final int value) {
        this.value = value;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public static int calculateAceScore(final int aceCount, final int otherCardsScore) {
        if (aceCount == 0) {
            return 0;
        }
        final int aceScoreExceptOne = (aceCount - 1) * ACE.value;
        if (otherCardsScore + aceScoreExceptOne <= MAX_SCORE_WITHOUT_ONE_ACE) {
            return ACE_OPTIONAL_SCORE + aceScoreExceptOne;
        }
        return ACE.value + aceScoreExceptOne;
    }

    public int getValue() {
        return value;
    }
}
