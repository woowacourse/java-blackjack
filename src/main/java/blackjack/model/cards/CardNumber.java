package blackjack.model.cards;

public enum CardNumber {
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

    private final Score score;

    CardNumber(final int score) {
        this(new Score(score));
    }

    CardNumber(final Score score) {
        this.score = score;
    }

    public static CardNumber generate(final int index) {
        return values()[index];
    }

    public boolean isAce() {
        return ACE.equals(this);
    }

    public Score getScore() {
        return score;
    }
}
