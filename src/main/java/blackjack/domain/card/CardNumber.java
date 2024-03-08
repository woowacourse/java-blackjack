package blackjack.domain.card;

public enum CardNumber {
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
    KING(10),
    ACE(11);

    private final int number;

    CardNumber(final int number) {
        this.number = number;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getNumber() {
        return number;
    }
}
