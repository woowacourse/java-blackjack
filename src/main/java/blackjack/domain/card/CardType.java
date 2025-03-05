package blackjack.domain.card;

public enum CardType {
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
    ACE(11);

    private final int value;
    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
