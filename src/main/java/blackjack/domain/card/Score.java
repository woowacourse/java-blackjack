package blackjack.domain.card;

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
    JACK(10),
    KING(10),
    QUEEN(10),
    ACE_ELEVEN(11);

    private int amount;

    Score(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
